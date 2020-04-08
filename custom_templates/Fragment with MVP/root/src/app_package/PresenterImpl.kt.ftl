package ${packageName}


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ${prefixName}PresenterImpl @Inject constructor(val interactor: Interactor) : ${prefixName}Presenter {

    @Inject
    lateinit var view: ${prefixName}View
    private val disposable = CompositeDisposable()

    override fun load() {
        val d = interactor.findUserByInn()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.progress(true) }
                .doFinally { view.progress(false) }
                .subscribe(::method, ::onError)
        disposable.add(d)

    }

    private fun onError(throwable: Throwable) {

    }
    private fun method(){}
}
