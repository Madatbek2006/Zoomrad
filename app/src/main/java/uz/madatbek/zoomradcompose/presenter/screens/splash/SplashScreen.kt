package uz.madatbek.zoomradcompose.presenter.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import org.orbitmvi.orbit.compose.collectAsState
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ThemeType
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
import uz.madatbek.zoomradcompose.utils.setLocale

class SplashScreen : Screen {


    @Composable
    override fun Content() {
        val context= LocalContext.current
        val screenModel: SplashContract.Model = getViewModel<SplashModel>()
        ZoomradTheme {
            SplashContent(
                uiState = screenModel.collectAsState().value,
            ) {
              screenModel.onEventDispatcher(SplashContract.Intent.NextScreen)
            }
        }

        context.setLocale(MyShar.getLanguage())
    }
}

@Composable
fun SplashContent(
    uiState: SplashContract.UIState,
    onEventDispatchers: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset(
        if (MyShar.getTheme()==ThemeType.LIGHT)"splash_white.json" else
            "splash_night.json"
    ))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1
    )
    var modifier by remember {
        mutableStateOf( Modifier)
    }
    LaunchedEffect(key1 = null){
        modifier.fillMaxSize()
        if (MyShar.getTheme()==ThemeType.LIGHT){
            modifier.background(Color.White)
        }
    }
    MySurface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = modifier
        ) {
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize(),
            )
            LaunchedEffect(progress) {
                if (progress == 1f) {
                    onEventDispatchers()
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
}
