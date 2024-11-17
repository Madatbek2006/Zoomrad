package uz.madatbek.zoomradcompose.presenter.screens.addcard

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
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import uz.madatbek.zoomradcompose.utils.postUiState
import uz.madatbek.zoomradcompose.utils.safetyFlow
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    private val navigator: AppNavigator,
    private val cardRepository: CardRepository
) : ViewModel(), ScreenModel, AddCardContract.Model {
    override val container =
        container<AddCardContract.UIState, AddCardContract.SideEffect>(AddCardContract.UIState.Default)

    override fun onEventDispatchers(intent: AddCardContract.Intent) = intent {
        when (intent) {
            AddCardContract.Intent.OnBackScreen -> {
                navigator.back()
            }
            is AddCardContract.Intent.AddCard -> {
                postUiState(AddCardContract.UIState.Progress)
                cardRepository.addCard(intent.card).apply {
                    onSuccess {
//                        postSideEffect(AddCardContract.SideEffect.Toast(message = it.message))
                        delay(200)
                        postUiState(AddCardContract.UIState.Success)
                    }
                    onFailure {
                        postSideEffect(
                            AddCardContract.SideEffect.Toast(
                                message = it.message ?: "Unknown error!!"
                            )
                        )
                        postUiState(AddCardContract.UIState.Fail)
                    }
                }
            }
        }
    }

    private fun addCard(card: AddCardData): Flow<Result<String>> = safetyFlow {


    }
}

