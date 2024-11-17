package uz.madatbek.zoomradcompose.presenter.screens.main.pages.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material.Switch
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.utils.myLog

@Composable
fun HomeBottomContent(isUzb:Boolean,onClick: (Boolean) -> Unit) {
    val isUZBVal= remember {
        mutableStateOf(isUzb)
    }
    MySurface{
        Column(modifier = Modifier
            .fillMaxSize(
            )
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))) {
            Box(modifier = Modifier
                .padding(8.dp)
                .height(8.dp)
                .width(64.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(300.dp))
                .background(Color.Gray)
            )

            Row(modifier = Modifier.fillMaxWidth()) {

                BottomSheetButton(
                    Modifier
                        .weight(1f)
                        .padding(vertical = 16.dp).padding(start = 16.dp),isUZBVal.value,"UZS") {
                    isUZBVal.value=true
                    "isUZBVal.value=>${isUZBVal.value}".myLog()
                }

                BottomSheetButton(
                    Modifier
                        .weight(1f)
                        .padding(vertical = 16.dp).padding(end = 16.dp),!isUZBVal.value,"USD") {
                    isUZBVal.value=false
                    "isUZBVal.value=>${isUZBVal.value}".myLog()
                }

            }
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.home_page_overall_balance),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = if (isUzb)"0 сум" else "0 USD",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Row {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .padding(vertical = 16.dp),
                    text = "Показывать эту валюту на главной странице",
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    modifier = Modifier.padding(end = 16.dp),
                    checked = if (isUZBVal.value) isUzb else !isUzb, onCheckedChange = {
                        onClick(if (isUZBVal.value) it else !it)
                })
            }

        }
    }
}

@Composable
fun BottomSheetButton(modifier: Modifier=Modifier,isCLick:Boolean,data:String,onClick:()->Unit,) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable {
                onClick()
            }
          ,
        colors =if (isCLick) CardDefaults.cardColors( colorResource(id = R.color.zumrat)) else CardDefaults.cardColors(),
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.flag_america), contentDescription = null
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp),
                text = data,
                color=if (isCLick) Color.White else Color.Unspecified
            )
        }
    }
}


@Preview
@Composable
fun HomeBottomPreview() {
//    HomeBottomContent()
}