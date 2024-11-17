package uz.madatbek.zoomradcompose.presenter.screens.mycards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.domain.CardRepository
import uz.madatbek.zoomradcompose.presenter.screens.addcard.AddCardScreen
import uz.madatbek.zoomradcompose.presenter.screens.monitoring.MonitoringScreen
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import uz.madatbek.zoomradcompose.utils.postUiState
import javax.inject.Inject

@HiltViewModel
class MyCardsViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val navigator: AppNavigator
) : ViewModel(), ScreenModel, MyCardsContract.Model {
    override val container = container<MyCardsContract.UIState, MyCardsContract.SideEffect>(
        MyCardsContract.UIState(data = emptyList())
    )

    private val uiState by lazy { container.stateFlow.value }

    init {
        intent {
            load()
        }
    }
   private suspend fun load(){
        cardRepository.getAllCards().apply {
            onSuccess {
                intent { postUiState(MyCardsContract.UIState(data = it)) }
            }
            onFailure {
                intent {
                    postSideEffect(MyCardsContract.SideEffect.Toast(it.message ?: "Unknown Error!!"))
                }
            }
        }

    }

    override fun onEventDispatcher(intent: MyCardsContract.Intent) =intent{
        when(intent){
            MyCardsContract.Intent.OnCLickBack->{
                navigator.back()
            }
            MyCardsContract.Intent.OpenAddScreen->{
                navigator.navigateTo(AddCardScreen())
            }
            is MyCardsContract.Intent.OnClickCardData->{
                MyShar.setChooseCard(intent.data.pan)
                navigator.back()
            }
        }
    }
}



