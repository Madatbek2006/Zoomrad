package uz.madatbek.zoomradcompose.presenter.screens.monitoring

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferDetail
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferHistoryResponse
import uz.madatbek.zoomradcompose.domain.TransferRepository
import uz.madatbek.zoomradcompose.utils.myLog
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import uz.madatbek.zoomradcompose.utils.postUiState
import uz.madatbek.zoomradcompose.utils.safetyFlow
import javax.inject.Inject

@HiltViewModel
class MonitoringViewModel @Inject constructor(
    private val navigator: AppNavigator,
   private val transferRepository: TransferRepository
):ViewModel(),ScreenModel, MonitoringContract.Model {
    override val container =container<MonitoringContract.UIState, MonitoringContract.SideEffect>(
        MonitoringContract.UIState(
            emptyList(),
            true
        )
    )
    override fun onEventDispatchers(intent: MonitoringContract.Intent) =intent{
        when(intent){
            MonitoringContract.Intent.NextLoadData ->{

            }
            MonitoringContract.Intent.OnClickBack ->{
                navigator.back()
            }
        }
    }
    fun getHistory(size:Int, pageCount: Int): Flow<PagingData<TransferDetail>> =
        transferRepository.getTransferHistory(size = size, currentPage = pageCount).cachedIn(viewModelScope)

}