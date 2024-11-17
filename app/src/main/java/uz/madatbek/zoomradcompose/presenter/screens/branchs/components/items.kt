package uz.madatbek.zoomradcompose.presenter.screens.branchs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.presenter.screens.branchs.MarkerData
import uz.madatbek.zoomradcompose.ui.components.MySurface

@Composable
fun GoogleMapItem(isCheck:Boolean,data: String,onClickItem:(data:String)->Unit) {
    Card(

        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onClickItem(data)
            }
            .background(if (isCheck) colorResource(id = R.color.zumrat) else MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp),
        colors =if (isCheck) CardDefaults.cardColors( colorResource(id = R.color.zumrat)) else CardDefaults.cardColors(),
    ) {

        Text(text = data,
            color = if (isCheck) Color.White else Color.Unspecified)
    }
}
@Composable
fun GoogleMapItem2(data: MarkerData, onClickItem: (data: MarkerData) -> Unit){
    MySurface {
        Column(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp)))
        {
            Box(modifier = Modifier
                .padding(top = 16.dp)
                .width(32.dp)
                .height(8.dp)
                .clip(RoundedCornerShape(300.dp))
                .background(Color.Gray)
                .align(Alignment.CenterHorizontally))

            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                text = data.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp)
                    .height(56.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 4.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp))
                ){
                    Box(modifier = Modifier.fillMaxSize()){
                        Icon(
                            modifier = Modifier.align(Alignment.Center),
                            painter = painterResource(id = R.drawable.ic_map_marker_info),
                            contentDescription = null,
                            tint = colorResource(id = R.color.zumrat)
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = data.adress,
                )

            }
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp)
                    .height(56.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 4.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp))
                ){
                    Box(modifier = Modifier.fillMaxSize()){
                        Icon(
                            modifier = Modifier.align(Alignment.Center),
                            painter = painterResource(id = R.drawable.ic_map_marker_info),
                            contentDescription = null,
                            tint = colorResource(id = R.color.zumrat)
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = data.time
                    ,
                )
            }
            Card(modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 32.dp)
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .height(48.dp)
                .clip(
                    RoundedCornerShape(8.dp)
                )
                .clickable {
                    onClickItem(data)
                }
            ) {
                Card(modifier = Modifier.fillMaxSize()){
                    Box(modifier = Modifier.fillMaxSize()){
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "Маршрут",)
                    }
                }
            }
        }
    }
}
@Composable
fun GoogleMapItem3(data: MarkerData, onClickItem: (data: MarkerData) -> Unit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
        .clickable {
            onClickItem(data)
        }
    )
    {

        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            text = data.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp)
                .height(56.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 4.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
            ){
                Box(modifier = Modifier.fillMaxSize()){
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_map_marker_info),
                        contentDescription = null,
                        tint = colorResource(id = R.color.zumrat)
                    )
                }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = data.adress,
            )

        }
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, bottom = 16.dp)
                .height(56.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 4.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
            ){
                Box(modifier = Modifier.fillMaxSize()){
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_map_marker_info),
                        contentDescription = null,
                        tint = colorResource(id = R.color.zumrat)
                    )
                }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = data.time
                ,
            )
        }

    }
}

@Preview
@Composable
fun ComponentsPreview() {
    GoogleMapItem2(data = MarkerData(1.0,1.0,"daac","asccs","qwpfdc"), onClickItem = {} )

//    GoogleMapItem(isCheck = false, data = "dcffff", onClickItem ={} )
//    GoogleMapItem2(data = MarkerData(1.0,1.0,"daac","asccs","qwpfdc"), onClickItem = {})
}