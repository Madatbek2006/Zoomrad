package uz.madatbek.zoomradcompose.presenter.screens.main.pages.help

import android.Manifest
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.HelpItem1Data
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.utils.checkPermission
import uz.madatbek.zoomradcompose.utils.myLog

object HelpPage : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(id = R.string.help)
            val icon =
                rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_help))

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
        val context = LocalContext.current
        val viewModel = getViewModel<HelpViewModel>()
        HelpComponent(viewModel::onEventDispatchers) {
            context.checkPermission(listOf(Manifest.permission.CALL_PHONE)) {
                try {
                    val callIntent = Intent(Intent.ACTION_CALL)
                    callIntent.data = Uri.parse("tel:+998932320322")
                    startActivity(context, callIntent, bundleOf())
                } catch (e: Exception) {
                    Log.d("TTT", e.message.toString())
                }
            }
        }
    }

}

@Composable
fun HelpComponent(onEventDispatchers: (HelpContract.Intent) -> Unit, callBank: () -> Unit) {
    val context = LocalContext.current
    val isDontHaveLocation = remember {
        mutableStateOf(false)
    }
    MySurface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row {

                HelpItem1(helpItem1[0]) {
                    callBank()
                }
                HelpItem1(helpItem1[1]) {
                    "HelpItem1 clickble".myLog()
                    context.checkPermission(
                        listOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    ) {
                        onEventDispatchers(HelpContract.Intent.OpenMapScreen)
                    }
                }
            }

        }
    }
}


val helpItem1 = arrayListOf(
    HelpItem1Data(
        icon = R.drawable.ic_call,
        name = R.string.help_page_call_to_bank
    ),
    HelpItem1Data(
        icon = R.drawable.ic_location,
        name = R.string.help_page_bank_to_cart
    )
)

@Composable
fun HelpItem1(data: HelpItem1Data, onClickData: (HelpItem1Data) -> Unit) {
    Column(
        modifier = Modifier
            .padding(start = 32.dp, top = 24.dp)
            .width(64.dp)
    ) {
        Card(
            modifier = Modifier
                .width(64.dp)
                .height(64.dp)
                .clip(RoundedCornerShape(300.dp))
                .shadow(16.dp, spotColor = Color.Black)
                .clickable(
                    onClick = {
                        onClickData(data)
                    }
                ),
        ) {
            Image(
                modifier = Modifier.padding(18.dp),
                painter = painterResource(id = data.icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = colorResource(id = R.color.zumrat))
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 4.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = data.name),
            textAlign = TextAlign.Center,
            fontSize = 10.sp
        )
    }
}


fun isLocationEnabled(context: Context): Boolean {
    val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return lm.isProviderEnabled(LocationManager.GPS_PROVIDER) && lm.isProviderEnabled(
        LocationManager.NETWORK_PROVIDER
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHelpScreen() {
    HelpComponent(onEventDispatchers = {

    }) {

    }
}