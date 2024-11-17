package uz.madatbek.zoomradcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.madatbek.zoomradcompose.R

@Composable
fun TransferSuccessComponent(title:Int, isSuccess:Boolean,onClickBack: () -> Unit,onClickViewCheck:()->Unit){
    MySurface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()){
            TopBar(onClickBack = { onClickBack() }, titleCenter = stringResource(id = title))
            Column(modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 100.dp)){
                Image(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),painter = painterResource(id = if (isSuccess) R.drawable.ic_payment_completed else R.drawable.ic_payment_failed), contentDescription = null)
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = stringResource(id = if (isSuccess) R.string.success_component_success else R.string.success_component_fail),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            onClickViewCheck()
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
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .height(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(colorResource(id = R.color.zumrat))
                    .clickable {
                        onClickBack()
                    }
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(id = R.string.success_component_ok),
                        color = Color.White
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun TransferPreview() {

    TransferSuccessComponent(R.string.transfer,true,{}){}
}