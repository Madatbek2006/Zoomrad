package uz.madatbek.zoomradcompose.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import uz.madatbek.zoomradcompose.ui.components.MySurface

@Composable
fun LoadingComponent(modifier: Modifier=Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("loader.json"))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    MySurface {
        Box(modifier = modifier) {
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = modifier
                    .align(Alignment.Center)
                    .fillMaxSize(),
            )
        }
    }
}