package uz.madatbek.zoomradcompose.presenter.screens.history

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.navigator.tab.TabNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val navigator: AppNavigator
):ViewModel(),ScreenModel,HistoryContract.Model {
    override val container=container<HistoryContract.UIState, HistoryContract.SideEffect>(HistoryContract.UIState.Monitoring(
        emptyList()
    ))

    override fun onEventDispatchers(intent: HistoryContract.Intent) =intent{
        when(intent){
            HistoryContract.Intent.OnClickBack->{
                navigator.back()
            }
        }
    }
}