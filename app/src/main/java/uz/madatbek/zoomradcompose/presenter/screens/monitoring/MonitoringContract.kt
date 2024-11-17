package uz.madatbek.zoomradcompose.presenter.screens.monitoring

import org.orbitmvi.orbit.ContainerHost
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferDetail

interface MonitoringContract {
    interface Model:ContainerHost<UIState, SideEffect>{
        fun onEventDispatchers(intent: Intent)
    }
    data class UIState(
            val data:List<TransferDetail>,
            var isLoad:Boolean
    )


    interface SideEffect{
        data class Toast(val message:String): SideEffect
    }

    interface Intent{
        data object OnClickBack: Intent
        data object NextLoadData: Intent
    }
}