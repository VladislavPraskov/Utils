package ${packageName}

import android.app.Application
import androidx.lifecycle.liveData


class ${viewModelClass}(app: Application, val interactor: SomeInteractor) : BaseViewModel<${action}, ${viewState}, ${resultAction}>(app,${viewState}()) {

    override fun handleNewAction(action: ${action}) = liveData<${resultAction}> {
        when (action) {
            is ${action}.SomeAction -> {
                emit(${resultAction}.Loading)
            }
            is ${action}.SomeAction2 -> {
                emit(${resultAction}.SomeAction())
            }
        }
    }
    
    override fun reduceNewViewState(
        currentViewState: ${viewState},
        result: ${resultAction}
    ): ${viewState} {
        return when (result) {
            is ${resultAction}.SomeAction -> {
                currentViewState.copy()
            }
            is ${resultAction}.Loading -> {
                currentViewState.copy(isLoading = true)
            }
        }
    }
}
