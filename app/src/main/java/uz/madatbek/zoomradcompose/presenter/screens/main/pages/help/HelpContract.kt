package uz.madatbek.zoomradcompose.presenter.screens.main.pages.help

import org.orbitmvi.orbit.ContainerHost

interface HelpContract {
    interface Model:ContainerHost<UIState,SideEffect>{
        fun onEventDispatchers(intent: Intent)
    }

    interface UIState{
        data object InitUState:UIState
    }

    interface SideEffect{
        data class Toast(val massage:String)
    }

    interface Intent{
        object OpenMapScreen:Intent
    }
}