package uz.madatbek.zoomradcompose.presenter.screens.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferRequest
import uz.madatbek.zoomradcompose.domain.TransferRepository
import uz.madatbek.zoomradcompose.presenter.screens.transferverify.TransferVerifyScreen
import uz.madatbek.zoomradcompose.utils.myLog
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import uz.madatbek.zoomradcompose.utils.postUiState
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val transferRepository: TransferRepository,
    private val navigator: AppNavigator
):ViewModel(),ScreenModel,TransferContract.Model {

    override val container= container<TransferContract.UIState, TransferContract.SideEffect>(TransferContract.UIState.Default)



    override fun onEventDispatchers(intent: TransferContract.Intent) =intent{

        when(intent){
            is TransferContract.Intent.Transfer->{
                postUiState(TransferContract.UIState.Progress)
                "TransferContract.Intent.Transfer".myLog()
                val request:TransferRequest
                intent.data.apply {
                    request = TransferRequest(
                        "third-card",
                        data.id.toString(),
                        recipientCard,
                        amount.toLong(),
                    )
                }
                transferRepository.transferFOrCard(request).onEach {
                    it.onSuccess {
                        postUiState(TransferContract.UIState.Default)
                        "onSuccess".myLog()
                        MyShar.setTransferVerifyToken(it.token)

                            navigator.replace(TransferVerifyScreen(intent.data))
                    }
                    it.onFailure {
                        postUiState(TransferContract.UIState.Default)
                        postSideEffect(TransferContract.SideEffect.Toast(it.message?:"Unknown Error!!"))
                    }
                }.launchIn(viewModelScope)
            }

            TransferContract.Intent.OnClickBack->{
                navigator.back()
            }
        }
    }
}