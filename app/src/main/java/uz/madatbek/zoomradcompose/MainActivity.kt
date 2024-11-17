package uz.madatbek.zoomradcompose

import android.content.res.Configuration
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.sudo_pacman.paynet.utils.network_status.ConnectivityLiveData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.presenter.screens.login.LoginScreen
import uz.madatbek.zoomradcompose.presenter.screens.main.MainScreen
import uz.madatbek.zoomradcompose.presenter.screens.main.MainScreenStatus
import uz.madatbek.zoomradcompose.presenter.screens.splash.SplashScreen
import uz.madatbek.zoomradcompose.ui.components.NetworkDialog
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
import uz.madatbek.zoomradcompose.utils.myLog
import uz.madatbek.zoomradcompose.utils.navigation.NavigationHandler
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : FragmentActivity() {


    private var isHaveNetworkListener:((Boolean)->Unit)?=null

    @Inject
    lateinit var navigationHandler: NavigationHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        "onCreate".myLog("TTT onCreate")
        super.onCreate(savedInstanceState)
        MyShar.init(this)
        setContent {
            var showDialog by remember {
                mutableStateOf(false)
            }
            MainScreenStatus.isComposition.onEach {
                if (it){
                    NetworkStatus.hasNetwork.onEach{
                        showDialog=!it
                        "showDialog=> $showDialog".myLog()
                    }.launchIn(lifecycleScope)
                }
            }.launchIn(lifecycleScope)
            ZoomradTheme{
                Navigator(screen = SplashScreen()) { navigator ->
                    navigationHandler.navigationStack
                        .onEach { it.invoke(navigator) }
                        .launchIn(lifecycleScope)
                    CurrentScreen()
                }
                NetworkDialog(showDialog = showDialog,
                    onDismiss = {
                                showDialog=false
                    },
                    onConfirm = {
//                        showDialog=false
                    }
                )
            }

        }
        val connectivityLiveData = ConnectivityLiveData(this)

        connectivityLiveData.observe(this) { networkState ->

//            isHaveNetworkListener?.invoke(networkState.isConnected)
//            MyShar.setIsHaveNetwork(networkState.isConnected)
            if (networkState.isConnected) {
//
                NetworkStatus.hasNetwork.tryEmit(true)
//                "networkState connected".myLog()
//
            } else {
                NetworkStatus.hasNetwork.tryEmit(false)
//                "networkState Disconnected".myLog()
//                // Disconnected logic
            }
        }
    }


    override fun onResume() {




        super.onResume()
    }

}

object NetworkStatus{
    val hasNetwork=MutableStateFlow(false)
}


