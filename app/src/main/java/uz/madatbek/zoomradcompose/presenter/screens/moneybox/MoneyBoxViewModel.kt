package uz.madatbek.zoomradcompose.presenter.screens.moneybox

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class MoneyBoxViewModel @Inject constructor(
    private val navigator: AppNavigator
):ViewModel(),ScreenModel,MoneyBoxContract.Model {


    override val container = container<MoneyBoxContract.UIState, MoneyBoxContract.SideEffect>(MoneyBoxContract.UIState.InitState)


    override fun onEventDispatcher(intent: MoneyBoxContract.Intent) =intent{
        when(intent){
            MoneyBoxContract.Intent.onClickBack->{
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