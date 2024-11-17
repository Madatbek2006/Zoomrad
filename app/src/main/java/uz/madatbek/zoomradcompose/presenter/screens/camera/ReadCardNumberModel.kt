package uz.madatbek.zoomradcompose.presenter.screens.camera

//import androidx.lifecycle.ViewModel
//import dagger.hilt.android.lifecycle.HiltViewModel
//import org.orbitmvi.orbit.syntax.simple.intent
//import org.orbitmvi.orbit.viewmodel.container
//import uz.madatbek.zoomradcompose.presenter.screens.camera.ReadCardNumberContract
//import uz.madatbek.zoomradcompose.presenter.screens.camera.ReadCardNumberDirection
//import javax.inject.Inject

//@HiltViewModel
//class ReadCardNumberModel @Inject constructor(
//    private val direction: ReadCardNumberDirection,
//) : ViewModel(), ReadCardNumberContract.Model {
//    override fun onEventDispatcher(intent: ReadCardNumberContract.Intent) {
//        when (intent) {
//            ReadCardNumberContract.Intent.Back -> intent { direction.back() }
//            is ReadCardNumberContract.Intent.BackWithData -> intent {
//                direction.backWithData(
//                    intent.cardNumber,
//                    intent.expirationDate
//                )
//            }
//        }
//    }
//
//    override val container =
//        container<ReadCardNumberContract.UIState, ReadCardNumberContract.SideEffect>(
//            ReadCardNumberContract.UIState)
//}