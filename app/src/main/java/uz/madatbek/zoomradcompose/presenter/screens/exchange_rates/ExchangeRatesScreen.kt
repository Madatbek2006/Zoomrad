package uz.madatbek.zoomradcompose.presenter.screens.exchange_rates

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import uz.madatbek.zoomradcompose.data.model.HomeItem3Data
import uz.madatbek.zoomradcompose.ui.components.TopBar
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme

class ExchangeRatesScreen(private val data: List<HomeItem3Data>):Screen {
    @Composable
    override fun Content() {
        val viewModel=getViewModel<ExchangeRatesViewModel>()

        ZoomradTheme {
            ExchangeRatesComponent(data = data,viewModel::onEventDispatcher)
        }
    }
}


@Composable
fun ExchangeRatesComponent(data: List<HomeItem3Data>,onEventDispatchers:(ExchangeRatesContract.Intent)->Unit) {
    MySurface {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(onClickBack = { onEventDispatchers(ExchangeRatesContract.Intent.onClickBack)}, titleCenter = stringResource(id = R.string.exchange_rates_screen))

            LazyColumn(modifier = Modifier){
                items(data) {

                    Item(modifier =Modifier.padding(horizontal = 16.dp) , data = it, onClick ={

                    } )
                }
            }
        }
    }
}


@Preview
@Composable
fun ExchangeRatesPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        homeItem3Data.forEach{
            Item(modifier =Modifier , data = it, onClick ={

            } )
        }
    }
}

val homeItem3Data = arrayListOf(
    HomeItem3Data(R.drawable.flag_america, "USD", "12385", "12465"),
    HomeItem3Data(R.drawable.flag_euro, "EUR", "13000", "14000"),
    HomeItem3Data(R.drawable.flag_jpy, "JPY", "75", "95"),
    HomeItem3Data(R.drawable.flag_chf, "CHF", "13900", "14900"),
    HomeItem3Data(R.drawable.flag_gbp, "GBP", "15000", "16000")
)
@Composable
fun Item(modifier: Modifier, data: HomeItem3Data, onClick: (HomeItem3Data) -> Unit) {
        Column(
            modifier = modifier

        ) {
            Image(
                modifier= Modifier
                    .width(80.dp)
                    .padding(8.dp),
                painter = painterResource(id = data.icon),
                contentDescription =null
            )

            Row(modifier=Modifier.fillMaxWidth()) {
                Column(modifier= Modifier

                    .padding(vertical = 8.dp)
                    .padding(start = 8.dp)) {
                    Text(modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = stringResource(id = R.string.home_page_item3_valuta)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = data.valuta, textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(modifier= Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp)
                    .padding(start = 12.dp)) {
                    Text(modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = stringResource(id = R.string.home_page_item3_purchase)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = data.pokypka, textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(modifier= Modifier
                    .padding(vertical = 8.dp)
                    .padding(start = 12.dp)
                    .padding(end = 12.dp)) {
                    Text(modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = stringResource(id = R.string.home_page_item3_sale)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = data.prodaja, textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
}