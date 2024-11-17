package uz.madatbek.zoomradcompose.presenter.screens.viewcards

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
import uz.madatbek.zoomradcompose.domain.CardRepository
import uz.madatbek.zoomradcompose.presenter.screens.addcard.AddCardScreen
import uz.madatbek.zoomradcompose.presenter.screens.cardsettings.CardSettingsScreen
import uz.madatbek.zoomradcompose.presenter.screens.monitoring.MonitoringScreen
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import uz.madatbek.zoomradcompose.utils.postUiState
import javax.inject.Inject

@HiltViewModel
class ViewCardsViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val navigator: AppNavigator
) : ViewModel(), ScreenModel, ViewCardsContract.Model {
    override val container = container<ViewCardsContract.UIState, ViewCardsContract.SideEffect>(
        ViewCardsContract.UIState(data = emptyList())
    )

    private val uiState by lazy { container.stateFlow.value }


    suspend fun loadCards(){
        cardRepository.getAllCards().apply {
            onSuccess {
                intent { postUiState(ViewCardsContract.UIState(data = it, progress = false)) }
            }
            onFailure {
                intent {
                    postUiState(ViewCardsContract.UIState(progress = false))
                    postSideEffect(ViewCardsContract.SideEffect.Toast(it.message ?: "Unknown Error!!"))
                }
            }
        }

    }

    override fun onEventDispatcher(intent: ViewCardsContract.Intent) =intent{
        when(intent){
            ViewCardsContract.Intent.OnCLickBack->{
                navigator.back()
            }
            ViewCardsContract.Intent.OpenAddScreen->{
                navigator.navigateTo(AddCardScreen())
            }
            is ViewCardsContract.Intent.BottomSheetAction->{
                when(intent.resId){
                    R.string.addCard_screen_botrom_sheet_monitoring->navigator.navigateTo(MonitoringScreen())
                    R.string.addCard_screen_botrom_sheet_card_settings->navigator.navigateTo(CardSettingsScreen(intent.data))
                    R.string.addCard_screen_botrom_sheet_rekvizits->{}
                    R.string.addCard_screen_botrom_sheet_make_the_main_one->{}
                    R.string.addCard_screen_botrom_sheet_delete_card->{ postUiState(ViewCardsContract.UIState(dialog = true, data = uiState.data))}
                }
            }
            is ViewCardsContract.Intent.DeleteCard->{
                postUiState(ViewCardsContract.UIState(progress = true))
                cardRepository.deleteCard(intent.id).apply {
                    onSuccess {
                        loadCards()
                    }
                    onFailure {
                        intent {
                            postUiState(ViewCardsContract.UIState(progress = false))
                            postSideEffect(ViewCardsContract.SideEffect.Toast(it.message ?: "Unknown Error!!"))
                        }
                    }
                }
            }
            ViewCardsContract.Intent.DismissDialog->{
                postUiState(ViewCardsContract.UIState(dialog = false,data = uiState.data))
            }
        }
    }
}



