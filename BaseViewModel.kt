
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import ru.taxcom.mytaxcom.App


abstract class BaseViewModel<Action, ViewState, ResultAction>(
    app: Application,
    initState: ViewState
) : AndroidViewModel(app) {

    val context = getApplication<App>()
    /** (3)
     * ��������� ����� action, ������������ �� ��������, ������������ ��� � ��������� resultAction
     */
    protected abstract fun handleNewAction(action: Action): LiveData<ResultAction>

    /** (4)
     * ���������� ����� �������� internalViewState � ����������� �� ���������� handleNewAction
     */
    protected abstract fun reduceNewViewState(
        currentViewState: ViewState,
        result: ResultAction
    ): ViewState


    private val nextAction = MutableLiveData<Action>()

    lateinit var viewState: LiveData<ViewState>
        private set

    /** (2)
     * �������� ��������� �� viewState
     * ����� ��������� nextAction(1) ����������� switchMap � ���������� ����� handleNewAction
     * ������� ������������ action ����������� �� view. �� ����������� handleNewAction() ������������
     * LiveData � ����� Result, ����� ���� ����������� map() � ���������� reduceNewViewState() �������
     * ��������� �������� internalViewState, ��-�� ���� ����������� viewState:LiveDate �� ������� ��������� activity
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
     * view �������� ���� ����� �� viewModel � �������� ����� ���������: viewModel.setNextAction(action: Action)
     */
    fun setNextAction(action: Action) {
        nextAction.value = action
    }
}