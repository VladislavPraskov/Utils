
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
     * Ïðèíèìàåò íîâûé action, îòïðàâëåííûé èç àêòèâèòè, îáðàáàòûâàåò åãî è âîçðàùàåò resultAction
     */
    protected abstract fun handleNewAction(action: Action): LiveData<ResultAction>

    /** (4)
     * Âûñòàâëÿåò íîâîå çíà÷åíèå internalViewState â çàâèñèìîñòè îò ðåçóëüòàòà handleNewAction
     */
    protected abstract fun reduceNewViewState(
        currentViewState: ViewState,
        result: ResultAction
    ): ViewState


    private val nextAction = MutableLiveData<Action>()

    lateinit var viewState: LiveData<ViewState>
        private set

    /** (2)
     * Àêòèâèòè ïîäïèñàíà íà viewState
     * Ïîñëå èçìåíåíèÿ nextAction(1) ñðàáàòûâàåò switchMap è âûçûâàåòñÿ ìåòîä handleNewAction
     * êîòîðûé îáðàáàòûâàåò action îïðàâëåííûé èç view. Ïî ðåçóëüòàòàì handleNewAction() âîçâðàùàåòñÿ
     * LiveData ñ òèïîì Result, ïîñëå ÷åãî ñðàáàòûâàåò map() è âûçûâàåòñÿ reduceNewViewState() êîòîðûé
     * îáíîâëÿåò çíà÷åíèå internalViewState, èç-çà ÷åãî îáíîâëÿåòñÿ viewState:LiveDate íà êîòîðûé ïîäïèñàíà activity
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
     * view âûçûâàåò ýòîò ìåòîä íà viewModel è ïåðåäàå¸ò íîâîå ñîñòîÿíèå: viewModel.setNextAction(action: Action)
     */
    fun setNextAction(action: Action) {
        nextAction.value = action
    }
}
