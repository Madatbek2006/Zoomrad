package uz.madatbek.zoomradcompose.presenter.screens.moneybox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.ui.components.TopBar
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme

class MoneyBoxScreen:Screen {
    @Composable
    override fun Content() {
        val viewModel=getViewModel<MoneyBoxViewModel>()
        ZoomradTheme {
            MoneyBoxContent(viewModel::onEventDispatcher)
        }
    }
}


@Composable
fun MoneyBoxContent(onEventDispatchers:(MoneyBoxContract.Intent)->Unit) {
    MySurface {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(onClickBack = { onEventDispatchers(MoneyBoxContract.Intent.onClickBack) }, titleCenter = stringResource(id = R.string.money_box_screen_what_are_we_going_to_save_for))
        }
    }
}

@Preview
@Composable
fun MoneyBoxPreview() {
    MoneyBoxContent{}
}