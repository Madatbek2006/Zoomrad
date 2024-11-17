package uz.madatbek.zoomradcompose.presenter.screens.main

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.LoginData
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.presenter.screens.history.HistoryScreen
import uz.madatbek.zoomradcompose.presenter.screens.monitoring.MonitoringScreen
import uz.madatbek.zoomradcompose.presenter.screens.login.LoginScreen
import uz.madatbek.zoomradcompose.presenter.screens.notify.NotifyScreen
import uz.madatbek.zoomradcompose.presenter.screens.profile.ProfileScreen
import uz.madatbek.zoomradcompose.presenter.screens.settings.SettingScreen
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import uz.madatbek.zoomradcompose.utils.postUiState
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(
    private val navigator: AppNavigator
):ViewModel(),ScreenModel,MainContract.Model {
    override val container=container<MainContract.UIState, MainContract.SideEffect>(MainContract.UIState.Default)

    var onCLickHelpItem:(()->Unit)?=null
    override fun onEventDispatcher(intent: MainContract.Intent) =intent{

        when(intent){
            MainContract.Intent.OpenProfileScreen->{ navigator.navigateTo(ProfileScreen()) }
            is MainContract.Intent.OnCLickDrawerData->{
                when(intent.data.name){

                    R.string.drawer_profile_txt->{
                        navigator.navigateTo(ProfileScreen())
                    }
                    R.string.drawer_settings_txt->{
                        navigator.navigateTo(SettingScreen())
                    }
                    R.string.drawer_history_txt->{
                        navigator.navigateTo(HistoryScreen())
                    }
                    R.string.drawer_monitoring_txt->{
                        navigator.navigateTo(MonitoringScreen())
                    }
                    R.string.drawer_help_txt->{
                         onCLickHelpItem?.invoke()
                    }
                    R.string.drawer_share_txt->{

                    }
                    R.string.drawer_exit_txt->{
                        postUiState(MainContract.UIState.ShowDialog)
                    }
                }
            }
            MainContract.Intent.OpenScreenNotify->{ navigator.navigateTo(NotifyScreen()) }

            MainContract.Intent.LogOut->{
                postUiState(MainContract.UIState.Default)
                MyShar.setUserLoginData(LoginData(null,null,null))
                MyShar.setAusUser(false)
                navigator.replaceAll(LoginScreen())
            }
            MainContract.Intent.DialogDismiss->{
                postUiState(MainContract.UIState.Default)
            }
        }


    }
}