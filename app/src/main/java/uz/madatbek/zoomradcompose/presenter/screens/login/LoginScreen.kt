package uz.madatbek.zoomradcompose.presenter.screens.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SnapshotMutationPolicy
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.hilt.getViewModel
import com.sudo_pacman.paynet.presenter.screens.fast_payment.components.PhoneInputFastPayment
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.LoginData
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.presenter.screens.password.PasswordWorning
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
import uz.madatbek.zoomradcompose.utils.myLog

class LoginScreen : Screen {


    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val context= LocalContext.current
        val screenModel = getViewModel<LoginModule>()
        ZoomradTheme {
            LoginScreenContent(
                uiState = screenModel.collectAsState(),
                onEventDispatcher = screenModel::onEventDispatcher
            )
        }

        screenModel.collectSideEffect{
            when (it) {
                is LoginContract.SideEffect.Toast -> {
                    Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }

    }
}

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenContent(
    uiState: State<LoginContract.UIState>,
    onEventDispatcher: (LoginContract.Intent) -> Unit
) {
    val phoneText = remember { mutableStateOf("") }
    val passwordText = remember { mutableStateOf("") }
    val isVisibleTxt = remember {
        mutableStateOf(false)
    }
    MySurface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 64.dp)
                    .padding(horizontal = 128.dp)
                    .aspectRatio(1f)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.ic_zoomrad),
                contentDescription = null
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 24.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .height(48.dp)
            ) {
                PhoneInputFastPayment(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically),
                    text = phoneText.value,
                    onTextChanged = {newValue->
                        if (newValue.length <= 9) {
                            phoneText.value = newValue
                        }
                    }

                )
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .height(48.dp)
            ) {
              TextField(

                    textStyle = TextStyle(
                        fontSize = 16.sp,
                    ),
                    value = passwordText.value,
                    onValueChange = { newText ->
                        passwordText.value = newText
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.login_screen_enter_your_password_here), fontSize = 16.sp, color = Color.Gray,
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
                    visualTransformation = if (isVisibleTxt.value){
                        VisualTransformation.None

                    }else{
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
                    painter = painterResource(id = if (isVisibleTxt.value)R.drawable.eyes else R.drawable.eyes2), contentDescription =null,
                    tint = colorResource(id = R.color.zumrat)
                )
            }
            if (passwordText.value.isNotEmpty() && passwordText.value.length < 6) {
                PasswordWorning(stringResource(id = R.string.login_screen_the_password_must_contain_at_least_6_characters))
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(8.dp))
                .height(48.dp)
                .background(
                    color =
                    if (phoneText.value.length == 9 && passwordText.value.length > 5) {
                        "${phoneText.value.length}".myLog()
                        colorResource(id = R.color.zumrat)
                    } else {
                        "${phoneText.value.length}".myLog()
                        Color.Gray
                    }
                )
                .clickable {
                    if (phoneText.value.length < 9 && passwordText.value.length > 5) {
                        return@clickable
                    }
                    onEventDispatcher(
                        LoginContract.Intent.OpenSMSScreen(
                            "+998${phoneText.value}", passwordText.value
                        )
                    )
                }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.login_screen_send_sms),
                    color = Color.White
                )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
                    .clickable(
                        onClick = {
                            onEventDispatcher(LoginContract.Intent.OpenSingUpScreen)
                        },

                        )
                ,
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.login_screen_dont_have_an_account),
                fontSize = 12.sp,
                color = colorResource(id = R.color.zumrat)
                )

        }
    }
}

private fun formatPhoneText(input: String): String {
    /* Add your formatting logic here. You can use a library or write custom logic to apply the phone number format */
    return input
}


@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreenContent(
        mutableStateOf(LoginContract.UIState.InitState)) {

    }
}

