package uz.madatbek.zoomradcompose.presenter.screens.payments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.TransferItem1Data
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.payments.OutlinedTextExample
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme

class PaymentsScreen:Screen {
    @Composable
    override fun Content() {
        val viewModel=getViewModel<PaymentsViewModel>()
        ZoomradTheme {
            PaymentsComponent(viewModel.collectAsState(),viewModel::onEventDispatcher)
        }
    }
}






@Composable
fun PaymentsComponent(uiState:State<PaymentsContract.UIState>,onEventDispatcher:(PaymentsContract.Intent)->Unit) {
    val textState= remember {
        mutableStateOf("")
    }
    MySurface {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth()){
                Box(modifier = Modifier
                    .width(56.dp)
                    .height(56.dp)
                    .clickable(
                        onClick = {
                                  onEventDispatcher(PaymentsContract.Intent.onClickBack)
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
                        tint = colorResource(id = R.color.zumrat)
                    )
                }
                OutlinedTextExample(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth()
                        .padding(end = 16.dp)
                        .clip(RoundedCornerShape(300.dp)), textState = textState,

                    )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Указываем количество столбцов в сетке
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(data) {
                    PaymentItem(modifier = Modifier.weight(1f), data = it, onCLick = {
                        onEventDispatcher(PaymentsContract.Intent.OpenPaynetScreen(it))
                    })
                }
                item{
                    Box(modifier = Modifier.height(64.dp))
                }
                item{
                    Box(modifier = Modifier.height(64.dp))
                }
            }

        }
    }
}
@Composable
fun PaymentItem(
    modifier: Modifier,
    data: TransferItem1Data,
    onCLick: ((TransferItem1Data) -> Unit)
) {
    Box(
        modifier = modifier
            .height(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onCLick(data) },
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .fillMaxHeight()
                .padding(start = 12.dp)
                .padding(vertical = 12.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(color = Color.White)
            ){
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .padding(12.dp),
                    painter = painterResource(id = data.icon), contentDescription = null,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp),
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = stringResource(id = data.name),
                    fontSize = 10.sp
                )
            }
        }

    }
}

val data = arrayListOf(
    TransferItem1Data(R.drawable.ic_cellular, R.string.payments_screen_callular),
    TransferItem1Data(R.drawable.ic_beeline, R.string.payments_screen_item1_1),
    TransferItem1Data(R.drawable.ic_usell, R.string.payments_usell),
    TransferItem1Data(R.drawable.ic_uzmobile,  R.string.payments_uzmobile),
    TransferItem1Data(R.drawable.ic_mobiuz, R.string.payments_mobiuz),
    TransferItem1Data(R.drawable.ic_perfectum,R.string.payments_perfectum),
    TransferItem1Data(R.drawable.ic_tele2,R.string.payments_tele2),
    TransferItem1Data(R.drawable.ic_kcell,R.string.payments_kcell),
    TransferItem1Data(R.drawable.ic_megafon,R.string.payments_megafon),
//    TransferItem1Data(R.drawable.ic_donation,R.string.payments_item1_8),
//    TransferItem1Data(R.drawable.ic_basket,R.string.payments_item1_9),
//    TransferItem1Data(R.drawable.ic_advertising,R.string.payments_item1_10),
//    TransferItem1Data(R.drawable.ic_services,R.string.payments_item1_11),
//    TransferItem1Data(R.drawable.ic_servis,R.string.payments_item1_12),
//    TransferItem1Data(R.drawable.ic_book,R.string.payments_item1_13),
//    TransferItem1Data(R.drawable.ic_wallet,R.string.payments_item1_14),
//    TransferItem1Data(R.drawable.ic_puzzle,R.string.payments_item1_15),
//    TransferItem1Data(R.drawable.ic_provider,R.string.payments_item1_16),
//    TransferItem1Data(R.drawable.ic_basket,R.string.payments_item1_17),
//    TransferItem1Data(R.drawable.ic_advertising,R.string.payments_item1_18),
//    TransferItem1Data(R.drawable.ic_services,R.string.payments_item1_19),
//    TransferItem1Data(R.drawable.ic_servis,R.string.payments_item1_20),
//    TransferItem1Data(R.drawable.ic_book,R.string.payments_item1_21),
//    TransferItem1Data(R.drawable.ic_wallet,R.string.payments_item1_22)
)

@Preview
@Composable
fun PaymentsPreview() {
}