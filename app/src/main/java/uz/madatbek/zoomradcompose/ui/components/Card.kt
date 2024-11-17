package uz.madatbek.zoomradcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.CardData
import uz.madatbek.zoomradcompose.utils.toCardPan
import uz.madatbek.zoomradcompose.utils.toDate
import uz.madatbek.zoomradcompose.utils.toManyFormat

@Composable
fun CardComponent(
    modifier: Modifier,
    data: Int,
    cardName: MutableState<String>,
    cardNumber: MutableState<String>,
    cardDate: MutableState<String>
) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)),
            painter = painterResource(id = data),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_card_ic),
                contentDescription = null
            )
            Text(
                modifier = Modifier,
                text = cardName.value,
                color = Color.White,
                fontSize = 12.sp,
            )
        }

        Row(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomStart)
        ) {
            Text(
                modifier = Modifier,
                text = cardNumber.value.toCardPan(),
                color = Color.White,
                fontSize = 12.sp
            )
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = cardDate.value.toDate(),
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}


@Preview
@Composable
fun Preview() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            CardComponentForTransferView(
                modifier = Modifier
                    .padding(horizontal = 32.dp),
                data = CardData(0, "Madatbek", 1111111, "", "0026", 2028, 6, 0, true)
            ) {

            }
        }
    }
}

@Composable
fun CardComponentForView(
    modifier: Modifier,
    data: CardData,
    onCLick: ((CardData) -> Unit)? = null
) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .clickable(
                    enabled = onCLick!=null,
                    onClick = {
                        onCLick?.invoke(data)
                    }
                ),
            painter = painterResource(id =when (MyShar.getThemeCard(if (data.pan.length<16)"000800080008"+data.pan else data.pan )) {
                0 -> R.drawable.img_card1
                1 -> R.drawable.img_card2
                2 -> R.drawable.img_card3
                3 -> R.drawable.img_card4
                4 -> R.drawable.img_card5
                5 -> R.drawable.img_card6
                else -> {
                    R.drawable.img_card7
                }
            },),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_card_ic),
                contentDescription = null
            )
            Text(
                modifier = Modifier,
                text = data.name,
                color = Color.White,
                fontSize = 12.sp,
            )
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomStart)
        ) {
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                Text(
                    modifier = Modifier,
                    text = data.amount.toString().toManyFormat(),
                    fontSize = 24.sp,
                    color = Color.White
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(start = 4.dp),
                    text = stringResource(id = R.string.home_page_item1_sum),
                    color = Color.White
                )
            }
            Row {
                Text(
                    modifier = Modifier,
                    text = "0008 **** **** ${data.pan}",
                    color = Color.White,
                    fontSize = 12.sp
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "${data.expiredMonth}/${data.expiredYear}",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }


    }
}



@Composable
fun CardComponentForTransferView(
    modifier: Modifier,
    data: CardData,
    onCLick: ((CardData) -> Unit)? = null
) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .clickable(
                    enabled = onCLick!=null,
                    onClick = {
                        onCLick?.invoke(data)
                    }
                ),
            painter = painterResource(id =when (data.themeType) {
                0 -> R.drawable.img_card1
                1 -> R.drawable.img_card2
                2 -> R.drawable.img_card3
                3 -> R.drawable.img_card4
                4 -> R.drawable.img_card5
                5 -> R.drawable.img_card6
                else -> {
                    R.drawable.img_card7
                }
            },),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_card_ic),
                contentDescription = null
            )
            Text(
                modifier = Modifier,
                text = data.name,
                color = Color.White,
                fontSize = 12.sp,
            )
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomStart)
        ) {
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                Text(
                    modifier = Modifier,
                    text = data.amount.toString().toManyFormat(),
                    fontSize = 24.sp,
                    color = Color.White
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(start = 4.dp),
                    text = stringResource(id = R.string.home_page_item1_sum),
                    color = Color.White
                )
            }
            Row {
                Text(
                    modifier = Modifier,
                    text = data.pan.toCardPan(),
                    color = Color.White,
                    fontSize = 12.sp
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "${data.expiredMonth}/${data.expiredYear}",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }


    }
}