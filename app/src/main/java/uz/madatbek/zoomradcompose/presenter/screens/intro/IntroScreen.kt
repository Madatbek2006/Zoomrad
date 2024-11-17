package uz.madatbek.zoomradcompose.presenter.screens.intro

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
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
import uz.madatbek.zoomradcompose.data.model.IntroData
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
import uz.madatbek.zoomradcompose.utils.myLog

val data = arrayListOf(
    IntroData(
        title = R.string.intro_dizayn_title,
        message = R.string.intro_dizayn_message,
        icon = R.drawable.img_dizayn
    ),
    IntroData(
        title = R.string.intro_gr_title,
        message = R.string.intro_gr_message,
        icon = R.drawable.img_scan_qp
    ),
    IntroData(
        title = R.string.intro_transver_title,
        message = R.string.intro_transver_message,
        icon = R.drawable.img_transver_img
    ),
    IntroData(
        title = R.string.intro_onlay_chat_title,
        message = R.string.intro_onlay_chat_message,
        icon = R.drawable.img_onlay_chat
    ),
    IntroData(
        title = R.string.intro_sello_title,
        message = R.string.intro_sello_message,
        icon = R.drawable.img_sello
    ),
    IntroData(
        title = R.string.intro_strahovka_title,
        message = R.string.intro_strahovka_message,
        icon = R.drawable.img_car_strahovka
    ),
    IntroData(
        title = R.string.intro_bron_title,
        message = R.string.intro_bron_message,
        icon = R.drawable.img_bron
    ),
    IntroData(
        title = R.string.intro_inves_title,
        message = R.string.intro_inves_message,
        icon = R.drawable.img_invest
    ),
)

class IntroScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = getViewModel<IntroViewModel>()
        ZoomradTheme {
            IntroComponent(data, onEventDispatcher = screenModel::onEventDispatcher)
        }
    }
}


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroComponent(data: List<IntroData>, onEventDispatcher: (IntoContract.Intent) -> Unit) {
    val currentPage = remember {
        mutableStateOf(0)
    }
    val state = rememberPagerState {
        return@rememberPagerState data.size
    }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.zumrat))
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.bg_intro),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                state = state
            ) {
                currentPage.value = it
                "currentPage.value=> ${currentPage.value}".myLog()
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = stringResource(id = data[it].title),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 80.dp, vertical = 16.dp)
                            .aspectRatio(1f),
                        painter = painterResource(id = data[it].icon),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = stringResource(id = data[it].message),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Box(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 32.dp)

                .clickable {
                    onEventDispatcher(IntoContract.Intent.ScipInto)
                }){
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(8.dp)
                        .clickable {
                            onEventDispatcher(IntoContract.Intent.ScipInto)
                        }, text = "Пропустить",
                    color = Color.White
                )
            }

            /*  DotIndicator(
                  modifier = Modifier
                      .align(Alignment.BottomStart)
                      .padding(start = 16.dp, bottom = 32.dp),
                  totalDots = data.size, selectedIndex = currentPage.value,
                  activeColor = colorResource(id = R.color.zumrat2),
                  inactiveColor = Color.White
              )*/
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 32.dp)
            ) {
                repeat(data.size) { iteration ->
                    val color =
                        if (state.currentPage == iteration) colorResource(id = R.color.zumrat2) else Color.White
                        Box(modifier = Modifier
                            .padding(4.dp)
                            .size(8.dp)
                            .background(color = color, shape = CircleShape)

                        )
                }
            }

        }
    }
}

@Preview
@Composable
fun IntroPreview() {
    IntroComponent(data = data) {

    }
}