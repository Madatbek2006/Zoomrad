package uz.madatbek.zoomradcompose.presenter.screens.main.pages.more

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.MoreItem1Data
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme

object MorePage: Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(id = R.string.more)
            val icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_more))

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
        MoreContent()
    }
}

@Composable
fun MoreContent() {
    MySurface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {


            LazyVerticalGrid(
                columns
                = GridCells.Fixed(3), // Указываем количество столбцов в сетке
                contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
            ) {
                items(data){
                    MoreItem1(modifier = Modifier.weight(1f),it)
                }
                item {
                    Box(modifier = Modifier.height(64.dp))
                }
                item {
                    Box(modifier = Modifier.height(64.dp))
                }
            }
        }
    }
}


@Composable
fun MoreItem1(modifier: Modifier,data:MoreItem1Data){
    Card (
        modifier=modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
    ){
        Column(
            modifier = Modifier
        ) {
            Image(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 32.dp, end = 32.dp)
                    .aspectRatio(1f),
                painter = painterResource(id = data.icon),
                contentDescription =null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier=Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(id = data.name),
                fontSize = 10.sp,
            )
        }
    }

}

@Preview
@Composable
fun MorePevView() {
    MoreContent()
}
val data= arrayListOf(
    MoreItem1Data(R.drawable.ic_phone, R.string.payments_item1_1),
    MoreItem1Data(R.drawable.ic_network, R.string.payments_item1_2),
    MoreItem1Data(R.drawable.ic_call,  R.string.payments_item1_3),
    MoreItem1Data(R.drawable.ic_home, R.string.payments_item1_3),
    MoreItem1Data(R.drawable.ic_car,R.string.payments_item1_4),
    MoreItem1Data(R.drawable.ic_tv,R.string.payments_item1_5),
    MoreItem1Data(R.drawable.ic_credit,R.string.payments_item1_6),
    MoreItem1Data(R.drawable.ic_government,R.string.payments_item1_7),
    MoreItem1Data(R.drawable.ic_donation,R.string.payments_item1_8),
    MoreItem1Data(R.drawable.ic_basket,R.string.payments_item1_9),
    MoreItem1Data(R.drawable.ic_advertising,R.string.payments_item1_10),
    MoreItem1Data(R.drawable.ic_services,R.string.payments_item1_11),
    MoreItem1Data(R.drawable.ic_servis,R.string.payments_item1_12),
    MoreItem1Data(R.drawable.ic_book,R.string.payments_item1_13),
    MoreItem1Data(R.drawable.ic_wallet,R.string.payments_item1_14),
    MoreItem1Data(R.drawable.ic_puzzle,R.string.payments_item1_15),
    MoreItem1Data(R.drawable.ic_provider,R.string.payments_item1_16),
    MoreItem1Data(R.drawable.ic_basket,R.string.payments_item1_17),
    MoreItem1Data(R.drawable.ic_advertising,R.string.payments_item1_18),
    MoreItem1Data(R.drawable.ic_services,R.string.payments_item1_19),
    MoreItem1Data(R.drawable.ic_servis,R.string.payments_item1_20),
    MoreItem1Data(R.drawable.ic_book,R.string.payments_item1_21),
    MoreItem1Data(R.drawable.ic_wallet,R.string.payments_item1_22)
)