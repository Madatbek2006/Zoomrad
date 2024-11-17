package uz.madatbek.zoomradcompose.presenter.screens.monitoring

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import org.orbitmvi.orbit.compose.collectAsState
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferDetail
import uz.madatbek.zoomradcompose.ui.components.TopBar
import uz.madatbek.zoomradcompose.presenter.screens.monitoring.component.MonitoringDateItem
import uz.madatbek.zoomradcompose.presenter.screens.monitoring.component.MonitoringItem
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
import uz.madatbek.zoomradcompose.utils.formatTimestamp

class MonitoringScreen : Screen {

    @Composable
    override fun Content() {
        val pageCount = remember {
            mutableStateOf(1)
        }


        val viewModel = getViewModel<MonitoringViewModel>()
        ZoomradTheme {
            HistoryComponent(
                viewModel.collectAsState(),
                viewModel.getHistory(size = 10, pageCount = pageCount.value)
                    .collectAsLazyPagingItems(),
                pageCount,
                viewModel::onEventDispatchers
            )
        }

    }

}


@Composable
fun HistoryComponent(
    uiState: State<MonitoringContract.UIState>,
    lazyItemsData: LazyPagingItems<TransferDetail>,
    pageCount: MutableState<Int>,
    onEventDispatchers: (MonitoringContract.Intent) -> Unit
) {

    var date=""
    var olpos=-1
    MySurface {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopBar(
                onClickBack = { onEventDispatchers(MonitoringContract.Intent.OnClickBack) },
                titleCenter = stringResource(id = R.string.drawer_monitoring_txt)
            )

//            if (uiState.value.isLoad){
//                LoadingComponent()
//            }else{
                LazyColumn {

                    items(
                        lazyItemsData.itemCount,
                        key = lazyItemsData.itemKey { it.time },
                        contentType = lazyItemsData.itemContentType { "contentType" }
                    ) {
                        val item = lazyItemsData[it]

                        var dateDay = item?.time?.formatTimestamp("dd:MM:yyyy")?:""
                        if (date != dateDay) { // "24.03" == 24.02
                            if (olpos<it){
                                MonitoringDateItem(data = dateDay)
                            }else{
                                MonitoringDateItem(data = dateDay)
                            }

                            date = dateDay
                            olpos=it
                        }
                        MonitoringItem(data = item) {
                        }
                    }
                }
//            }
        }
    }
}

@Composable
fun PageLoader(modifier: Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("loading.json"))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Box(modifier = modifier) {
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),

            )
    }
}

val data = arrayListOf(
    TransferDetail("income", "Muhammadali", "1234567898765420", 2000, 1671350649653),
    TransferDetail("outcome", "Muhammadali", "1234567898765420", 2000, 1671350649653),
    TransferDetail("income", "Muhammadali", "1234567898765420", 24000, 1671350649653),
    TransferDetail("outcome", "Muhammadali", "1234567898765420", 12000, 1671350649653),
    TransferDetail("income", "Muhammadali", "1234567898765420", 15600, 1671350649653),
)

@Preview
@Composable
fun HistoryPreview() {
//    HistoryComponent(,remember {
//        mutableStateOf(Monitoring.UIState.Monitoring(data))
//    }){
//
//    }
}


@SuppressLint("SimpleDateFormat")
fun groupAndSortByDay(transfers: List<TransferDetail>): Map<String, List<TransferDetail>> {
    return transfers.groupBy { transfer ->
        transfer.time.formatTimestamp("dd:mm:yyyy")
    }.mapValues { entry ->
        entry.value.sortedBy { it.time }
    }
}
