package uz.madatbek.zoomradcompose.presenter.screens.viewcards

import org.orbitmvi.orbit.ContainerHost
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.CardData
import uz.madatbek.zoomradcompose.presenter.screens.addcard.AddCardContract

interface ViewCardsContract {

    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val progress:Boolean = false,
        val data: List<CardData> = listOf(),
        val dialog:Boolean = false
    )

    interface SideEffect {
        data class Toast(val message: String) : SideEffect
    }

    interface Intent {
        object OnCLickBack : Intent
        object OpenAddScreen : Intent
        data class BottomSheetAction(
            val resId: Int,
            val data:CardData
        ) : Intent

        data class DeleteCard(
            val id: String
        ) : Intent
        data object DismissDialog:Intent
    }

}
