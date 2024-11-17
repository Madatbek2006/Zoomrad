package uz.madatbek.zoomradcompose.presenter.screens.main.pages.home

import org.orbitmvi.orbit.ContainerHost
import uz.madatbek.zoomradcompose.data.model.HomeItem1Data
import uz.madatbek.zoomradcompose.data.model.HomeItem2Data
import uz.madatbek.zoomradcompose.data.model.HomeItem3Data

interface HomeContract {

    interface Model:ContainerHost<UIState,SideEffect>{
        fun onEventDispatchers(intent: Intent)
    }

    data class UIState(
        val totalBalance:Long
    )


    interface SideEffect{
        data class Toast(val message:String)
    }

    interface Intent{
        data object OpenAddScreen:Intent
        data object OpenViewCardsScreen:Intent
        data class OnClickItem1(
            val data:HomeItem1Data
        ):Intent
        data class OpenWalute(
            val data:List<HomeItem3Data>
        ):Intent
        data class OpenOffers(
            val data:List<HomeItem2Data>
        ):Intent
    }

}