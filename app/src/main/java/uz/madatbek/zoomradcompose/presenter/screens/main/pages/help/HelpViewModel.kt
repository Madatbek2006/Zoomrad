package uz.madatbek.zoomradcompose.presenter.screens.main.pages.help

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.presenter.screens.branchs.BranchScreen
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class HelpViewModel @Inject constructor(
    val navigator: AppNavigator
) :ViewModel(),ScreenModel,HelpContract.Model {
    override val container= container<HelpContract.UIState, HelpContract.SideEffect>(HelpContract.UIState.InitUState)



    override fun onEventDispatchers(intent: HelpContract.Intent) =intent{

        when(intent){
            HelpContract.Intent.OpenMapScreen->{
                navigator.navigateTo(BranchScreen())
            }
        }
    }
}