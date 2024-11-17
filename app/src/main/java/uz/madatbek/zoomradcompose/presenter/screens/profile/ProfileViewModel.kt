package uz.madatbek.zoomradcompose.presenter.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.domain.UserRepository
import uz.madatbek.zoomradcompose.utils.myLog
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import uz.madatbek.zoomradcompose.utils.postUiState
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val navigation: AppNavigator,
    private val userRepository: UserRepository
) : ViewModel(), ScreenModel, ProfileContract.Model {


    override val container =
        container<ProfileContract.UIState, ProfileContract.SideEffect>(ProfileContract.UIState.Progress)

    init {
        loadUserInfo()
    }

    override fun onEventDispatchers(intent: ProfileContract.Intent) = intent {
        when (intent) {
            ProfileContract.Intent.OnCLickBack -> {
                if (container.stateFlow.value==ProfileContract.UIState.Success){
                    loadUserInfo()
                }else{
                    navigation.back()
                }

            }

            is ProfileContract.Intent.OnClickCheck -> {
                postUiState(ProfileContract.UIState.Progress)
                "OnClickCheck".myLog()
                "${intent.user}".myLog()
                userRepository.updateUserInfo(intent.user)
                    .onEach {
                        it.onSuccess {
                            postUiState(ProfileContract.UIState.Success)
                        }
                        it.onFailure {
                            postSideEffect(
                                ProfileContract.SideEffect.Toast(
                                    message = it.message ?: "Unknown error!!"
                                )
                            )
                            loadUserInfo()
                        }
                    }.launchIn(viewModelScope)
            }

            else -> {

            }
        }
    }

    private fun loadUserInfo() {
        userRepository.getFullUserInfo().onEach {
            it.onSuccess {
                intent { postUiState(ProfileContract.UIState.InitUIState(it)) }
            }
            it.onFailure {
                intent {
                    postSideEffect(
                        ProfileContract.SideEffect.Toast(
                            message = it.message ?: "Unknown error!!"
                        )
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}