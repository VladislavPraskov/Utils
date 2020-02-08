

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
    /** (3)
     * Принимает новый action, отправленный из активити, обрабатывает его и возращает resultAction
     */
    protected abstract fun handleNewAction(action: Action): LiveData<ResultAction>

    /** (4)
     * Выставляет новое значение internalViewState в зависимости от результата handleNewAction
     */
    protected abstract fun reduceNewViewState(
        currentViewState: ViewState,
        result: ResultAction
    ): ViewState


    private val nextAction = MutableLiveData<Action>()

    lateinit var viewState: LiveData<ViewState>
        private set

    /** (2)
     * Активити подписана на viewState
     * После изменения nextAction(1) срабатывает switchMap и вызывается метод handleNewAction
     * который обрабатывает action оправленный из view. По результатам handleNewAction() возвращается
     * LiveData с типом Result, после чего срабатывает map() и вызывается reduceNewViewState() который
     * обновляет значение internalViewState, из-за чего обновляется viewState:LiveDate на который подписана activity
     */
    init {
        viewState = Transformations.map(Transformations.switchMap(nextAction) { action ->
            handleNewAction(action)
        })
        { result ->
            reduceNewViewState(viewState.value ?: initState, result)
        }
    }


    /** (1)
     * view вызывает этот метод на viewModel и передаеёт новое состояние: viewModel.setNextAction(action: Action)
     */
    fun setNextAction(action: Action) {
        nextAction.value = action
    }
}
