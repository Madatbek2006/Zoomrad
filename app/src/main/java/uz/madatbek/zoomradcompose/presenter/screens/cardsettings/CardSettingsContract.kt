package uz.madatbek.zoomradcompose.presenter.screens.cardsettings

import org.orbitmvi.orbit.ContainerHost
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.AddCardData
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.UpdateCard

interface CardSettingsContract {

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
        data class UpdateCard(
            val card:uz.madatbek.zoomradcompose.data.sourse.remote.cards.UpdateCard
        ):Intent
        data object OnBackScreen:Intent


    }
}