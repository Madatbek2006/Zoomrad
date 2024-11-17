package uz.madatbek.zoomradcompose.presenter.screens.branchs

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.domain.DataRepository
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class BranchViewModel @Inject constructor(
    private val navigator: AppNavigator,
    private val repository: DataRepository
):ViewModel(),ScreenModel, BranchContract.Model {


    val regionData=listOf("Филиалы","Центры К.О","Банкоматы","Обменные пункты")
    override val container=container<BranchContract.UIState, BranchContract.SideEffect>(
        BranchContract.UIState.InitUIState(
            repository.getLocationsType(regionData[0])
        ),
    )



    override fun onEventDispatchers(intent: BranchContract.Intent) =intent{
        when(intent){
            BranchContract.Intent.OnClickBack ->{
                navigator.back()
            }
            is BranchContract.Intent.ClickType ->{
                reduce {
                    BranchContract.UIState.InitUIState(
                        data = repository.getLocationsType(intent.name)
                    )
                }
            }
        }
    }


}