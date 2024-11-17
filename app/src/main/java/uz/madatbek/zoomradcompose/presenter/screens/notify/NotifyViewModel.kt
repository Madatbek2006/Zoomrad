package uz.madatbek.zoomradcompose.presenter.screens.notify

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject
@HiltViewModel
class NotifyViewModel @Inject constructor(
    private val navigator: AppNavigator
):ViewModel(),ScreenModel,NotifyContract.Model {
    override val container = container<NotifyContract.UIState, NotifyContract.SideEffect>(NotifyContract.UIState.InitUIState)



    override fun onEventDispatcher(intent: NotifyContract.Intent) =intent{
        when(intent){
            NotifyContract.Intent.OnClickBack->{
                navigator.back()
            }
        }
    }

}