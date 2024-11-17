package uz.madatbek.zoomradcompose.presenter.screens.mycards

import org.orbitmvi.orbit.ContainerHost
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.CardData

interface MyCardsContract {

    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val data: List<CardData> = listOf(),
    )

    interface SideEffect {
        data class Toast(val message: String) : SideEffect
    }

    interface Intent {
        object OnCLickBack : Intent
        object OpenAddScreen : Intent
        data class OnClickCardData(
            val data:CardData
        ):Intent
    }

}
