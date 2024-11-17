package uz.madatbek.zoomradcompose.presenter.screens.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.ui.components.TopBar
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme

class HistoryScreen:Screen {
    @Composable
    override fun Content() {
        val viewModel:HistoryContract.Model=getViewModel<HistoryViewModel>()
        ZoomradTheme {
            HistoryComponent(viewModel::onEventDispatchers)
        }
    }
}


@Composable
fun HistoryComponent(onEventDispatcher: (HistoryContract.Intent)->Unit) {
    MySurface {
        Column {
            Box {
                TopBar(
                    onClickBack = {
                        onEventDispatcher(HistoryContract.Intent.OnClickBack)
                    },
                    titleCenter = stringResource(id = R.string.history_screen_txt) )
            }

            Box(modifier = Modifier.fillMaxSize()){
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(64.dp),
                        painter = painterResource(id = R.drawable.ic_monitoring_icon),
                        contentDescription = null,
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = stringResource(id = R.string.history_screen_is_empty)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HistoryPreview() {
    HistoryComponent{

    }
}