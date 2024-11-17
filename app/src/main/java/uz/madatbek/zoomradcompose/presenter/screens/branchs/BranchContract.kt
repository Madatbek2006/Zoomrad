package uz.madatbek.zoomradcompose.presenter.screens.branchs

import org.orbitmvi.orbit.ContainerHost

interface BranchContract {
    interface Model:ContainerHost<UIState, SideEffect>{
        fun onEventDispatchers(intent: Intent)
    }
    interface UIState{
        data class InitUIState(
            val data:List<MarkerData>
        ): UIState
        
        data class RegionType(
            val data:List<String>
        ): UIState
    }
    interface SideEffect{
        data class Toast(val message:String): SideEffect
    }
    interface Intent{
        data object OnClickBack: Intent
        data class ClickType(
            val name:String
        ): Intent
    }
}