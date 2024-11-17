package uz.madatbek.zoomradcompose.presenter.screens.main.pages.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.HomeItem1Data
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import uz.madatbek.zoomradcompose.data.model.HomeItem2Data
import uz.madatbek.zoomradcompose.data.model.HomeItem3Data
import uz.madatbek.zoomradcompose.presenter.screens.branchs.components.GoogleMapItem2
import uz.madatbek.zoomradcompose.presenter.screens.main.components.TopBarIntent
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.home.components.HomeBottomContent
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.utils.HomeContentNav
import uz.madatbek.zoomradcompose.utils.openMap
import uz.madatbek.zoomradcompose.utils.toManyFormat

object HomePage : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(id = R.string.home)
            val icon =
                rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_home))

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @SuppressLint("SuspiciousIndentation")
    @Composable
    override fun Content() {
        val viewModel= getViewModel<HomeViewModel>()
        HomeComponent(viewModel.collectAsState(),viewModel::onEventDispatchers)
        LaunchedEffect(key1 = Unit){
            viewModel.getTotalBalance()
        }
    }
}
@Composable
fun HomeComponent(uiState: State<HomeContract.UIState>,onEventDispatchers:(HomeContract.Intent)->Unit) {
    val isVisibleCost = remember { mutableStateOf(true) }
    var isUzb by remember {
        mutableStateOf(true)
    }
    HomeContentNav.isUzb={
        isUzb=it
    }

        MySurface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {

                    Box(modifier = Modifier.fillMaxSize()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 32.dp)
                                .padding(bottom = 8.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(color = colorResource(id = R.color.zumrat))
                        )
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 16.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                HomeContentNav.isOpen?.invoke()
                            }
                        ) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(id = R.drawable.bg_top),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(24.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.home_page_overall_balance),
                                    color = Color.White
                                )
                                Row(modifier = Modifier.padding(top = 8.dp)) {
                                    Icon(painter =
                                    if (isVisibleCost.value) {
                                        painterResource(id = R.drawable.eyes)
                                    } else {
                                        painterResource(id = R.drawable.eyes2)
                                    }, contentDescription = null,
                                        modifier = Modifier
                                            .height(24.dp)
                                            .width(24.dp)
                                            .clickable(
                                                onClick = {
                                                    isVisibleCost.value = !isVisibleCost.value
                                                },
                                                indication = rememberRipple(
                                                    bounded = false,
                                                    radius = 20.dp
                                                ),
                                                interactionSource = remember {
                                                    MutableInteractionSource()
                                                }
                                            ),
                                        tint = Color.White
                                    )
                                    Text(
                                        text =
                                        if (isVisibleCost.value) {
                                            if (isUzb){
                                                "${
                                                    uiState.value.totalBalance.toString().toManyFormat()
                                                } сум"
                                            }else{
                                                "0 USD"
                                            }
                                        } else {
                                            "***"
                                        },
                                        fontSize = 24.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(24.dp)
                            ) {
                                Image(
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .height(24.dp)
                                        .width(24.dp)
                                        .clip(RoundedCornerShape(300.dp))
                                        .background(Color.White)
                                        .padding(6.dp)
                                        .clickable(
                                            onClick = {
                                                onEventDispatchers(HomeContract.Intent.OpenViewCardsScreen)
                                            },
                                            indication = rememberRipple(
                                                bounded = false,
                                                radius = 20.dp,
                                            ),
                                            interactionSource = remember {
                                                MutableInteractionSource()
                                            }
                                        ),
                                    painter = painterResource(id = R.drawable.arrow_next),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(colorResource(id = R.color.zumrat))
                                )
                                Text(
                                    text = stringResource(id = R.string.home_page_add_card),
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(start = 8.dp),
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .weight(3f)
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    item {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            HomeItem1(
                                modifier = Modifier
                                    .weight(1f),
                                homeItem1Data[0],
                                onClick = {
                                    onEventDispatchers(HomeContract.Intent.OnClickItem1(it))
                                }
                            )
                            HomeItem1(
                                modifier = Modifier
                                    .weight(1f),
                                homeItem1Data[1],
                                onClick = {
                                    onEventDispatchers(HomeContract.Intent.OnClickItem1(it))
                                }
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            HomeItem1(
                                modifier = Modifier
                                    .weight(1f),
                                homeItem1Data[2],
                                onClick = {
                                    onEventDispatchers(HomeContract.Intent.OnClickItem1(it))
                                }
                            )
                            HomeItem1(
                                modifier = Modifier
                                    .weight(1f),
                                homeItem1Data[3],
                                onClick = {
                                    onEventDispatchers(HomeContract.Intent.OnClickItem1(it))
                                }

                            )
                        }
                    }
                    item {
                        Row {
                            Text(
                                text = stringResource(id = R.string.home_page_offer),
                                modifier = Modifier
                                    .padding(start = 12.dp)
                                    .padding(vertical = 16.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Box(modifier = Modifier
                                .clickable(
                                    onClick = {
                                        onEventDispatchers(HomeContract.Intent.OpenOffers(
                                            homeItem2Data
                                        ))
                                    },
                                    indication = rememberRipple(
                                        bounded = false,
                                        radius = 24.dp
                                    ),
                                    interactionSource = remember { MutableInteractionSource() }
                                ),
                            ){
                                Text(
                                    text = stringResource(id = R.string.all),
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .padding(vertical = 16.dp)
                                )
                            }
                        }

                        LazyRow {
                            homeItem2Data.forEach {
                                item {
                                    HomeItem2(modifier = Modifier, data = it, onClick = {

                                    })
                                }
                            }
                        }
                        Row {
                            Text(
                                text = stringResource(id = R.string.home_page_fast_payments),
                                modifier = Modifier
                                    .padding(start = 12.dp)
                                    .padding(vertical = 16.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Box(modifier = Modifier.clickable(
                                onClick = {
                                    onEventDispatchers
                                },
                                indication = rememberRipple(
                                    bounded = false,
                                    radius = 24.dp
                                ),
                                interactionSource = remember { MutableInteractionSource() }
                            ),
                            ){
                                Text(
                                    text = stringResource(id = R.string.all),
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .padding(vertical = 16.dp)
                                )
                            }
                        }


                        Card(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .width(100.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .height(80.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()){
                                Image(
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .width(32.dp),
                                    painter = painterResource(id = R.drawable.plus),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(Color.Gray)
                                )
                            }
                        }
                        Row {
                            Text(
                                text = stringResource(id = R.string.home_page_fast_payments),
                                modifier = Modifier
                                    .padding(start = 12.dp)
                                    .padding(vertical = 16.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Box(modifier = Modifier.clickable(
                                onClick = {
                                    onEventDispatchers(HomeContract.Intent.OpenWalute(homeItem3Data))
                                },
                                indication = rememberRipple(
                                    bounded = false,
                                    radius = 24.dp
                                ),
                                interactionSource = remember { MutableInteractionSource() }
                            ),) {
                                Text(
                                    text = stringResource(id = R.string.all),
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .padding(vertical = 16.dp)
                                )
                            }

                        }
                        LazyRow {
                            homeItem3Data.forEach {
                                item {
                                    HomeItem3(modifier = Modifier, data = it, onClick = {

                                    })
                                }
                            }
                        }
                        Box(modifier = Modifier.height(64.dp))
                    }

                }
            }

        }
}

@Composable
fun HomeItem1(modifier: Modifier, data: HomeItem1Data, onClick: ((HomeItem1Data) -> Unit)) {
    Card(
        modifier = modifier
            .padding(start = 4.dp, top = 8.dp, end = 4.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick(data) },
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 12.dp)
                    .padding(vertical = 12.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        color = colorResource(
                            id = R.color.zumrat
                        )
                    )
                    .padding(12.dp),
                painter = painterResource(id = data.icon), contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp, top = 12.dp),
            ) {
                Text(
                    text = stringResource(id = data.name),
                    fontSize = 10.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "${data.cost}",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = data.currency),
                    fontSize = 10.sp
                )

            }
        }

    }
}


@Composable
fun HomeItem2(modifier: Modifier, data: HomeItem2Data, onClick: ((HomeItem2Data) -> Unit)) {
    Box(
        modifier = modifier
            .padding(horizontal = 4.dp)
            .width(100.dp)
            .aspectRatio(0.8f)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.bg_home_item2), contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize()

        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
                    .width(80.dp)
                    .height(80.dp),
                painter = painterResource(id = data.icon),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier.fillMaxSize()) {
                androidx.compose.material.Text(
                    text = stringResource(id = data.name), modifier = Modifier.align(Alignment.TopCenter),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 10.sp
                )
            }
        }
    }

}

@Composable
fun HomeItem3(modifier: Modifier, data: HomeItem3Data, onClick: (HomeItem3Data) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier

        ) {
            Image(
                modifier= Modifier
                    .width(64.dp)
                    .align(Alignment.CenterVertically)
                    .padding(8.dp),
                painter = painterResource(id = data.icon),
                contentDescription =null
            )
            Column(modifier= Modifier
                .padding(vertical = 16.dp)
                .padding(start = 8.dp)) {
                Text(modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.home_page_item3_valuta)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = data.valuta, textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(modifier= Modifier
                .padding(vertical = 16.dp)
                .padding(start = 12.dp)) {
                Text(modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.home_page_item3_purchase)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = data.pokypka, textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(modifier= Modifier
                .padding(vertical = 16.dp)
                .padding(start = 12.dp)
                .padding(end = 12.dp)) {
                Text(modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.home_page_item3_sale)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = data.prodaja, textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}
val homeItem1Data = arrayListOf(
    HomeItem1Data(icon = R.drawable.ic_cashback_icon, cost = 0, currency = R.string.home_page_item1_ball, name =R.string.home_page_item1_ceshbek),
    HomeItem1Data(icon = R.drawable.ic_chest, cost = 0, currency = R.string.home_page_item1_sum, R.string.home_page_item1_kopilka),
    HomeItem1Data(icon = R.drawable.ic_deposits, cost = 0, currency = R.string.home_page_item1_sum, R.string.home_page_item1_vkladi),
    HomeItem1Data(icon = R.drawable.ic_credit_time, cost = 0, currency = R.string.home_page_item1_sum, R.string.home_page_item1_credit),
)
val homeItem2Data = arrayListOf(
    HomeItem2Data(icon = R.drawable.img_hadj, name = R.string.home_page_item2_payment_for_hajj, isNew = true),
    HomeItem2Data(icon = R.drawable.img_dizayn, name = R.string.home_page_item2_individual_design, isNew = true),
    HomeItem2Data(icon = R.drawable.ic_visa_direct, name = R.string.home_page_item2_VISA_direct, isNew = true),
    HomeItem2Data(icon = R.drawable.img_hadj, name = R.string.home_page_item2_payment_for_umra, isNew = false),
    HomeItem2Data(icon = R.drawable.img_car_strahovka, name = R.string.home_page_item2_apply_for_insurance, isNew = false),
    HomeItem2Data(icon = R.drawable.img_investitsya, name =R.string.home_page_item2_Investments , isNew = false),
    HomeItem2Data(icon = R.drawable.ic_bronirovanie, name = R.string.home_page_item2_bron, isNew = false),
    HomeItem2Data(icon = R.drawable.img_sello, name =R.string.home_page_item2_sello , isNew = false),
    HomeItem2Data(icon = R.drawable.ic_vklad, name = R.string.home_page_item2_open_vklad, isNew = false),
    HomeItem2Data(
        icon = R.drawable.img_uslugi,
        name = R.string.home_page_item2_state_online_services,
        isNew = false
    ),
    HomeItem2Data(icon = R.drawable.ic_visual_card, name = R.string.home_page_item2_virtual_cart, isNew = false),
//    HomeItem2Data(icon = R.drawable.humo, name = "HUMOPay", isNew = false),
)
val homeItem3Data = arrayListOf(
    HomeItem3Data(R.drawable.flag_america, "USD", "12385", "12465"),
    HomeItem3Data(R.drawable.flag_euro, "EUR", "13000", "14000"),
    HomeItem3Data(R.drawable.flag_jpy, "JPY", "75", "95"),
    HomeItem3Data(R.drawable.flag_chf, "CHF", "13900", "14900"),
    HomeItem3Data(R.drawable.flag_gbp, "GBP", "15000", "16000")
)

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeComponent(remember {
        mutableStateOf(HomeContract.UIState(1000))
    }){

    }
}