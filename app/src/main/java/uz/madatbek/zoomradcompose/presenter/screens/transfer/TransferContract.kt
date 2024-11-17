package uz.madatbek.zoomradcompose.presenter.screens.transfer

import org.orbitmvi.orbit.ContainerHost
import uz.madatbek.zoomradcompose.data.model.CheckData
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.CardData

interface TransferContract {

    interface Model:ContainerHost<UIState,SideEffect>{

        fun onEventDispatchers(intent: Intent)
    }


    interface UIState{
        data object Default:UIState
        data object Progress:UIState
    }
    interface SideEffect{
        data class Toast(val message:String):SideEffect
    }
    interface Intent{
        data object OnClickBack:Intent
        data class Transfer(
            val data:TransferUIData
        ):Intent
    }
}