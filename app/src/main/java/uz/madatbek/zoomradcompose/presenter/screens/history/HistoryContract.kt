package uz.madatbek.zoomradcompose.presenter.screens.history

import org.orbitmvi.orbit.ContainerHost
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferDetail

interface HistoryContract {
    interface Model: ContainerHost<UIState, SideEffect> {
        fun onEventDispatchers(intent: Intent)
    }
    interface UIState{
        data class Monitoring(
            val data:List<TransferDetail>
        ): UIState
    }


    interface SideEffect{
        data class Toast(val message:String): SideEffect
    }

    interface Intent{
        data object OnClickBack: Intent
    }
}