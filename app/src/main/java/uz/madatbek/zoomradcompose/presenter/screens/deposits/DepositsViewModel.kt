package uz.madatbek.zoomradcompose.presenter.screens.deposits

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class DepositsViewModel @Inject constructor(
    private val navigator: AppNavigator
):ViewModel(),ScreenModel, DepositsContract.Model {


    override val container = container<DepositsContract.UIState, DepositsContract.SideEffect>(
        DepositsContract.UIState.InitState
    )


    override fun onEventDispatcher(intent: DepositsContract.Intent) =intent{
        when(intent){
            DepositsContract.Intent.onClickBack ->{
                navigator.back()
            }
        }
    }
}


/*
@HiltViewModel
class CashBackViewModel @Inject constructor(
    private val navigator: AppNavigator
):ViewModel(),ScreenModel,CashBackContract.Model {


    override val container = container<CashBackContract.UIState, CashBackContract.SideEffect>(CashBackContract.UIState.InitState)


    override fun onEventDispatcher(intent: CashBackContract.Intent) =intent{
        when(intent){
            CashBackContract.Intent.onClickBack->{
                navigator.back()
            }
        }
    }
}
*/