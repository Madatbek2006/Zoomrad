package uz.madatbek.zoomradcompose.presenter.screens.main.pages.transver

import org.orbitmvi.orbit.ContainerHost
import uz.madatbek.zoomradcompose.data.model.TransferItem1Data
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.CardData
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.GetCardOwnerByPanData
import uz.madatbek.zoomradcompose.presenter.screens.transfer.TransferUIData
import uz.madatbek.zoomradcompose.presenter.screens.viewcards.ViewCardsContract

interface TransferContract {

    interface Model:ContainerHost<UIState,SideEffect>{
        fun onEventDispatchers(intent: Intent)
    }
    interface UIState{
        data object InitUIState:UIState
        data class Text(val txt:String,val isSuccess:Boolean):UIState
        data class LoadAllCards(
            val data:List<CardData>
        ): UIState
    }

    interface SideEffect{
        data class Toast(val message:String):SideEffect
    }

    interface Intent{
        data object OpenAddScreen:Intent
        data class GetCardByPan(
            val pan:GetCardOwnerByPanData
        ):Intent
        data class OpenTransferScreen(
            val data: TransferUIData,
        ):Intent
        data class OnClickItem1(
            val data: TransferItem1Data
        ):Intent
    }

}