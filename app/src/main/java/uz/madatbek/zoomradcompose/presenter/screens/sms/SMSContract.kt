package uz.madatbek.zoomradcompose.presenter.screens.sms

import org.orbitmvi.orbit.ContainerHost
import uz.madatbek.zoomradcompose.data.sourse.remote.singIn.SingInUserData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.SingUpUserData
import uz.madatbek.zoomradcompose.presenter.screens.pincode.PinCodeContract

interface SMSContract {

    interface Model:ContainerHost<UIState,SideEffect>{
            fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState{
        data object InitSate:UIState
    }

    sealed interface SideEffect{
        data class Toast(val massage:String):SideEffect
    }
    sealed interface Intent{
        data class OpenCreatePinCodeScreenLogin(
            val sms:String
        ):Intent
        data class OpenCreatePinCodeScreenSingUp(
            val sms:String
        ):Intent
        data class SingUpUser(
            val data:SingUpUserData
            ):Intent
        data class SingInUser(
            val data:SingInUserData
        ):Intent
    }


}

