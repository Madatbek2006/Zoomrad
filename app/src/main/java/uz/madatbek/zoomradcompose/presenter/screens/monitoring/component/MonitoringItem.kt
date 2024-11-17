package uz.madatbek.zoomradcompose.presenter.screens.monitoring.component

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferDetail
import uz.madatbek.zoomradcompose.utils.formatTimestamp
import uz.madatbek.zoomradcompose.utils.toHideCard
import uz.madatbek.zoomradcompose.utils.toManyFormat

@Composable
fun MonitoringItem(modifier:Modifier=Modifier, data:TransferDetail?, onClick:()->Unit) {

    data?.let { data->
        val isInCome = data.type == "income"

        Box(
            modifier = modifier
                .height(80.dp)
                .clickable {
                },

            ) {
            Row(modifier = modifier.fillMaxWidth()) {
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 12.dp)
                        .padding(vertical = 12.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        painter = painterResource(id = if (isInCome) R.drawable.arrow_down else R.drawable.arrow_up),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(
                            color = Color.Gray
                        )
                    )
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 8.dp)
                ) {
                    Text(text = if (isInCome) "Kartani toldirish" else "")
                    Text(
                        text = if (isInCome) data.from else data.to,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(text = data.time.formatTimestamp("hh:mm"))
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 16.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = if (isInCome) "+" + data.amount.toString()
                            .toManyFormat() else "-" + data.amount.toString().toManyFormat(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isInCome) colorResource(id = R.color.zumrat) else colorResource(
                            id = R.color.zumrat_exception
                        ),
                        textAlign = TextAlign.End
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = if (isInCome) data.to.toHideCard() else data.from.toHideCard(),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

@Composable
fun MonitoringDateItem(data:String) {
    Box(modifier = Modifier.fillMaxWidth()
    ){
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = data
        )
    }
}


@Preview
@Composable
fun HistoryPreview() {
    MonitoringItem(data = TransferDetail("income","Muhammadali","1234567898765420",2000,1671350649653)) {

    }
//    HistoryDateItem(data = "20024.03.22")
}