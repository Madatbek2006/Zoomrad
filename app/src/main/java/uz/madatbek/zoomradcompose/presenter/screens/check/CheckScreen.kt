package uz.madatbek.zoomradcompose.presenter.screens.check

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.CheckData
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.components.TopBar
import uz.madatbek.zoomradcompose.utils.formatTimestamp
import uz.madatbek.zoomradcompose.utils.toHideCard

class CheckScreen(private  val data: CheckData):Screen {
    @Composable
    override fun Content() {
        val viewModel=getViewModel<CheckViewModel>()
        CheckContent(data, viewModel::onEventDispatcher)
    }
}


@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CheckContent(data: CheckData,onEventDispatchers:(CheckContract.Intent)->Unit) {
    MySurface {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(onClickBack = { onEventDispatchers(CheckContract.Intent.onClickBack) }, titleCenter = stringResource(id = R.string.check_screen_txt))
            Box(modifier = Modifier
                .padding(vertical = 8.dp)
                .height(80.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(color = colorResource(id = R.color.zumrat))
                .align(Alignment.CenterHorizontally)

            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .rotate(180f),
                    painter = painterResource(id = R.drawable.ic_arrow_up),
                    contentDescription = null,
                    tint = Color.White
                )
            }

            CheckedText(modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 4.dp)
                .padding(top = 16.dp), title = stringResource(id = R.string.check_screen_recipient) , message = data.recipient)
            CheckedText(modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp), title = stringResource(id = R.string.check_screen_to_card) , message = data.toCardPan.toHideCard())
            CheckedText(modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp), title = stringResource(id = R.string.check_screen_from_card) , message = data.fromCardPan.toHideCard())
            CheckedText(modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp), title = stringResource(id = R.string.check_screen_amount) , message = data.amount.toString()+stringResource(id = R.string.check_screen_sum))
            CheckedText(modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp), title = stringResource(id = R.string.check_screen_time) , message = data.time.formatTimestamp("yyyy-MM-dd hh:mm"))
            CheckedText(modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp), title = stringResource(id = R.string.check_screen_merchart) , message = data.merchant)
            CheckedText(modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp), title = stringResource(id = R.string.check_screen_teminal) , message = data.terminal)
            CheckedText(modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp), title = stringResource(id = R.string.check_screen_commission) , message = data.commission.toString())
            CheckedText(modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp), title = stringResource(id = R.string.check_screen_status) , message = data.status)
            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {

                    }
            ) {
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        painter = painterResource(id = R.drawable.ic_share2),
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically).padding(horizontal = 8.dp),
                    text = stringResource(id = R.string.check_screen_share),
                )
            }
        }
    }
}

@Composable
fun CheckedText(modifier: Modifier,title:String,message:String) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 4.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = title
        )


        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            text = message,
            textAlign = TextAlign.End,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
@Preview
@Composable
fun CheckPreview() {
    CheckContent(data = CheckData("JASURBEK DJURAEV","0008000800080035","0008000800080026",5000,System.currentTimeMillis(),"ECECEFOPKPO094123","E0000028",0,"SUCCESSFULLY"), onEventDispatchers = {})
}
