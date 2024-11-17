package uz.madatbek.zoomradcompose.presenter.screens.notify

import org.orbitmvi.orbit.ContainerHost

interface NotifyContract {

    interface Model:ContainerHost<UIState,SideEffect>{
        fun onEventDispatcher(intent: Intent)
    }
    interface UIState{
        data object InitUIState:UIState
    }

    interface SideEffect{

    }

    interface Intent{
        data object OnClickBack:Intent
    }
}