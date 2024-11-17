package uz.madatbek.zoomradcompose.presenter.screens.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.help.HelpPage
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.home.HomePage
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.more.MorePage
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.payments.OutlinedTextExample
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.payments.PaymentsPage
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.transver.TransferPage

@Composable
fun NavigatorTopBar(tab:Tab,onEventDispatcher:(TopBarIntent)->Unit) {
    when (tab) {
        HomePage -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(16.dp)
                        .clickable(
                            onClick = {
                                onEventDispatcher(TopBarIntent.OPEN_DRAWER)
                            },
                            indication = rememberRipple(
                                bounded = false,
                                radius = 24.dp
                            ),
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(20.dp)
                        .clickable(
                            onClick = {
                                onEventDispatcher(TopBarIntent.OPEN_NOTIFY)
                            },
                            indication = rememberRipple(
                                bounded = false,
                                radius = 24.dp
                            ),
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                    painter = painterResource(id = R.drawable.ic_bell),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color(0xFF1E536D))
                )
            }
        }

        MorePage -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(16.dp)
                        .clickable(
                            onClick = {
                                onEventDispatcher(TopBarIntent.OPEN_DRAWER)
                            },
                            indication = rememberRipple(
                                bounded = false,
                                radius = 24.dp
                            ),
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = null
                )
                Text(
                    text = stringResource(id = R.string.main_screen_more),
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Gray
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(20.dp),
                    painter = painterResource(id = R.drawable.ic_menu_for_more),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = colorResource(id = R.color.zumrat))
                )
            }
        }

        TransferPage -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(16.dp)
                        .clickable(
                            onClick = {
                                onEventDispatcher(TopBarIntent.OPEN_DRAWER)
                            },
                            indication = rememberRipple(
                                bounded = false,
                                radius = 24.dp
                            ),
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = null
                )
                Text(
                    text = stringResource(id = R.string.main_screen_transver_card_in_card),
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Gray
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(20.dp).clickable(
                            onClick = {
                                onEventDispatcher(TopBarIntent.OPEN_NOTIFY)
                            },
                            indication = rememberRipple(
                                bounded = false,
                                radius = 24.dp
                            ),
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                    painter = painterResource(id = R.drawable.ic_bell),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color(0xFF1E536D))
                )
            }
        }

        PaymentsPage -> {
            val textState = remember { mutableStateOf("") }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(16.dp)
                        .clickable(
                            onClick = {
                                onEventDispatcher(TopBarIntent.OPEN_DRAWER)
                            },
                            indication = rememberRipple(
                                bounded = false,
                                radius = 24.dp
                            ),
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = null
                )

                OutlinedTextExample(
                    modifier = Modifier.padding(horizontal = 56.dp)
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(300.dp)), textState = textState,

                )
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(20.dp)
                        .clickable(
                            onClick = {
                                onEventDispatcher(TopBarIntent.OPEN_NOTIFY)
                            },
                            indication = rememberRipple(
                                bounded = false,
                                radius = 24.dp
                            ),
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                    painter = painterResource(id = R.drawable.ic_bell),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color(0xFF1E536D))
                )
            }
        }

        HelpPage -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(16.dp)
                        .clickable(
                            onClick = {
                                onEventDispatcher(TopBarIntent.OPEN_DRAWER)
                            },
                            indication = rememberRipple(
                                bounded = false,
                                radius = 24.dp
                            ),
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = null
                )
                Text(
                    text = stringResource(id = R.string.main_screen_help_support),
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Gray
                )
            }
        }
    }
}


enum class TopBarIntent{
    OPEN_DRAWER,OPEN_NOTIFY
}