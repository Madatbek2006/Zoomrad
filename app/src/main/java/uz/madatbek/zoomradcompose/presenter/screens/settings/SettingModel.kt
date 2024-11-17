package uz.madatbek.zoomradcompose.presenter.screens.settings

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.presenter.screens.read.ReadScreen
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class SettingModel @Inject constructor(
    private val navigator: AppNavigator
):ViewModel(),ScreenModel,SettingContract.Model {


    override val container=container<SettingContract.UIState, SettingContract.SideEffect>(SettingContract.UIState.InitUIState)


    override fun onEventDispatchers(intent: SettingContract.Intent) =intent{
        when(intent){
            SettingContract.Intent.OnCLickBack->{
                navigator.back()
            }
            SettingContract.Intent.OpenReadScreen->{
                navigator.navigateTo(ReadScreen())
            }

        }
    }
}