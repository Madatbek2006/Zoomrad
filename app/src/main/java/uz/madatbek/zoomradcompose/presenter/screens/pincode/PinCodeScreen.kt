package uz.madatbek.zoomradcompose.presenter.screens.pincode

import android.content.Context
import android.content.Intent
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.Settings.ACTION_BIOMETRIC_ENROLL
import android.provider.Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.biometric.BiometricManager
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.LoginData
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ThemeType
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
import uz.madatbek.zoomradcompose.utils.BiometricPromptManager
import uz.madatbek.zoomradcompose.utils.myLog
import kotlin.math.roundToInt
import kotlin.reflect.KFunction1
import androidx.compose.runtime.remember as remember
import androidx.compose.runtime.rememberCoroutineScope as rememberCoroutineScope1

class PinCodeScreen : Screen {
    @Composable
    override fun Content() {

        val context= LocalContext.current
        val activity=context as FragmentActivity
        val promptManager by lazy {
            BiometricPromptManager(context = context, activity = activity)
        }
        ZoomradTheme {
            val screenModel = getViewModel<PinCodeModel>()
            val biometricResult by promptManager.promptResults.collectAsState(
                initial = null
            )
            val enrollLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartActivityForResult(),
                onResult = {
                    println("Activity result: $it")
                }
            )
            LaunchedEffect(biometricResult){
                if (biometricResult is BiometricPromptManager.BiometricResult.AuthenticationNotSet) {
                    if (Build.VERSION.SDK_INT >= 30) {
                        val enrollIntent = Intent(ACTION_BIOMETRIC_ENROLL).apply {
                            putExtra(
                                EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL
                            )
                        }
                        enrollLauncher.launch(enrollIntent)
                    }
                }
            }
            biometricResult?.let { result ->
                when (result) {
                    is BiometricPromptManager.BiometricResult.AuthenticationError -> {
                        "BiometricResult.AuthenticationError".myLog()
                        result.error
                    }
                    BiometricPromptManager.BiometricResult.AuthenticationFailed -> {
                        "Authentication failed".myLog()
                    }

                    BiometricPromptManager.BiometricResult.AuthenticationNotSet -> {
                        "Authentication not set".myLog()
                    }

                    BiometricPromptManager.BiometricResult.AuthenticationSuccess -> {
                        screenModel.onEventDispatcher(PinCodeContract.Intent.OpenMainScreen)
                        "Authentication success".myLog()
                    }

                    BiometricPromptManager.BiometricResult.FeatureUnavailable -> {
                        "Feature unavailable".myLog()
                    }

                    BiometricPromptManager.BiometricResult.HardwareUnavailable -> {
                        "Hardware unavailable".myLog()
                    }
                }
            }



            LaunchedEffect(key1 = null, block = {

                promptManager.showBiometricPrompt(
                    title = "Sample prompt",
                    description = "Sample prompt description"
                )
            })
            PinCodeComponent(
                uiState = screenModel.collectAsState().value,
                onEventDispatcher ={
                    screenModel.onEventDispatcher(it)
                }
            )
        }
    }
}

@Composable
fun PinCodeComponent(
    uiState: PinCodeContract.UIState,
    onEventDispatcher: (PinCodeContract.Intent)-> Unit
) {
    val context= LocalContext.current
    val offsetX = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope1()
    val density = LocalDensity.current
    val vibrationAmplitude = with(density) { 10.dp.toPx() }

    var pinCode by remember { mutableStateOf("") }
    MySurface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            Image(
                modifier = Modifier
                    .padding(bottom = 64.dp)
                    .padding(horizontal = 128.dp)
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.ic_zoomrad),
                contentDescription = null
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            Text(
                text = stringResource(id = R.string.pincode_screen_enter_your_PIN),
                fontWeight = FontWeight.Bold
            )
            PinCodeNumb(modifier = Modifier.fillMaxWidth(), pinCode = pinCode,offsetX)
            Text(
                text = stringResource(id = R.string.pincode_screen_reset_PIN),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable(
                        onClick = {
                            pinCode = ""
                            onEventDispatcher(PinCodeContract.Intent.LogOut)
                        },

                        )
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            NumberPad(
                modifier = Modifier.fillMaxSize(), onNumberSelected = {
                    if (pinCode.length > 3) {
                    } else {
                        pinCode += it
                        if (pinCode.length == 4) {
                            if (pinCode == MyShar.getUserLoginData()!!.pinCode) {
                                onEventDispatcher(PinCodeContract.Intent.OpenMainScreen)
                            }else{
                                vibrate(context,500)
                                coroutineScope.launch {
                                    repeat(5) {
                                        offsetX.animateTo(
                                            targetValue = vibrationAmplitude,
                                            animationSpec = tween(durationMillis = 50, easing = LinearEasing)
                                        )
                                        offsetX.animateTo(
                                            targetValue = -vibrationAmplitude,
                                            animationSpec = tween(durationMillis = 50, easing = LinearEasing)
                                        )
                                    }
                                    offsetX.animateTo(
                                        targetValue = 0f,
                                        animationSpec = tween(durationMillis = 50, easing = LinearEasing)
                                    )
                                    pinCode=""
                                }

                            }
                        } else {

                        }
                    }
                },
                onClear = {
                    if (pinCode.isNotEmpty()) {
                        pinCode = pinCode.substring(0, pinCode.length - 1)
                    }
                }
            )
        }
    }

}
fun vibrate(context: Context, duration: Long) {
    val vibrator = ContextCompat.getSystemService(context, Vibrator::class.java)
    vibrator?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            it.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            it.vibrate(duration)
        }
    }
}



@Composable
fun PinCodeNumb(
    modifier: Modifier,
    pinCode: String,
    offsetX:Animatable<Float,AnimationVector1D>
) {
    Column(
        modifier = modifier
            .offset { IntOffset(offsetX.value.roundToInt(), 0) }
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(modifier = Modifier.padding(top = 8.dp)) {
            PinCodeCircle(bool = pinCode.length > 0)
            PinCodeCircle(bool = pinCode.length > 1)
            PinCodeCircle(bool = pinCode.length > 2)
            PinCodeCircle(bool = pinCode.length > 3)
        }
    }
}

@Composable
fun PinCodeCircle(bool: Boolean) {
    Spacer(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(300.dp))
            .border(
                width =
                if (bool)
                    0.dp
                else {
                    1.dp
                },
                color = if (bool)
                    colorResource(id = R.color.zumrat)
                else if (MyShar.getTheme() == ThemeType.LIGHT) Color.Black else if (MyShar.getTheme() == ThemeType.SYSTEM && !isSystemInDarkTheme()) Color.Black else Color.White,
                shape = CircleShape
            )
            .background(
                color = if (bool)
                    colorResource(id = R.color.zumrat)
                else androidx.compose.material3.MaterialTheme.colorScheme.background
            )
            .padding(8.dp)
    )
}

@Composable
fun NumberPad(modifier: Modifier, onNumberSelected: (String) -> Unit, onClear: () -> Unit) {
    Column(modifier = modifier) {
        (1..3).forEach { row ->
            Row(modifier = Modifier
                .weight(1f)
            ) {
                (0..2).forEach { column ->
                    NumberButton(
                        (row * 3 - 2 + column).toString(),
                        onNumberSelected,
                        Modifier
                            .weight(1f)
                    )
                }
            }
        }
        Row(modifier = Modifier.weight(1f)) {
            Spacer(modifier = Modifier.weight(1f))
            NumberButton("0", onNumberSelected,
                Modifier
                    .weight(1f)
                    .fillMaxHeight())
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clickable(
                    onClick = { onClear() },
                    indication = rememberRipple(
                        bounded = false,
                        radius = 50.dp
                    ), // Рипл-эффект без границ
                    interactionSource = remember { MutableInteractionSource() } // Источник взаимодействия
                )

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clear),
                    contentDescription = "Backspace",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun NumberButton(number: String, onNumberSelected: (String) -> Unit, modifier: Modifier) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .aspectRatio(1f)
            .clickable(
                onClick = { onNumberSelected(number) },
                indication = rememberRipple(
                    bounded = false,
                    radius = 50.dp
                ),
                interactionSource = remember { MutableInteractionSource() } // Источник взаимодействия
            ),



    ) {
        Text(
            text = number, fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewPinCodeScreen() {
    PinCodeComponent(PinCodeContract.UIState.InitState, onEventDispatcher = {})
}

