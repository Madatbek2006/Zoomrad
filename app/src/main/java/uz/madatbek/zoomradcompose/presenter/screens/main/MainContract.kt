package uz.madatbek.zoomradcompose.presenter.screens.main

import org.orbitmvi.orbit.ContainerHost
import uz.madatbek.zoomradcompose.data.model.DrawerData

interface MainContract {

    interface Model:ContainerHost<UIState,SideEffect>{
        fun onEventDispatcher(intent: Intent)
    }

    interface UIState{
        data object Default:UIState
        data object ShowDialog:UIState
    }

    interface  SideEffect{
        data class Toast(val message:String)
    }
    sealed interface Intent{
        data object OpenProfileScreen:Intent
        data class OnCLickDrawerData(
            val data:DrawerData
        ):Intent
        data object OpenScreenNotify:Intent

        data object LogOut:Intent

        data object DialogDismiss:Intent
    }
}