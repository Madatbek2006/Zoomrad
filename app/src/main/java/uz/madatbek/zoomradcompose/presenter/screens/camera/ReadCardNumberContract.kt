package uz.madatbek.zoomradcompose.presenter.screens.camera
//
//import org.orbitmvi.orbit.ContainerHost
//
//interface ReadCardNumberContract {
//    interface Model : ContainerHost<UIState, SideEffect> {
//        fun onEventDispatcher(intent: Intent)
//    }
//
//    data object UIState
//
//    data object SideEffect
//
//    sealed interface Intent {
//        data class BackWithData(
//            var cardNumber: String,
//            var expirationDate: String
//        ) : Intent
//
//        data object Back : Intent
//    }
//}