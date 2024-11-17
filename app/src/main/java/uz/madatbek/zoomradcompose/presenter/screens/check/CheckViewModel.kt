package uz.madatbek.zoomradcompose.presenter.screens.check

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class CheckViewModel @Inject constructor(
    private val navigator: AppNavigator
):ViewModel(),ScreenModel, CheckContract.Model {


    override val container = container<CheckContract.UIState, CheckContract.SideEffect>(
        CheckContract.UIState.InitState
    )


    override fun onEventDispatcher(intent: CheckContract.Intent) =intent{
        when(intent){
            CheckContract.Intent.onClickBack ->{
                navigator.back()
            }
        }
    }
}

