package uz.madatbek.zoomradcompose.presenter.screens.cashback

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.presenter.screens.cashback.CashBackContract
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class CashBackViewModel @Inject constructor(
    private val navigator: AppNavigator
):ViewModel(),ScreenModel, CashBackContract.Model {


    override val container = container<CashBackContract.UIState, CashBackContract.SideEffect>(
        CashBackContract.UIState.InitState
    )


    override fun onEventDispatcher(intent: CashBackContract.Intent) =intent{
        when(intent){
            CashBackContract.Intent.onClickBack ->{
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