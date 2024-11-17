package uz.madatbek.zoomradcompose.presenter.screens.intro

import org.orbitmvi.orbit.ContainerHost

interface IntoContract {
    interface Module:ContainerHost<UIState,SideEffect>{
        fun onEventDispatcher(intent: Intent)
    }


    interface UIState{
        object InitUIState:UIState
    }

    sealed interface SideEffect{
        data class Toast(val message:String):SideEffect
    }

    sealed interface Intent{
        data object ScipInto:Intent
    }
}


