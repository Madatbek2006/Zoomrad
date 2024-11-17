package uz.madatbek.zoomradcompose.presenter.screens.sms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.viewmodel.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import uz.madatbek.zoomradcompose.data.model.LoginData
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.SMSData
import uz.madatbek.zoomradcompose.domain.LoginRepository
import uz.madatbek.zoomradcompose.presenter.screens.createpin.CreatePinCodeScreen
import uz.madatbek.zoomradcompose.presenter.screens.login.LoginContract
import uz.madatbek.zoomradcompose.presenter.screens.password.SingUpContract
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class SMSVIewModel @Inject constructor(
    private val navigator: AppNavigator,
    private val repository: LoginRepository
):SMSContract.Model,ViewModel(),ScreenModel {

    override val container= container<SMSContract.UIState,SMSContract.SideEffect>(SMSContract.UIState.InitSate)


    override fun onEventDispatcher(intent: SMSContract.Intent) =intent{
        when(intent){
            is SMSContract.Intent.OpenCreatePinCodeScreenLogin->{
                    repository.smsSingInVerify(SMSData(MyShar.getSMSToken(),intent.sms)).onEach {
                        it.onFailure {
                            postSideEffect(SMSContract.SideEffect.Toast(it.message?:"Unknown error!!"))
                        }
                        it.onSuccess {
                            MyShar.setAccessToken(it.accessToken)
                            MyShar.setRefreshToken(it.refreshToken)
                            MyShar.setAusUser()
                            navigator.replaceAll(CreatePinCodeScreen())
                        }
                    }.launchIn(viewModelScope)
            }
            is SMSContract.Intent.OpenCreatePinCodeScreenSingUp->{
                repository.smsSingUpVerify(SMSData(MyShar.getSMSToken(),intent.sms)).onEach {
                    it.onFailure {
                        postSideEffect(SMSContract.SideEffect.Toast(it.message?:"Unknown error!!"))
                    }
                    it.onSuccess {
                        MyShar.setAccessToken(it.accessToken)
                        MyShar.setRefreshToken(it.refreshToken)
                        MyShar.setAusUser()
                        navigator.replaceAll(CreatePinCodeScreen())
                    }
                }.launchIn(viewModelScope)
            }

            is SMSContract.Intent.SingUpUser->{
                repository.singUpUser(
                    intent.data
                ).onEach {
                    it.onSuccess {
                        MyShar.setIsSingInUser(false)
                        MyShar.setUserSMSToken(it.token)
                    }
                    it.onFailure {

                    }
                }.launchIn(viewModelScope)
            }

            is SMSContract.Intent.SingInUser -> {
                repository.singInUser(
                    intent.data
                ).onEach {
                    it.onSuccess {

                        MyShar.setIsSingInUser(true)
                        MyShar.setUserSMSToken(it.token)
                    }
                    it.onFailure {
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}