package uz.madatbek.zoomradcompose.presenter.screens.main.pages.payments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.TransferItem1Data
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.home.HomeComponent
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
import uz.madatbek.zoomradcompose.utils.myLog

object PaymentsPage : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(id = R.string.payments)
            val icon =
                rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_payment))

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel=getViewModel<PaymentsViewModel>()

        ZoomradTheme {
            PaymentComponent(viewModel.collectAsState(),viewModel::onEventDispatcher)
        }
    }
}


@Composable
fun PaymentComponent(uiState:State<PaymentsContract.UIState>,onEventDispatchers:(PaymentsContract.Intent)->Unit) {
    MySurface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Text(
                text = stringResource(id = R.string.payments_page_ol),
                modifier = Modifier
                    .padding(start = 12.dp)
                    .padding(vertical = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.payments_page_ol_servise),
                modifier = Modifier
                    .padding(start = 12.dp)
                    .padding(vertical = 8.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Указываем количество столбцов в сетке
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(data) {
                    TransferItem(modifier = Modifier.weight(1f), data = it, onCLick = {
                        onEventDispatchers(PaymentsContract.Intent.OpenPaymentsScreen)
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
fun TransferItem(
    modifier: Modifier,
    data: TransferItem1Data,
    onCLick: ((TransferItem1Data) -> Unit)
) {
    Box(
        modifier = modifier
            .height(80.dp)
            .clip(RoundedCornerShape(16.dp))


            .clickable {onCLick(data)},
//                onClick = {
//                    onClick(data)
//                }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Card(modifier = Modifier
                .fillMaxHeight()
                .padding(start = 12.dp)
                .padding(vertical = 12.dp)
                .clip(RoundedCornerShape(16.dp))
            ){
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .padding(12.dp),
                    painter = painterResource(id = data.icon), contentDescription = null,
                    colorFilter = ColorFilter.tint(color = colorResource(id = R.color.zumrat))
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextExample(modifier: Modifier, textState: MutableState<String>) {
    Card(modifier = modifier) {
    Row {
        TextField(
            value = textState.value,
            onValueChange = { newText ->
                textState.value = newText
            },
            placeholder = {
                Text(
                    text = "Название сервиса", fontSize = 12.sp
                )
            },
            modifier = Modifier
                .height(28.dp)
                .weight(1f)
                .align(Alignment.CenterVertically),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent, // Устанавливаем прозрачный цвет контейнера
                unfocusedIndicatorColor = Color.Transparent, // Цвет индикатора, когда поле не в фокусе
                focusedIndicatorColor = Color.Transparent  // Цвет индикатора, когда поле в фокусе
            ),
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = Color.White
            )
        )
        Icon(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp)
                .height(16.dp)
                .width(16.dp),
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null
        )
    }
    }

}


val data = arrayListOf(
    TransferItem1Data(R.drawable.ic_phone, R.string.payments_item1_1),
    TransferItem1Data(R.drawable.ic_network, R.string.payments_item1_2),
    TransferItem1Data(R.drawable.ic_call,  R.string.payments_item1_3),
    TransferItem1Data(R.drawable.ic_home, R.string.payments_item1_3),
    TransferItem1Data(R.drawable.ic_car,R.string.payments_item1_4),
    TransferItem1Data(R.drawable.ic_tv,R.string.payments_item1_5),
    TransferItem1Data(R.drawable.ic_credit,R.string.payments_item1_6),
    TransferItem1Data(R.drawable.ic_government,R.string.payments_item1_7),
    TransferItem1Data(R.drawable.ic_donation,R.string.payments_item1_8),
    TransferItem1Data(R.drawable.ic_basket,R.string.payments_item1_9),
    TransferItem1Data(R.drawable.ic_advertising,R.string.payments_item1_10),
    TransferItem1Data(R.drawable.ic_services,R.string.payments_item1_11),
    TransferItem1Data(R.drawable.ic_servis,R.string.payments_item1_12),
    TransferItem1Data(R.drawable.ic_book,R.string.payments_item1_13),
    TransferItem1Data(R.drawable.ic_wallet,R.string.payments_item1_14),
    TransferItem1Data(R.drawable.ic_puzzle,R.string.payments_item1_15),
    TransferItem1Data(R.drawable.ic_provider,R.string.payments_item1_16),
    TransferItem1Data(R.drawable.ic_basket,R.string.payments_item1_17),
    TransferItem1Data(R.drawable.ic_advertising,R.string.payments_item1_18),
    TransferItem1Data(R.drawable.ic_services,R.string.payments_item1_19),
    TransferItem1Data(R.drawable.ic_servis,R.string.payments_item1_20),
    TransferItem1Data(R.drawable.ic_book,R.string.payments_item1_21),
    TransferItem1Data(R.drawable.ic_wallet,R.string.payments_item1_22)
)

@Preview
@Composable
fun PaymentPreview() {
//    PaymentComponent()
}