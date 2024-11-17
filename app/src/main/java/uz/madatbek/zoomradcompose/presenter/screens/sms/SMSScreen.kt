package uz.madatbek.zoomradcompose.presenter.screens.sms

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.SingUpUserData
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
import uz.madatbek.zoomradcompose.utils.myLog
import uz.madatbek.zoomradcompose.utils.toPhoneFormat
import java.util.concurrent.TimeUnit

class SMSScreen(private val singUpUserData: SingUpUserData) :Screen {
    @Composable
    override fun Content() {
        val context= LocalContext.current
        val screenModel = getViewModel<SMSVIewModel>()
        ZoomradTheme {

            SMSContent(
                singUpUserData,
                uiState = screenModel.collectAsState().value,
                onEventDispatcher = screenModel::onEventDispatcher
            )
        }
        val sideEffect=screenModel.container.sideEffectFlow.collectAsState(initial = null)

        when(val effect=sideEffect.value){
            is SMSContract.SideEffect.Toast->{
                Toast.makeText(context,effect.massage,Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }
}


@Composable
fun SMSContent(singUpUserData: SingUpUserData,uiState: SMSContract.UIState,onEventDispatcher:(SMSContract.Intent)->Unit) {
    var timeLeft by remember { mutableStateOf(120) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if (!isRunning) {
            timeLeft=120
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft -= 1
            }
            isRunning = true
        }
    }

    val minutes = TimeUnit.SECONDS.toMinutes(timeLeft.toLong())
    val seconds = timeLeft % 60
    val smsCode= remember {
        mutableStateOf("")
    }
    MySurface(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .fillMaxWidth()
                    .padding(horizontal = 128.dp)
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.ic_zoomrad),
                contentDescription = null
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Мы отправили смс с кодом на номер"
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 4.dp),
                text =
                MyShar.getUserLoginData()?.phone?:
                "+998932320322".toPhoneFormat(),
                fontWeight = FontWeight.Bold
            )

            MyTextFieldWithHint(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(top = 16.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                ), textState = smsCode)
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp))
            {
                Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                    androidx.compose.material3.Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text =String.format("%02d:%02d", minutes, seconds)
                    )

                    Row(modifier = Modifier
                        .padding(8.dp)
                        .height(56.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            if (isRunning) {
                                isRunning = false
                                onEventDispatcher(SMSContract.Intent.SingUpUser(data =singUpUserData ))
                            }
                        }
                    ){
                        Card(
                            modifier = Modifier.size(56.dp)
                        ) {
                            Image(
                                modifier = Modifier.padding(16.dp),
                                painter = painterResource(id = R.drawable.ic_phone),
                                contentDescription = null
                            )
                        }
                        androidx.compose.material3.Text(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .align(Alignment.CenterVertically),
                            text = stringResource(id = R.string.transfer_verify_send_cade_again),
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .height(48.dp)
                    .background(
                        color =
                        if (smsCode.value.length == 6) {
                            colorResource(id = R.color.zumrat)
                        } else {

                            Color.Gray
                        }
                    )

                    .clickable {
                        if (smsCode.value.length == 6) {
                            onEventDispatcher(SMSContract.Intent.OpenCreatePinCodeScreenSingUp(sms = smsCode.value))
                        } else {
                            return@clickable
                        }
                    }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Продолжить",
                    color = Color.White
                )
            }

        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldWithHint(modifier: Modifier, textState: MutableState<String>) {

    Card(modifier = modifier) {
        TextField(

            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
            ),
            value = textState.value,
            onValueChange = { newText ->
                if (newText.length>6)return@TextField
                textState.value = newText
                "naw Text=> $textState".myLog()
            },
            placeholder = {
                Text(
                    text = "Введите sms здесь", fontSize = 16.sp, color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent, // Устанавливаем прозрачный цвет контейнера
                unfocusedIndicatorColor = Color.Transparent, // Цвет индикатора, когда поле не в фокусе
                focusedIndicatorColor = Color.Transparent  // Цвет индикатора, когда поле в фокусе
            ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }

}


@Preview
@Composable
fun SMSPreview() {
    SMSContent(uiState = SMSContract.UIState.InitSate, singUpUserData = SingUpUserData("","","","","","")){

    }
}