package uz.madatbek.zoomradcompose.presenter.screens.splash

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.presenter.screens.createpin.CreatePinCodeScreen
import uz.madatbek.zoomradcompose.presenter.screens.login.LoginScreen
import uz.madatbek.zoomradcompose.presenter.screens.pincode.PinCodeScreen
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class SplashModel @Inject constructor(
    private val navigator: AppNavigator
) :SplashContract.Model,ViewModel(),ScreenModel {
    override val container= container<SplashContract.UIState,SplashContract.SideEffect>(SplashContract.UIState.InitState)


    override fun onEventDispatcher(intent: SplashContract.Intent) = intent{
        when(intent){
            SplashContract.Intent.NextScreen->{
                if (MyShar.getUserLoginData()!!.pinCode != "") {
                    navigator.replace(PinCodeScreen())
                } else if (!MyShar.isAusUser()) {
                    navigator.replace(LoginScreen())
                } else {
                    navigator.replace(CreatePinCodeScreen())
                }
            }
        }
    }
}
/*

  if (MyShar.getUserLoginData()!!.pinCode != "") {
                    screenModel.onEventDispatcher(SplashContract.Intent.NextScreen)
                } else if (!MyShar.isAusUser()) {
                    screenModel.onEventDispatcher(SplashContract.Intent.OpenLoginScreen)
                } else {
                    screenModel.onEventDispatcher(SplashContract.Intent.OpenCreatePinCodeScreen)
                }
*/