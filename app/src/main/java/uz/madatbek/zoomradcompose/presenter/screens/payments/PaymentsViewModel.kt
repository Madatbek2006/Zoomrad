package uz.madatbek.zoomradcompose.presenter.screens.payments

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.data.model.PaynetData
import uz.madatbek.zoomradcompose.data.model.TransferItem1Data
import uz.madatbek.zoomradcompose.presenter.screens.paynet.PaynetScreen
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(
    private val navigator: AppNavigator
):ViewModel(),ScreenModel,PaymentsContract.Model {


    override val container = container<PaymentsContract.UIState, PaymentsContract.SideEffect>(PaymentsContract.UIState.InitState)


    @SuppressLint("ResourceType")
    override fun onEventDispatcher(intent: PaymentsContract.Intent) =intent{
        when(intent){
            PaymentsContract.Intent.onClickBack->{
                navigator.back()
            }
           is PaymentsContract.Intent.OpenPaynetScreen->{
               val data=intent.data
                navigator.navigateTo(PaynetScreen(PaynetData(data.icon,data.name)))
            }
            else -> {}
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