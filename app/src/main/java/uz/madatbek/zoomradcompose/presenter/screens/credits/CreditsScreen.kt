package uz.madatbek.zoomradcompose.presenter.screens.credits

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.components.TopBar
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme

class CreditsScreen:Screen {
    @Composable
    override fun Content() {
        val viewModel=getViewModel<CreditsViewModel>()
        ZoomradTheme {
            CreditsContent(viewModel::onEventDispatcher)
        }
    }
}


@Composable
fun CreditsContent(onEventDispatcher:(CreditsContract.Intent)->Unit) {
    MySurface {
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            TopBar(onClickBack = {onEventDispatcher(CreditsContract.Intent.onClickBack)}, titleCenter = stringResource(id =R.string.credit_screen_txt)) }
    }
}
