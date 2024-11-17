package uz.madatbek.zoomradcompose.presenter.screens.transferverify

import org.orbitmvi.orbit.ContainerHost
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferRequest
import uz.madatbek.zoomradcompose.presenter.screens.transfer.TransferUIData
import uz.madatbek.zoomradcompose.utils.SuccessEnum

interface TransferVerifyContract {
    interface Model: ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        var successComponent: SuccessEnum = SuccessEnum.NONE
    )

    interface SideEffect{
        data class Toast(val message:String):SideEffect
    }

    interface Intent{
        data class TransferVerify(
            val sms:String
        ):Intent
        data object OnCLickBack:Intent
        data class Transfer(
            val request: TransferRequest
        ):Intent
        data class OpenCheckScreen(
            val data: TransferUIData
        ):Intent
    }
}


