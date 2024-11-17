package uz.madatbek.zoomradcompose.presenter.screens.createpin

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.IntroData
import uz.madatbek.zoomradcompose.data.model.LoginData
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.presenter.screens.intro.IntroScreen
import uz.madatbek.zoomradcompose.presenter.screens.main.MainScreen
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject
@HiltViewModel
class CreatePinCodeModel @Inject constructor(
    val navigator: AppNavigator
)
    :CreatePinCodeContract.Model,ScreenModel,ViewModel() {
    override val container =container<CreatePinCodeContract.UIState,CreatePinCodeContract.SideEffect>(CreatePinCodeContract.UIState(
        R.string.create_pincode_4_part
    ))



    override fun onEventDispatcher(intent: CreatePinCodeContract.Intent) =intent{
        when(intent){
            CreatePinCodeContract.Intent.OpenMainScreen->{
                    delay(100)
                    navigator.replaceAll(IntroScreen())
            }
        }
    }
}