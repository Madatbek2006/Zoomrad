package uz.madatbek.zoomradcompose.presenter.screens.createpin

import org.orbitmvi.orbit.ContainerHost

interface CreatePinCodeContract {
    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class  UIState(
        var txt:Int
    )

    sealed interface SideEffect {
        data class Toast(val message: String): SideEffect
    }

    sealed interface Intent {
        object OpenMainScreen: Intent
    }
}