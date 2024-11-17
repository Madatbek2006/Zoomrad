package uz.madatbek.zoomradcompose.presenter.screens.payments

import org.orbitmvi.orbit.ContainerHost
import uz.madatbek.zoomradcompose.data.model.TransferItem1Data

interface PaymentsContract {
    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object InitState: UIState
    }

    sealed interface SideEffect {
        data class Toast(val message: String): SideEffect
    }

    sealed interface Intent {
        data object onClickBack: Intent
        data class OpenPaynetScreen(
            val data: TransferItem1Data
        ):Intent
    }
}