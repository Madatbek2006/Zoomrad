package uz.madatbek.zoomradcompose.presenter.screens.pincode

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.data.model.LoginData
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.presenter.screens.login.LoginScreen
import uz.madatbek.zoomradcompose.presenter.screens.main.MainScreen
import uz.madatbek.zoomradcompose.utils.myLog
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class PinCodeModel @Inject constructor(
    val navigator: AppNavigator
)
    :PinCodeContract.Model,ScreenModel,ViewModel() {

    override val container =container<PinCodeContract.UIState,PinCodeContract.SideEffect>(PinCodeContract.UIState.InitState)



    override fun onEventDispatcher(intent: PinCodeContract.Intent) =intent{
        when(intent){
            PinCodeContract.Intent.OpenMainScreen->{
                "Salom".myLog()
                navigator.replaceAll(MainScreen())
            }
            PinCodeContract.Intent.LogOut->{
                if (false){
                    MyShar.setUserLoginData(LoginData(null,null,null))
                    MyShar.setAusUser(false)
                    navigator.replaceAll(LoginScreen())
                }

            }
        }
    }
}