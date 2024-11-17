package uz.madatbek.zoomradcompose.presenter.screens.addcard

import org.orbitmvi.orbit.ContainerHost
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.AddCardData

interface AddCardContract {

    interface  Model:ContainerHost<UIState,SideEffect>{
        fun onEventDispatchers(intent: Intent)
    }
    interface UIState{
        data object Default:UIState
        data object Progress:UIState
        data object Success:UIState
        data object Fail:UIState
    }

    interface SideEffect{
        data class Toast(val message:String):SideEffect
    }

    interface Intent{
        data class AddCard(
            val card:AddCardData
        ):Intent
        data object OnBackScreen:Intent


    }
}