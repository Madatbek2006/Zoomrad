package uz.madatbek.zoomradcompose.presenter.screens.transferverify

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
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferRequest
import uz.madatbek.zoomradcompose.ui.components.SuccessComponent
import uz.madatbek.zoomradcompose.ui.components.TopBar
import uz.madatbek.zoomradcompose.presenter.screens.sms.MyTextFieldWithHint
import uz.madatbek.zoomradcompose.presenter.screens.transfer.TransferUIData
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.components.TransferSuccessComponent
import uz.madatbek.zoomradcompose.utils.SuccessEnum
import uz.madatbek.zoomradcompose.utils.toPhoneFormat
import java.util.concurrent.TimeUnit

class TransferVerifyScreen(private val request:TransferUIData):Screen{
    @Composable
    override fun Content() {
        val context= LocalContext.current
        val viewModel=getViewModel<TransferVerifyViewModel>()
        when(viewModel.collectAsState().value.successComponent){
            SuccessEnum.NONE->TransferVerifyComponent(TransferRequest("third-card", request.data.id.toString(), request.recipientCard, request.amount.toLong(),),viewModel::onEventDispatcher)
            SuccessEnum.SUCCESS-> TransferSuccessComponent(R.string.drawer_profile_txt,true,{viewModel.onEventDispatcher(TransferVerifyContract.Intent.OnCLickBack)}){viewModel.onEventDispatcher(TransferVerifyContract.Intent.OpenCheckScreen(request))}
            SuccessEnum.FAIL-> SuccessComponent(R.string.drawer_profile_txt,false,){viewModel.onEventDispatcher(TransferVerifyContract.Intent.OnCLickBack)}
        }

        viewModel.collectSideEffect{
            when(it){
                is TransferVerifyContract.SideEffect.Toast->{
                    Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun TransferVerifyComponent(request:TransferRequest,onEventDispatcher:(TransferVerifyContract.Intent)->Unit){
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
    MySurface(modifier = Modifier.fillMaxSize()) {
        val smsCode= remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopBar(onClickBack = {
                                 onEventDispatcher(TransferVerifyContract.Intent.OnCLickBack)
            }, titleCenter = stringResource(id = R.string.transfer_verify_confirmation))
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
                text = stringResource(id = R.string.transfer_verify_we_sent_SMS_with_code)
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 4.dp),
                text =
                (
                        MyShar.getUserLoginData()?.phone?:
                "+998932320322").toPhoneFormat(),
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
                    Text(
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
                                onEventDispatcher(TransferVerifyContract.Intent.Transfer(request = request))
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
                        Text(
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
                            onEventDispatcher(
                                TransferVerifyContract.Intent.TransferVerify(
                                    sms = smsCode.value
                                )
                            )
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




@Preview
@Composable
fun TransferVerifyPreview() {
//    TransferVerifyComponent(){
//
//    }
}