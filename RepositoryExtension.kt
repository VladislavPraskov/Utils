
/**
 * Reference: https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
 */

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T?
): ApiResult<T?> {
    return try {
        ApiResult.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        throwable.printStackTrace() //todo crashlytics
        when (throwable) {
            is IOException -> {
                NetworkError(code = 524,
                    errorRes = R.string.error_connection) //524 A Timeout Occurred
            }
            is HttpException -> {
                val code = throwable.code()
                var errorResponse = convertErrorBody(throwable)
                val resId = if (throwable.code() in 500..599) {
                    errorResponse = null
                    R.string.backend_connection_error
                } else R.string.unknown_error

                NetworkError(code = code, errorStr = errorResponse, errorRes = resId)
            }
            else -> {
                NetworkError(code = null, errorRes = R.string.unknown_error)
            }
        }
    }
}


private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.response()?.errorBody()?.string()
    } catch (exception: Exception) {
        null
    }
}


suspend fun <T> safeCacheCall(
    cacheCall: (suspend () -> T?)?,
    onSuccess: (suspend () -> T?)? = null,
    onError: (suspend () -> T?)? = null
): T? {
    return try {
        val result = cacheCall?.invoke()
        onSuccess?.invoke()
        result
    } catch (throwable: Throwable) {
        onError?.invoke() //todo crashlytics
    }
}
