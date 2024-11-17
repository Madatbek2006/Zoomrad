package uz.madatbek.zoomradcompose.presenter.screens.cashback

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme

class СashbackScreen:Screen {
    @Composable
    override fun Content() {
        val viewModel=getViewModel<CashBackViewModel>()
        ZoomradTheme {
            CashbackComponent(viewModel::onEventDispatcher)
        }
    }
}


@Composable
fun CashbackComponent(onEventDispatchers:(CashBackContract.Intent)->Unit) {
    var isOpen by remember {
        mutableStateOf(true)
    }
    MySurface {
        Column {
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)){

                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 32.dp)
                        .clip(RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)),
                    painter = painterResource(id = R.drawable.bg_top), contentDescription =null,
                    contentScale = ContentScale.Crop
                )
                Box(modifier = Modifier
                    .height(56.dp)
                    .align(Alignment.TopCenter)){
                    Text(
                        text = stringResource(id = R.string.cashback_screen_txt), modifier = Modifier
                            .align(Alignment.Center),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
                Column(modifier = Modifier
                    .height(56.dp)
                    .align(Alignment.Center)){
                    Text(
                        text = stringResource(id = R.string.cashback_screen_ostatok_ballov), modifier = Modifier,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "0.0",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    )
                }

                Box(modifier = Modifier
                    .width(56.dp)
                    .height(56.dp)
                    .align(Alignment.TopStart)
                    .clickable(
                        onClick = {
                            onEventDispatchers(CashBackContract.Intent.onClickBack)
                        },
                        indication = rememberRipple(
                            bounded = false,
                            radius = 36.dp
                        ),
                        interactionSource = remember { MutableInteractionSource() } // Источник взаимодействия
                    )
                ){
                    Icon(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_back_ios),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Box(modifier = Modifier
                    .width(56.dp)
                    .height(56.dp)
                    .align(Alignment.TopEnd)
                    .clickable(
                        onClick = {
                            onEventDispatchers(CashBackContract.Intent.onClickBack)
                        },
                        indication = rememberRipple(
                            bounded = false,
                            radius = 36.dp
                        ),
                        interactionSource = remember { MutableInteractionSource() } // Источник взаимодействия
                    )
                ){
                    Icon(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_info),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp, end = 8.dp)
                            .height(64.dp)
                            .clip(RoundedCornerShape(8.dp)),
                    ) {
                        Row(modifier = Modifier.fillMaxSize()) {
                            Box(modifier = Modifier
                                .padding(start = 8.dp)
                                .size(48.dp)
                                .align(Alignment.CenterVertically)
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    color = colorResource(
                                        id = R.color.zumrat
                                    )
                                )) {
                                Icon(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .align(Alignment.Center),
                                    painter = painterResource(id = R.drawable.ic_arrow_up),
                                    contentDescription =null,
                                    tint = Color.White
                                )

                            }
                            Column(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .align(Alignment.CenterVertically)
                            ) {
                                Text(
                                    text = "0.0",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.cashback_screen_accumulated),
                                    fontSize = 12.sp
                                )
                            }

                        }
                    }
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp, end = 16.dp)
                            .height(64.dp)
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Row(modifier = Modifier.fillMaxSize()) {
                            Box(modifier = Modifier
                                .padding(start = 8.dp)
                                .size(48.dp)
                                .align(Alignment.CenterVertically)
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    color = Color.Gray
                                )) {
                                Icon(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .align(Alignment.Center)
                                        .rotate(180f)
                                    ,
                                    painter = painterResource(id = R.drawable.ic_arrow_up),
                                    contentDescription =null,
                                    tint = Color.White,
                                )

                            }
                            Column(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .align(Alignment.CenterVertically)
                            ) {
                                Text(
                                    text = "0.0",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.cashback_screen_used),
                                    fontSize = 12.sp
                                )
                            }

                        }
                    }

                }

            }
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .weight(3f)){
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()){
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                            .clip(
                                RoundedCornerShape(8.dp)
                            )) {
                            Column {
                                Row(modifier = Modifier
                                    .height(56.dp)
                                    .clickable {
                                        isOpen = !isOpen
                                    }
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .size(56.dp)
                                            .padding(16.dp),
                                        painter = painterResource(id = R.drawable.ic_cashback_icon),
                                        contentDescription =null,
                                        tint = colorResource(id = R.color.zumrat)
                                    )

                                    Text(
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                        text = stringResource(id = R.string.cashback_screen_cashback_conditions),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Icon(modifier = Modifier
                                        .size(56.dp)
                                        .padding(16.dp),
                                        painter = painterResource(id = if (isOpen)R.drawable.arrow_up else R.drawable.arrow_down),
                                        contentDescription = null
                                        ,tint = colorResource(id = R.color.zumrat)
                                    )
                                }
                                if (isOpen){
                                    Row {
                                        Image(
                                            modifier = Modifier
                                                .size(64.dp)
                                                .padding(start = 16.dp),
                                            painter = painterResource(id = R.drawable.img_onlay_chat),
                                            contentDescription = null
                                        )

                                        Column(
                                            modifier = Modifier
                                                .padding(start = 8.dp)
                                                .align(Alignment.CenterVertically)
                                        ) {
                                            Text(text = stringResource(id = R.string.cashback_screen_trransfer_from_card_to_card),
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(text = stringResource(id = R.string.cashback_screen_trransfer_from_card_to_card_cost),
                                                fontWeight = FontWeight.Bold,
                                                color = colorResource(id = R.color.zumrat)
                                            )
                                        }

                                    }
                                    Row {
                                        Image(
                                            modifier = Modifier
                                                .size(64.dp)
                                                .padding(start = 16.dp),
                                            painter = painterResource(id = R.drawable.img_onlay_chat),
                                            contentDescription = null
                                        )

                                        Column(
                                            modifier = Modifier
                                                .padding(start = 8.dp)
                                                .align(Alignment.CenterVertically)
                                        ) {
                                            Text(text = stringResource(id = R.string.cashback_screen_all_trransfer),
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(text = stringResource(id = R.string.cashback_screen_all_trransfer_cost),
                                                fontWeight = FontWeight.Bold,
                                                color = colorResource(id = R.color.zumrat)
                                            )
                                        }

                                    }
                                    Text(
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .padding(vertical = 16.dp),
                                        text = stringResource(id = R.string.cashback_screen_exceptions)
                                    )

                                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                                        CashBackText(modifier = Modifier.weight(1f),title = stringResource(id = R.string.cashback_screen_exceptions), cost = stringResource(id = R.string.cashback_screen_all_energy_cost) )
                                        CashBackText(modifier = Modifier.weight(1f),title = stringResource(id = R.string.cashback_screen_gas), cost = stringResource(id = R.string.cashback_screen_all_energy_cost) )
                                    }
                                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                                        CashBackText(modifier = Modifier.weight(1f),title = stringResource(id = R.string.cashback_screen_garbage_call), cost = stringResource(id = R.string.cashback_screen_all_energy_cost) )
                                        CashBackText(modifier = Modifier.weight(1f),title = stringResource(id = R.string.cashback_screen_wather), cost = stringResource(id = R.string.cashback_screen_all_energy_cost) )
                                    }
                                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                                        CashBackText(modifier = Modifier.weight(1f),title = stringResource(id = R.string.cashback_screen_budget_payments), cost = stringResource(id = R.string.cashback_screen_budget_payments_cost) )
                                    }
                                    Text(
                                        modifier = Modifier.padding(start = 8.dp, bottom = 16.dp, top = 4.dp),
                                        text = stringResource(id = R.string.cashback_screen_exceptions_aloka_banck),
                                        fontSize = 12.sp
                                    )
                                }
                            }

                        }
                    }
                }
            }

        }
    }

}

@Composable
fun CashBackText(modifier: Modifier=Modifier,title:String,cost:String) {
    Column(modifier=modifier.padding(start = 8.dp)) {
        Text(text = title,
            fontWeight = FontWeight.Bold
        )
        Text(text = cost,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.zumrat)
        )
    }
}


@Preview
@Composable
fun CashbackPreview() {
    CashbackComponent{

    }
}