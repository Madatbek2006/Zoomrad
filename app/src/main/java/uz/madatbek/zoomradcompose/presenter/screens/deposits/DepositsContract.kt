package uz.madatbek.zoomradcompose.presenter.screens.deposits

import org.orbitmvi.orbit.ContainerHost

interface DepositsContract {
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
    }
}