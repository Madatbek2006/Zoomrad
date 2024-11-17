package uz.madatbek.zoomradcompose.presenter.screens.main

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabNavigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.DrawerData
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.presenter.screens.main.components.LogOutDialog
import uz.madatbek.zoomradcompose.presenter.screens.main.components.NavigatorTopBar
import uz.madatbek.zoomradcompose.presenter.screens.main.components.TopBarIntent
import uz.madatbek.zoomradcompose.presenter.screens.main.draver.DrawerValue
import uz.madatbek.zoomradcompose.presenter.screens.main.draver.MyModalDrawer
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.help.HelpPage
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.home.HomePage
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.more.MorePage
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.payments.OutlinedTextExample
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.payments.PaymentsPage
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.transver.TransferPage
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
import uz.madatbek.zoomradcompose.utils.TabNavigatorItem
import uz.madatbek.zoomradcompose.presenter.screens.main.draver.MyDrawer
import uz.madatbek.zoomradcompose.presenter.screens.main.draver.rememberDrawerState
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.home.components.HomeBottomContent
import uz.madatbek.zoomradcompose.ui.theme.ThemeType
import uz.madatbek.zoomradcompose.utils.HomeContentNav

class MainScreen : Screen {
    init {
        MainScreenStatus.isComposition.tryEmit(true)
    }
    @Composable
    override fun Content() {
        val viewModel = getViewModel<MainViewModel>()
        ZoomradTheme {
            HomeComponent(viewModel.collectAsState(), viewModel::onEventDispatcher)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainComponent(
    block: OnClickHelpPage,
    onEventTopBarDispatchers: (TopBarIntent) -> Unit,
) {

    TabNavigator(tab = HomePage) {
        Scaffold(
            backgroundColor = MaterialTheme.colorScheme.background,
            drawerBackgroundColor = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                NavigatorTopBar(tab = it.current, onEventDispatcher = onEventTopBarDispatchers)
            },
            content = {
                CurrentTab()
            },
            bottomBar = {
                BottomNavigation(
                    backgroundColor =
                    when (MyShar.getTheme()) {
                        ThemeType.LIGHT -> Color.White
                        ThemeType.NIGHT -> Color(0xFF333A43)
                        ThemeType.DARK -> Color(0xFF202022)
                        ThemeType.SYSTEM -> if(isSystemInDarkTheme())Color(0xFF37474F) else Color.White
                        else->Color(0xFF37474F)
                    },
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    val tabNavigator = LocalTabNavigator.current
                    block.block = {
                        tabNavigator.current = HelpPage
                    }
                    TabNavigatorItem(tab = HomePage)
                    TabNavigatorItem(tab = PaymentsPage)
                    TabNavigatorItem(tab = TransferPage)
                    TabNavigatorItem(tab = HelpPage)
                    TabNavigatorItem(tab = MorePage)
                }
            },
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeComponent(
    uiState: State<MainContract.UIState>,
    onEventDispatchers: (MainContract.Intent) -> Unit
) {
    var showDialog by remember {
        mutableStateOf(false)
    }
    when(uiState.value){
        MainContract.UIState.ShowDialog->{
            showDialog=true
        }
        MainContract.UIState.Default->{
            showDialog=false
        }
    }
    val block by remember {
        mutableStateOf(OnClickHelpPage())
    }

    val drawerState =
        rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val intWith = configuration.screenWidthDp
    val screenWidth = intWith.dp
    val drawerWidth = screenWidth * 0.5f
    val drawerWidthPx = with(density) { drawerWidth.toPx() }

    var lastOffset = 0f
    val drawerOffsetFlow = remember {
        snapshotFlow {
            drawerState.offset
        }.filter { _ ->
            return@filter true
        }
    }
    var oldProgress = 0.1f
    var progress: Float = 0f
    progress = if ((oldProgress * 10).toInt() != (progress * 10).toInt()) {
        oldProgress = progress
        drawerOffsetFlow.collectAsState(initial = 0f).value / drawerWidthPx
    } else {
        progress
    }
    val targetValue = 1 + ((0.9 - 1) * (progress + 2)) / (0 + 2)
    val scale = animateFloatAsState(targetValue = targetValue.toFloat(), label = "")
    val xOffset = animateFloatAsState(
        targetValue = (((intWith * 0.9f) * (progress + 2)) / 2).toFloat(),
        label = ""
    )

    var isUzb by remember {
        mutableStateOf(true)
    }
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    HomeContentNav.isOpen = {
        coroutineScope.launch { sheetState.show() }
    }


    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            HomeBottomContent(isUzb) {
                isUzb = it
                HomeContentNav.isUzb?.invoke(it)
            }
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {

        MyModalDrawer(
            drawerState = drawerState,
            drawerContent = {
                MyDrawer(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.85f),
                    onClickProfile = {
                        scope.launch {
                            drawerState.close()
                            onEventDispatchers(MainContract.Intent.OpenProfileScreen)
                        }

                    },
                    onClickDrawerData = {
                        if (it.name == R.string.drawer_help_txt) {
                            block.block?.invoke()
                        }
                        scope.launch {
                            drawerState.close()
                            onEventDispatchers(MainContract.Intent.OnCLickDrawerData(it))
                        }

                    }
                )
            },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)

                ) {
                    Box(
                        modifier = Modifier
//                            .graphicsLayer {
//                                scaleX = scale.value
//                                scaleY = scale.value
//                                translationX = xOffset.value
//                            }
                            .fillMaxSize()
                    ) {
                        MainComponent(block, onEventTopBarDispatchers =  { intent ->
                            when (intent) {
                                TopBarIntent.OPEN_DRAWER -> {
                                    scope.launch {
                                        if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                    }
                                }

                                TopBarIntent.OPEN_NOTIFY -> {
                                    onEventDispatchers(MainContract.Intent.OpenScreenNotify)
                                }
                            }

                        },)

                        LogOutDialog(showDialog = showDialog,
                            onDismiss = {
                                onEventDispatchers(MainContract.Intent.DialogDismiss)
                            }
                            ,
                            onConfirm = {
                                onEventDispatchers(MainContract.Intent.LogOut)
                            }
                        )

                    }
                }
            }
        )
    }


}


@Preview
@Composable
fun PreviewAnimation() {
    HomeComponent(remember {
        mutableStateOf(MainContract.UIState.Default)
    }) {

    }
}


val drawerData = arrayListOf(
    DrawerData(name = R.string.drawer_profile_txt, icon = R.drawable.ic_user),
    DrawerData(R.string.drawer_settings_txt, R.drawable.ic_settings),
    DrawerData(R.string.drawer_history_txt, R.drawable.ic_history_new),
    DrawerData(R.string.drawer_monitoring_txt, R.drawable.ic_monitoring_icon),
    DrawerData(R.string.drawer_help_txt, R.drawable.ic_chat),
    DrawerData(R.string.drawer_share_txt, R.drawable.ic_share),
    DrawerData(R.string.drawer_exit_txt, R.drawable.ic_exit),
)

class OnClickHelpPage {
    var block: (() -> Unit)? = null
}
object MainScreenStatus{
    val isComposition= MutableStateFlow(false)
}
