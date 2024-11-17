package uz.madatbek.zoomradcompose.presenter.screens.main.pages.payments

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.presenter.screens.payments.PaymentsScreen
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(
    private val navigator: AppNavigator
):ViewModel(),PaymentsContract.Model {
    override val container = container<PaymentsContract.UIState, PaymentsContract.SideEffect>(PaymentsContract.UIState.InitState)


    override fun onEventDispatcher(intent: PaymentsContract.Intent) =intent{
        when(intent){
            PaymentsContract.Intent.onClickBack->{
                navigator.back()
            }
            PaymentsContract.Intent.OpenPaymentsScreen->{
                navigator.navigateTo(PaymentsScreen())
            }
        }
    }

}