package uz.madatbek.zoomradcompose.presenter.screens.intro

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.presenter.screens.main.MainScreen
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val navigator: AppNavigator
):ViewModel(),ScreenModel,IntoContract.Module {
    override val container= container<IntoContract.UIState, IntoContract.SideEffect>(IntoContract.UIState.InitUIState)



    override fun onEventDispatcher(intent: IntoContract.Intent) =intent{
        when(intent){
            IntoContract.Intent.ScipInto->{
                navigator.replaceAll(MainScreen())
            }
        }
    }


}