package uz.madatbek.zoomradcompose.presenter.screens.exchange_rates

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class ExchangeRatesViewModel @Inject constructor(
    private val navigator: AppNavigator
):ViewModel(),ScreenModel,ExchangeRatesContract.Model {


    override val container = container<ExchangeRatesContract.UIState, ExchangeRatesContract.SideEffect>(ExchangeRatesContract.UIState.InitState)


    override fun onEventDispatcher(intent: ExchangeRatesContract.Intent) =intent{
        when(intent){
            ExchangeRatesContract.Intent.onClickBack->{
                navigator.back()
            }
        }
    }
}


/*
@HiltViewModel
class ExchangeRatesViewModel @Inject constructor(
    private val navigator: AppNavigator
):ViewModel(),ScreenModel,ExchangeRatesContract.Model {


    override val container = container<ExchangeRatesContract.UIState, ExchangeRatesContract.SideEffect>(ExchangeRatesContract.UIState.InitState)


    override fun onEventDispatcher(intent: ExchangeRatesContract.Intent) =intent{
        when(intent){
            ExchangeRatesContract.Intent.onClickBack->{
                navigator.back()
            }
        }
    }
}
*/