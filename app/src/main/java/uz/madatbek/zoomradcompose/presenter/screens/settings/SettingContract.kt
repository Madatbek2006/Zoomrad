package uz.madatbek.zoomradcompose.presenter.screens.settings

import org.orbitmvi.orbit.ContainerHost

interface SettingContract {

    interface Model:ContainerHost<UIState,SideEffect>{
        fun onEventDispatchers(intent: Intent)
    }

    interface UIState{
        data object InitUIState:UIState
    }

    interface SideEffect{
        data class Toast(val message:String)
    }
    interface Intent{
        data object OnCLickBack:Intent
        data object OpenReadScreen:Intent
    }
}