package uz.madatbek.zoomradcompose.presenter.screens.transfer

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.CardData
import uz.madatbek.zoomradcompose.ui.components.LoadingComponent
import uz.madatbek.zoomradcompose.ui.components.TopBar
import uz.madatbek.zoomradcompose.ui.components.CardComponentForTransferView
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
import uz.madatbek.zoomradcompose.utils.toCardPan
import uz.madatbek.zoomradcompose.utils.toManyFormat

class TransferScreen(private val transferUIData: TransferUIData) : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<TransferViewModel>()
        val uiState = viewModel.collectAsState()
        ZoomradTheme {
            when (uiState.value) {
                TransferContract.UIState.Progress -> {
                    LoadingComponent()
                }

                TransferContract.UIState.Default -> {
                    TransferComponent(transferUIData, viewModel::onEventDispatchers)
                }
            }
        }
    }

}


@Composable
fun TransferComponent(
    transferUIData: TransferUIData,
    onEventDispatcher: (TransferContract.Intent) -> Unit
) {
    MySurface {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(
                onClickBack = {
                              onEventDispatcher(TransferContract.Intent.OnClickBack)
                },
                titleCenter = stringResource(id = R.string.transfer_screen_card_to_card),
            )
            TransferTxt(
                modifier = Modifier.padding(top = 16.dp),
                title = stringResource(id = R.string.transfer_screen_recipient_name),
                message = transferUIData.name
            )
            TransferTxt(
                title = stringResource(id = R.string.transfer_screen_recipient_cart),
                message = transferUIData.recipientCard.toCardPan()
            )
            TransferTxt(
                title = stringResource(id = R.string.transfer_screen_transfer_amount),
                message = transferUIData.amount.toString()
                    .toManyFormat() + stringResource(id = R.string.home_page_item1_sum)
            )
            TransferTxt(
                title = stringResource(id = R.string.transfer_screen_transfer_fee),
                message = "0%"
            )
            TransferTxt(
                title = stringResource(id = R.string.transfer_screen_total_amount),
                message = transferUIData.amount.toString()
                    .toManyFormat() + stringResource(id = R.string.home_page_item1_sum)
            )
            Spacer(modifier = Modifier.weight(1f))
            CardComponentForTransferView(
                modifier = Modifier.padding(horizontal = 32.dp).padding(vertical = 32.dp).aspectRatio(1.8f),
                data = transferUIData.data
            )

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(colorResource(id = R.color.zumrat))
                .clickable {
                    onEventDispatcher(
                        TransferContract.Intent.Transfer(
                            data = transferUIData,
                        )
                    )
                }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.transfer_screen_txt),
                    color = Color.White
                )
            }
        }

    }
}


@Preview
@Composable
fun TransferScreenPreview() {
//    ZoomradTheme {
    TransferComponent(
        TransferUIData(
            data = CardData(id = 2, name = "Vo hoho", amount =  5000, owner = "dsf", pan =  "0008000800080026", expiredYear = 2028, expiredMonth = 6, themeType = 7, isVisible = true),
            name = "JASURBEK DJURAYEV",
            amount = 5000,
            recipientCard = "0008000800080045"
        )
    ){

    }
//    }

}

@Composable
fun TransferTxt(modifier: Modifier=Modifier,title: String, message: String) {
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
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


data class TransferUIData(
    val data: CardData,
    val name: String,
    val amount: Int,
    val recipientCard: String
)