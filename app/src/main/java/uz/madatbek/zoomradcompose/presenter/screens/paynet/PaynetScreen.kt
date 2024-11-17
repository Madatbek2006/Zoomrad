package uz.madatbek.zoomradcompose.presenter.screens.paynet

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.sudo_pacman.paynet.presenter.screens.fast_payment.components.PhoneInputFastPayment
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.PaynetData
import uz.madatbek.zoomradcompose.ui.components.TopBar
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
import uz.madatbek.zoomradcompose.utils.visualtransformation.MoneyVisualTransformation

class PaynetScreen(private val data:PaynetData):Screen {
    @Composable
    override fun Content() {
        val viewModel=getViewModel<PaynetViewModel>()
        ZoomradTheme {
            PaynetComponent(data,viewModel::onEventDispatcher)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ResourceType")
@Composable
fun PaynetComponent(data:PaynetData,onEventDispatcher:(PaynetContract.Intent)->Unit) {
    val passwordText= remember {
        mutableStateOf("")
    }
    val phoneText= remember {
        mutableStateOf("")
    }
    MySurface {
        Column {
            TopBar(onClickBack = { onEventDispatcher(PaynetContract.Intent.onClickBack) }, titleCenter = stringResource(id = data.resStr) )
            Box(modifier = Modifier
                .padding(vertical = 8.dp)
                .height(80.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .align(Alignment.CenterHorizontally)

            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    painter = painterResource(id = data.img), contentDescription = null
                )
            }

            Box(
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
                        .align(Alignment.CenterStart),
                    text = phoneText.value,
                    onTextChanged = {newValue->
                        if (newValue.length <= 9) {
                            phoneText.value = newValue
                        }
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

                            }
                        ),
                    painter = painterResource(id = R.drawable.ic_user), contentDescription =null,
                    tint = colorResource(id = R.color.zumrat)
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
                        if (newText.length>9)return@TextField
                        passwordText.value = newText
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.paynet_screen_amount_payment), fontSize = 16.sp, color = Color.Gray,
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
                    visualTransformation = MoneyVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    if (phoneText.value.length==9&&passwordText.value.isNotEmpty())
                    colorResource(id = R.color.zumrat)
                    else Color.Gray
                )
                .clickable {

                }
            ) {
                androidx.compose.material3.Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.paynet_screen_аurther),
                    color = Color.White
                )
            }

        }
    }
}

@SuppressLint("ResourceType")
@Preview
@Composable
fun PaynetPreview() {
    PaynetComponent(data = PaynetData(R.drawable.ic_beeline,R.string.beeline), onEventDispatcher = {})
}