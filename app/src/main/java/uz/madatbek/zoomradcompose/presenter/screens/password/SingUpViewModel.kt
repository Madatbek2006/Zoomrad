package uz.madatbek.zoomradcompose.presenter.screens.password

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
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.SingUpUserData
import uz.madatbek.zoomradcompose.domain.LoginRepository
import uz.madatbek.zoomradcompose.presenter.screens.login.LoginScreen
import uz.madatbek.zoomradcompose.presenter.screens.sms.SMSScreen
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class SingUpViewModel @Inject constructor(
    val navigator: AppNavigator,
    private val repository: LoginRepository
):ViewModel(),ScreenModel,SingUpContract.Model {
    override val container =container<SingUpContract.UIState,SingUpContract.SideEffect>(SingUpContract.UIState.InitState)

    override fun onEventDispatchers(intent: SingUpContract.Intent) =intent{
        when(intent){
            is SingUpContract.Intent.OpenCreatePinCodeScreen->{
                MyShar.setUserLoginData(
                    LoginData(
                        phone = MyShar.getUserLoginData()?.phone,
                        pinCode = null,
                        password = intent.password
                    )
                )
                val singUpUserData=SingUpUserData(intent.phone,intent.password,"Test","Test","969822000000","0")
                repository.singUpUser(
                    singUpUserData
                ).onEach {
                    it.onSuccess {
                        MyShar.setIsSingInUser(false)
                        MyShar.setUserSMSToken(it.token)
                        navigator.replaceAll(SMSScreen(singUpUserData))
                    }
                    it.onFailure {
                        postSideEffect(SingUpContract.SideEffect.Toast(it.message?:"Unknown error!!"))
                    }
                }.launchIn(viewModelScope)

            }
            SingUpContract.Intent.OpenSingInScreen->{
                navigator.replace(LoginScreen())
            }
        }
    }
}