package uz.madatbek.zoomradcompose.presenter.screens.password

import org.orbitmvi.orbit.ContainerHost

interface SingUpContract {

    interface Model:ContainerHost<UIState,SideEffect>{
        fun onEventDispatchers(intent:Intent)
    }

    sealed interface UIState{
        data object InitState:UIState
    }

    sealed interface SideEffect{
        data class Toast(val message:String):SideEffect
    }

    sealed interface Intent{
        data class OpenCreatePinCodeScreen(val phone:String,val password:String):Intent
        data object OpenSingInScreen:Intent
    }
}

