package uz.madatbek.zoomradcompose.presenter.screens.login

import org.orbitmvi.orbit.ContainerHost

interface LoginContract {
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
        data class OpenSMSScreen(
            val phone:String,
            val password:String
        ): Intent
        data object OpenSingUpScreen:Intent
    }
}