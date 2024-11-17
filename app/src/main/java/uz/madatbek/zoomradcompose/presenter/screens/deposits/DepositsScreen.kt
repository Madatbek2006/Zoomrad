package uz.madatbek.zoomradcompose.presenter.screens.deposits

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

class DepositsScreen:Screen {
    @Composable
    override fun Content() {
        val viewModel=getViewModel<DepositsViewModel>()
        ZoomradTheme {
            DepositsContent(viewModel::onEventDispatcher)
        }
    }
}

@Composable
fun DepositsContent(onEventDispatchers:(DepositsContract.Intent)->Unit) {
    MySurface {
        Column(modifier = Modifier.fillMaxSize()){
            TopBar(onClickBack = { onEventDispatchers(DepositsContract.Intent.onClickBack) }, titleCenter = stringResource(id = R.string.deposit_screen_txt))
        }
    }
}


@Preview
@Composable
fun DepositsPreview() {
    DepositsContent{
        
    }
}