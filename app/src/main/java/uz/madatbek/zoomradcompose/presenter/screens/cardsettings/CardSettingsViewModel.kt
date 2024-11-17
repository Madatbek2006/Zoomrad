package uz.madatbek.zoomradcompose.presenter.screens.cardsettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.AddCardData
import uz.madatbek.zoomradcompose.domain.CardRepository
import uz.madatbek.zoomradcompose.utils.myLog
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import uz.madatbek.zoomradcompose.utils.postUiState
import uz.madatbek.zoomradcompose.utils.safetyFlow
import javax.inject.Inject

@HiltViewModel
class CardSettingsViewModel @Inject constructor(
    private val navigator: AppNavigator,
    private val cardRepository: CardRepository
) : ViewModel(), ScreenModel, CardSettingsContract.Model {
    override val container =
        container<CardSettingsContract.UIState, CardSettingsContract.SideEffect>(CardSettingsContract.UIState.Default)

    override fun onEventDispatchers(intent: CardSettingsContract.Intent) = intent {
        when (intent) {
            CardSettingsContract.Intent.OnBackScreen -> {
                navigator.back()
            }
            is CardSettingsContract.Intent.UpdateCard -> {
                postUiState(CardSettingsContract.UIState.Progress)

                cardRepository.updateCard(intent.card).apply{
                    onSuccess {
                        postUiState(CardSettingsContract.UIState.Success)
                    }
                    onFailure {
                        postSideEffect(
                            CardSettingsContract.SideEffect.Toast(
                                message = it.message ?: "Unknown error!!"
                            )
                        )
                        "${it.message} Error message".myLog()
                        postUiState(CardSettingsContract.UIState.Fail)
                    }
                }
            }
        }
    }

}

