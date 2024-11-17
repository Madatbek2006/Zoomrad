package uz.madatbek.zoomradcompose.presenter.screens.transferverify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.data.model.CheckData
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferVerifyRequest
import uz.madatbek.zoomradcompose.domain.TransferRepository
import uz.madatbek.zoomradcompose.presenter.screens.check.CheckScreen
import uz.madatbek.zoomradcompose.utils.SuccessEnum
import uz.madatbek.zoomradcompose.utils.myLog
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import uz.madatbek.zoomradcompose.utils.postUiState
import java.util.UUID
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class TransferVerifyViewModel @Inject constructor(
    private val transferRepository:TransferRepository,
    private val navigator: AppNavigator
):ViewModel(),ScreenModel,TransferVerifyContract.Model {
    var time:Long=0


    override val container = container<TransferVerifyContract.UIState, TransferVerifyContract.SideEffect>(TransferVerifyContract.UIState(
        SuccessEnum.NONE))

    override fun onEventDispatcher(intent: TransferVerifyContract.Intent) =intent{
        when(intent){
            is  TransferVerifyContract.Intent.TransferVerify ->{
                transferRepository.transferVerify(transferVerify = TransferVerifyRequest(MyShar.getTransferVerifyToken(),intent.sms)).onEach {
                    it.onSuccess {
                        time=System.currentTimeMillis()
                        "onSuccess  transferVerify".myLog()
                        postUiState(TransferVerifyContract.UIState(SuccessEnum.SUCCESS))
                        postSideEffect(TransferVerifyContract.SideEffect.Toast(it.message?:"Unknown Error!!"))
                    }
                    it.onFailure {
                        "onFailure  transferVerify".myLog()
                        postUiState(TransferVerifyContract.UIState(SuccessEnum.FAIL))
                        postSideEffect(TransferVerifyContract.SideEffect.Toast(it.message?:"Unknown Error!!"))
                    }
                }.launchIn(viewModelScope)
            }
            TransferVerifyContract.Intent.OnCLickBack->{
                navigator.back()
            }
            is TransferVerifyContract.Intent.Transfer->{
                transferRepository.transferFOrCard(intent.request).onEach {
                    it.onSuccess {
                        MyShar.setTransferVerifyToken(it.token)
                    }
                    it.onFailure {
                        
                    }
                }.launchIn(viewModelScope)
            }

            is TransferVerifyContract.Intent.OpenCheckScreen->{
                intent.data.apply {
                    navigator.replace(CheckScreen(CheckData(name,recipientCard,"000800080008"+data.pan,amount,time,"ECECEFOPKPO0"+Random.nextInt(10000,99999),"E0000017",0,"Success")))
                }
            }
        }
    }


}