package uz.madatbek.zoomradcompose.presenter.screens.createpin

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.LoginData
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.presenter.screens.pincode.PinCodeModel
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ThemeType
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme

class CreatePinCodeScreen:Screen{

    @Composable
    override fun Content() {
        val screenModel = getViewModel<CreatePinCodeModel>()
           PinCodeComponent(
                uiState = screenModel.collectAsState(),
                onEventDispatcher = screenModel::onEventDispatcher
           )
    }

}





@Composable
fun PinCodeComponent(
    uiState: State<CreatePinCodeContract.UIState>,
    onEventDispatcher:(CreatePinCodeContract.Intent)-> Unit
) {

    var pinCode by remember { mutableStateOf("") }
    var newPinCode by remember { mutableStateOf("") }
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
                text = stringResource(uiState.value.txt),
                fontWeight = FontWeight.Bold
            )
            PinCodeNumb(modifier = Modifier.fillMaxWidth(), pinCode = pinCode)

            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            NumberPad(
                modifier = Modifier.fillMaxSize(), onNumberSelected = {
                    if (pinCode.length > 3) {
                    } else {
                        pinCode += it
                        if (pinCode.length == 4) {

                            if (newPinCode.isEmpty()) {
                                newPinCode = pinCode
                                pinCode = ""
                                uiState.value.txt = R.string.create_pincode_reenter_your_PIN_code
                            } else {
                                if (pinCode == newPinCode) {
                                    MyShar.setUserLoginData(
                                        LoginData(
                                            MyShar.getUserLoginData()!!.phone,
                                            pinCode,
                                            MyShar.getUserLoginData()!!.password
                                        )
                                    )
                                    onEventDispatcher(CreatePinCodeContract.Intent.OpenMainScreen)
                                } else {
                                    pinCode = ""
                                    newPinCode = ""
                                    uiState.value.txt = R.string.create_pincode_try_again
                                }
                            }
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

@Composable
fun PinCodeNumb(
    modifier: Modifier,
    pinCode: String
) {
    Column(
        modifier = modifier,
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
                }, color = if (bool)
                    colorResource(id = R.color.zumrat)
                else  if (MyShar.getTheme() == ThemeType.LIGHT) Color.Black else if (MyShar.getTheme() == ThemeType.SYSTEM && !isSystemInDarkTheme()) Color.Black else Color.White,
                shape = CircleShape
            )
            .background(
                color = if (bool)
                    colorResource(id = R.color.zumrat)
                else Color.Unspecified
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
                .background(Color.Transparent)) {
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
    PinCodeComponent(uiState = remember { mutableStateOf(CreatePinCodeContract.UIState(R.string.create_pincode_reenter_your_PIN_code)) }, onEventDispatcher = {

    })
}

