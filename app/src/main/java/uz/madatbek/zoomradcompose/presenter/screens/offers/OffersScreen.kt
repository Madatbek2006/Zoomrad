package uz.madatbek.zoomradcompose.presenter.screens.offers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.HomeItem2Data
import uz.madatbek.zoomradcompose.ui.components.TopBar
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme

class OffersScreen(private val data:List<HomeItem2Data>):Screen {
    @Composable
    override fun Content() {
        val viewModel=getViewModel<OffersViewModel>()
        ZoomradTheme {
            OffersComponent(data,viewModel::onEventDispatcher)
        }
    }
}

@Composable
fun OffersComponent(data:List<HomeItem2Data>,onEventDispatchers:(OffersContract.Intent)->Unit) {
    MySurface {
        Column {
            TopBar(onClickBack = { onEventDispatchers(OffersContract.Intent.onClickBack) }, titleCenter = stringResource(id = R.string.offers_txt))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Указываем количество столбцов в сетке
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(data){
                    Item(modifier =Modifier.aspectRatio(1f), data =it, onClick ={

                    })
                }

            }
        }
    }
}

@Composable
fun Item(modifier: Modifier, data: HomeItem2Data, onClick: ((HomeItem2Data) -> Unit)) {
    Box(
        modifier = modifier
            .padding(horizontal = 4.dp)
            .padding(vertical = 8.dp)
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
                    .padding(8.dp)
                    .width(80.dp)
                    .height(80.dp),
                painter = painterResource(id = data.icon),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = stringResource(id = data.name), modifier = Modifier.align(Alignment.CenterStart).padding(start = 16.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 10.sp
                )
            }
        }
    }

}


@Preview
@Composable
fun OffersPreview() {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Указываем количество столбцов в сетке
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(homeItem2Data){
            Item(modifier =Modifier.aspectRatio(1f), data =it, onClick ={

            })
        }

    }
}
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