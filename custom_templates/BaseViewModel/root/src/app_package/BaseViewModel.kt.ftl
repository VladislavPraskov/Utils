package ${packageName}

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations


abstract class BaseViewModel<Action, ViewState, ResultAction>(
    app: Application,
    initState: ViewState
) : AndroidViewModel(app) {

    val context = getApplication<App>()
    
    private val nextAction = MutableLiveData<Action>()

    lateinit var viewState: LiveData<ViewState>
        private set

    init {
        viewState = Transformations.map(Transformations.switchMap(nextAction) { action ->
            handleNewAction(action)
        })
        { result ->
            reduceNewViewState(viewState.value ?: initState, result)
        }
    }

    protected abstract fun handleNewAction(action: Action): LiveData<ResultAction>

    protected abstract fun reduceNewViewState(
        currentViewState: ViewState,
        result: ResultAction
    ): ViewState

    
    fun setNextAction(action: Action) {
        nextAction.value = action
    }
}
