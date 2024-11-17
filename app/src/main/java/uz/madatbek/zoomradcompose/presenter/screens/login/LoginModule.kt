package uz.madatbek.zoomradcompose.presenter.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.data.model.LoginData
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.singIn.SingInUserData
import uz.madatbek.zoomradcompose.domain.LoginRepository
import uz.madatbek.zoomradcompose.presenter.screens.password.SingUpScreen
import uz.madatbek.zoomradcompose.presenter.screens.sms.SMSLoginScreen
import uz.madatbek.zoomradcompose.presenter.screens.sms.SMSScreen
import uz.madatbek.zoomradcompose.utils.myLog
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class LoginModule @Inject
constructor(
    val navigator: AppNavigator,
    private val repository: LoginRepository
)
    :LoginContract.Model,ScreenModel ,ViewModel(){


    override val container =container<LoginContract.UIState,LoginContract.SideEffect>(LoginContract.UIState.InitState)


    override fun onEventDispatcher(intent: LoginContract.Intent) =intent{
        when(intent){
            is LoginContract.Intent.OpenSMSScreen->{
                val singInUserData= SingInUserData(intent.phone,intent.password)
                repository.singInUser(
                    singInUserData
                ).onEach {
                    it.onSuccess {

                        MyShar.setIsSingInUser(true)
                        MyShar.setUserLoginData(
                            LoginData(
                                phone = intent.phone,
                                pinCode = null,
                                password = intent.password
                            )
                        )
                        MyShar.setUserSMSToken(it.token)
                        navigator.replaceAll(SMSLoginScreen(singInUserData))
                    }
                    it.onFailure {
                        postSideEffect(LoginContract.SideEffect.Toast(it.message?:"Unknown error!!"))
                    }
                }.launchIn(viewModelScope)
            }
            LoginContract.Intent.OpenSingUpScreen->{
                navigator.replace(SingUpScreen())
            }
        }
    }
}