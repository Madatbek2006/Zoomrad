package uz.madatbek.zoomradcompose.presenter.screens.main.pages.transver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.api.TransferApi
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.GetCardOwnerByPanData
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferRequest
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferVerifyRequest
import uz.madatbek.zoomradcompose.domain.CardRepository
import uz.madatbek.zoomradcompose.domain.TransferRepository
import uz.madatbek.zoomradcompose.presenter.screens.addcard.AddCardScreen
import uz.madatbek.zoomradcompose.presenter.screens.mycards.MyCardsScreen
import uz.madatbek.zoomradcompose.presenter.screens.transfer.TransferScreen
import uz.madatbek.zoomradcompose.presenter.screens.transferverify.TransferVerifyScreen
import uz.madatbek.zoomradcompose.presenter.screens.viewcards.ViewCardsContract
import uz.madatbek.zoomradcompose.utils.myLog
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import uz.madatbek.zoomradcompose.utils.postUiState
import uz.madatbek.zoomradcompose.utils.safetyFlow
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val navigator: AppNavigator,
    private val cardRepository: CardRepository,
    private val transferRepository: TransferRepository
):ViewModel(),ScreenModel,TransferContract.Model {
    override val container= container<TransferContract.UIState, TransferContract.SideEffect>(TransferContract.UIState.InitUIState)


    override fun onEventDispatchers(intent: TransferContract.Intent)=intent{
        when(intent){
            TransferContract.Intent.OpenAddScreen->{
                navigator.navigateTo(AddCardScreen())
            }
            is TransferContract.Intent.GetCardByPan->{
                cardRepository.getCardOwnerByPan(card = intent.pan)
                    .apply {
                        onSuccess { postUiState(TransferContract.UIState.Text(it.pan,true)) }

                        onFailure { postUiState(TransferContract.UIState.Text(it.message?:"Unknown error!!",false)) }
                    }
            }
            is TransferContract.Intent.OpenTransferScreen->{
                navigator.navigateTo(TransferScreen(intent.data))
            }
            is TransferContract.Intent.OnClickItem1->{
                when(intent.data.name){
                    R.string.transfer_page_item_1->{
                        navigator.navigateTo(MyCardsScreen())
                    }
                    R.string.transfer_page_item_2->{

                    }
                }
            }

        }
    }

    suspend fun loadCards(){
        cardRepository.getAllCards().apply {
            onSuccess {
                "it.carddata=>${it.size}".myLog()
                intent { postUiState(TransferContract.UIState.LoadAllCards(it)) }
            }
            onFailure {
                intent { postSideEffect(TransferContract.SideEffect.Toast(it.message ?: "Unknown Error!!")) }
            }
        }

    }



}