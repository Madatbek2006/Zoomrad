package uz.madatbek.zoomradcompose.presenter.screens.main.pages.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.domain.HomeRepository
import uz.madatbek.zoomradcompose.presenter.screens.addcard.AddCardScreen
import uz.madatbek.zoomradcompose.presenter.screens.cashback.СashbackScreen
import uz.madatbek.zoomradcompose.presenter.screens.credits.CreditsScreen
import uz.madatbek.zoomradcompose.presenter.screens.deposits.DepositsScreen
import uz.madatbek.zoomradcompose.presenter.screens.exchange_rates.ExchangeRatesScreen
import uz.madatbek.zoomradcompose.presenter.screens.moneybox.MoneyBoxScreen
import uz.madatbek.zoomradcompose.presenter.screens.offers.OffersScreen
import uz.madatbek.zoomradcompose.presenter.screens.viewcards.ViewCardsScreen
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import uz.madatbek.zoomradcompose.utils.postUiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: AppNavigator,
    private val homeRepository: HomeRepository
):ViewModel(),ScreenModel,HomeContract.Model {
    override val container =container<HomeContract.UIState, HomeContract.SideEffect>(HomeContract.UIState(0))



    override fun onEventDispatchers(intent: HomeContract.Intent) =intent{
        when(intent){
            HomeContract.Intent.OpenAddScreen->{
                navigator.navigateTo(AddCardScreen())
            }
            HomeContract.Intent.OpenViewCardsScreen->{
                navigator.navigateTo(ViewCardsScreen())
            }
            is HomeContract.Intent.OnClickItem1->{
                when(intent.data.name){
                    R.string.home_page_item1_ceshbek->navigator.navigateTo(СashbackScreen())
                    R.string.home_page_item1_kopilka->navigator.navigateTo(MoneyBoxScreen())
                    R.string.home_page_item1_vkladi->navigator.navigateTo(DepositsScreen())
                    R.string.home_page_item1_credit->navigator.navigateTo(CreditsScreen())
                }
            }
            is HomeContract.Intent.OpenWalute->{
                navigator.navigateTo(ExchangeRatesScreen(intent.data))
            }
            is HomeContract.Intent.OpenOffers->{
                navigator.navigateTo(OffersScreen(intent.data))
            }
        }
    }

    fun getTotalBalance()=intent{
       homeRepository.getTotalBalance().apply {
           onSuccess {
               postUiState(HomeContract.UIState(it.totalBalance))
           }
           onFailure {

           }
       }
    }
}

/*

val homeItem1Data = arrayListOf(
    HomeItem1Data(icon = R.drawable.ic_cashback_icon, cost = 0, currency = R.string.home_page_item1_ball, name =R.string.home_page_item1_ceshbek),
    HomeItem1Data(icon = R.drawable.ic_chest, cost = 0, currency = R.string.home_page_item1_sum, R.string.home_page_item1_kopilka),
    HomeItem1Data(icon = R.drawable.ic_deposits, cost = 0, currency = R.string.home_page_item1_sum, R.string.home_page_item1_vkladi),
    HomeItem1Data(icon = R.drawable.ic_credit_time, cost = 0, currency = R.string.home_page_item1_sum, R.string.home_page_item1_credit),
)
*/