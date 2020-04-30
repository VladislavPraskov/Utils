
/**При запросе данных, сначала грузим из кэша,
 * отображаем, потом из сети,
 * сохраняем в кэш и снова отображаем*/
abstract class NetworkBoundResource<NetworkObj, CacheObj, ResultAction>(private val isCacheNeeded: Boolean = true) {
    val result = flow {
        //        delay(4000)
        //        Log.i("sdfdsfsdf","111111111111111111111111111111111111111111111111") //todo почему в livedata создаётся очередь. Когда корутина закрывается?

        // ****** STEP 1: VIEW CACHE ******
        //не лезим в базу, если не надо
        emit(mapToResultAction(if (isCacheNeeded) safeCacheCall({ retrieveCache() }) else null))

        // ****** STEP 2: MAKE NETWORK CALL, SAVE RESULT TO CACHE ******
        when (val apiResult = safeApiCall { networkRequest() }) {
            is ApiResult.Success -> {
                if (apiResult.value == null)
                    emit(mapErrorToResultAction(ApiResult.NetworkError(errorRes = R.string.unknown_error)))
                else {
                    // ****** STEP 3: VIEW CACHE and MARK JOB COMPLETED ******
                    safeCacheCall(
                        cacheCall = { saveCache(apiResult.value) },
                        onSuccess = {
                            //не лезим в базу, если не надо
                            emit(mapToResultAction(if (isCacheNeeded) safeCacheCall({ retrieveCache() }) else null))
                        }
                    )
                }
            }
            is ApiResult.NetworkError -> emit(mapErrorToResultAction(apiResult))
        }
    }

    abstract suspend fun networkRequest(): NetworkObj?
    abstract suspend fun retrieveCache(): CacheObj?
    abstract suspend fun saveCache(networkObject: NetworkObj)
    abstract fun mapToResultAction(cache: CacheObj?): ResultAction
    abstract fun mapErrorToResultAction(error: ApiResult.NetworkError?): ResultAction

}


class CategoriesRepositoryImpl(val api: ApiService, val db: DataBase) :
        CategoriesRepository {

    override fun loadCategories(): LiveData<CategoriesResultAction> {
        return object : NetworkBoundResource<List<CategoriesResponseModel>, List<CategoryUIModel>,CategoriesResultAction>(
            isCacheNeeded = false
        ) {
            override fun mapErrorToResultAction(error: ApiResult.NetworkError?) =
                CategoriesResultAction.Error(error)

            override fun mapToResultAction(resultObj: List<CategoryUIModel>?): CategoriesResultAction {
                val list = db.categoriesDao.getCategories()
                return CategoriesResultAction.getSuccessOrEmpty(list)
            }

            override suspend fun networkRequest() = api.getCategories()
            override suspend fun retrieveCache(): List<CategoryUIModel>? {
                return db.categoriesDao.getCategories()
            }
            override suspend fun saveCache(networkObject: List<CategoriesResponseModel>) {
                val cache = networkObject.map { CategoryEntity.create(it) }
                db.categoriesDao.insertCategories(cache)
            }

        }.result.asLiveData(Dispatchers.IO)
    }

sealed class ApiResult<out T> {

    data class Success<out T>(val value: T) : ApiResult<T>()

    data class NetworkError(
        val code: Int? = null,
        private val errorStr: String? = null,
        @StringRes
        private val errorRes: Int? = null
    ) : ApiResult<Nothing>() {
        fun getError(context: Context): String? {
            return errorStr ?: errorRes?.let { context.getString(it) }
        }
    }
}
