package uz.madatbek.zoomradcompose.presenter.screens.profile

import org.orbitmvi.orbit.ContainerHost
import uz.madatbek.zoomradcompose.data.UserRequest
import uz.madatbek.zoomradcompose.data.sourse.remote.user.UserResponse

interface ProfileContract {


    interface Model:ContainerHost<UIState,SideEffect>{
        fun onEventDispatchers(intent: Intent)
    }

    sealed interface UIState{
        data class InitUIState(
            val data:UserResponse
        ):UIState
        data object Success:UIState
        data object Progress:UIState
    }

    sealed interface SideEffect{
        data class Toast(val message: String): SideEffect
    }

    sealed interface Intent {
        data class OnClickCheck(
            val user:UserRequest
        ): Intent
        data object OnCLickBack:Intent
    }

}


/*
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
        object OpenMainScreen: Intent
    }
 */