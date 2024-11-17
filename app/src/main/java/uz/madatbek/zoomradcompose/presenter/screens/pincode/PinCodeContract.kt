package uz.madatbek.zoomradcompose.presenter.screens.pincode

import org.orbitmvi.orbit.ContainerHost

interface PinCodeContract {
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
        object OpenMainScreen: Intent
        object LogOut:Intent
    }
}