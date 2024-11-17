package uz.madatbek.zoomradcompose.presenter.screens.offers

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class OffersViewModel @Inject constructor(
    private val navigator: AppNavigator
):ViewModel(),ScreenModel,OffersContract.Model {


    override val container = container<OffersContract.UIState, OffersContract.SideEffect>(OffersContract.UIState.InitState)


    override fun onEventDispatcher(intent: OffersContract.Intent) =intent{
        when(intent){
            OffersContract.Intent.onClickBack->{
                navigator.back()
            }
        }
    }
}


