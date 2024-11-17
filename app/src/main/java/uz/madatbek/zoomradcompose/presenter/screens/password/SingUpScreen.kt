package uz.madatbek.zoomradcompose.presenter.screens.password

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.sudo_pacman.paynet.presenter.screens.fast_payment.components.PhoneInputFastPayment
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
import uz.madatbek.zoomradcompose.utils.myLog

class SingUpScreen : Screen {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        val screenModel = getViewModel<SingUpViewModel>()

        PasswordComponent(
            uiState = screenModel.collectAsState(),
            onEventDispatcher = screenModel::onEventDispatchers
        )

        screenModel.collectSideEffect {
            when (it) {
                is SingUpContract.SideEffect.Toast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordComponent(
    uiState: State<SingUpContract.UIState>,
    onEventDispatcher: (SingUpContract.Intent) -> Unit
) {

    val textState1 = remember { mutableStateOf("") }
    val textState2 = remember { mutableStateOf("") }
    val phoneText = remember { mutableStateOf("") }
    MySurface(
        modifier = Modifier.fillMaxSize()
    ) {
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
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .height(48.dp)
            ) {
                PhoneInputFastPayment(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically),
                    text = phoneText.value,
                    onTextChanged = { newValue ->
                        if (newValue.length <= 9) {
                            phoneText.value = newValue
                        }
                    }

                )
//                Text(
//                    modifier = Modifier.align(Alignment.CenterVertically).padding(start = 8.dp),
//                    text = "+998",
//                    fontWeight = FontWeight.Bold, // жирный стиль шрифта
//                    color = Color.Black,
//                    fontSize = 18.sp
//                )
//                TextField(
//                    modifier = Modifier.fillMaxSize().align(Alignment.CenterVertically),
//                    value = phoneText.value,
//                    onValueChange = { newValue ->
//                        if (newValue.length <= 9) {
//                            phoneText.value = newValue
//                        }
//                    },
//                    textStyle = TextStyle(
//                        fontWeight = FontWeight.Bold, // жирный стиль шрифта
//                        color = Color.Black, // цвет текста
//                        fontSize = 18.sp
//                    ),
//                    singleLine = true,
//                    colors = TextFieldDefaults.textFieldColors(
//                        containerColor = Color.Transparent, // Фон поля
//                        focusedIndicatorColor = Color.Transparent, // Цвет линии при фокусе
//                        unfocusedIndicatorColor = Color.Transparent, // Цвет линии без фокуса
//                        disabledIndicatorColor = Color.Transparent // Цвет линии когда элемент не активен
//                    ),
//                    keyboardOptions = KeyboardOptions.Default.copy(
//                        keyboardType = KeyboardType.Phone,
//                        imeAction = ImeAction.Done
//                    ),
//                    keyboardActions = KeyboardActions(onDone = { }),
//                    visualTransformation =PhoneMaskTransformation
//                )
            }
            MyTextFieldWithHint(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant), textState1,
                stringResource(id = R.string.passvord_screen_create_password)
            )
            if (textState1.value.isNotEmpty() && textState1.value.length < 6) {
                PasswordWorning(stringResource(id = R.string.passvord_screen_the_password_must_contain_at_least_6_characters))
            }

            MyTextFieldWithHint(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant), textState2,
                stringResource(id = R.string.passvord_screen_repeat_your_password)
            )
            if (textState2.value.isNotEmpty() && textState1.value.length <= textState2.value.length && textState1.value != textState2.value) {
                PasswordWorning(stringResource(id = R.string.passvord_screen_password_mismatch))
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .height(48.dp)
                    .background(
                        color =
                        if (phoneText.value.length == 9 && textState1.value.length > 5 && textState1.value == textState2.value) {
                            colorResource(id = R.color.zumrat)
                        } else {

                            Color.Gray
                        }
                    )

                    .clickable {
                        if (phoneText.value.length == 9 && textState1.value.length > 5 && textState1.value == textState2.value) {
                            onEventDispatcher(
                                SingUpContract.Intent.OpenCreatePinCodeScreen(
                                    phone = "+998" + phoneText.value,
                                    password = textState1.value
                                )
                            )
                        } else {
                            return@clickable
                        }
                    }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.passvord_screen_continue),
                    color = Color.White
                )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
                    .clickable(
                        onClick = {
                            onEventDispatcher(SingUpContract.Intent.OpenSingInScreen)
                        },

                        ),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.passvord_screen_have_an_account),
                fontSize = 12.sp,
                color = colorResource(id = R.color.zumrat)
            )

        }
    }
}

@Preview
@Composable
fun PasswordPreview() {
    ZoomradTheme {
        PasswordComponent(remember { mutableStateOf(SingUpContract.UIState.InitState) }) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldWithHint(modifier: Modifier, textState: MutableState<String>, hint: String) {
    val isVisibleTxt = remember {
        mutableStateOf(false)
    }
    MySurface {

        Box(modifier = modifier) {
            androidx.compose.material3.TextField(

                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                value = textState.value,
                onValueChange = { newText ->
                    textState.value = newText

                    "naw Text=> $textState".myLog()
                },
                placeholder = {
                    Text(
                        text = hint, fontSize = 16.sp, color = Color.Gray,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .height(48.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent, // Устанавливаем прозрачный цвет контейнера
                    unfocusedIndicatorColor = Color.Transparent, // Цвет индикатора, когда поле не в фокусе
                    focusedIndicatorColor = Color.Transparent  // Цвет индикатора, когда поле в фокусе
                ),
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),

                visualTransformation = if (isVisibleTxt.value) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }

            )

            Icon(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .width(16.dp)
                    .height(16.dp)
                    .align(Alignment.CenterEnd)
                    .clickable(
                        onClick = {
                            isVisibleTxt.value = !isVisibleTxt.value
                        }
                    ),
                painter = painterResource(id = if (isVisibleTxt.value) R.drawable.eyes else R.drawable.eyes2),
                contentDescription = null,
                tint = colorResource(id = R.color.zumrat)
            )
        }
    }

}

@Composable
fun PasswordWorning(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        text = text,
        color = colorResource(id = R.color.zumrat_exception),
        textAlign = TextAlign.Center,
        fontSize = 12.sp
    )
}