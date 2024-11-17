//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import kotlinx.coroutines.launch
//import uz.madatbek.zoomradcompose.presenter.screens.main.MainComponent
//import uz.madatbek.zoomradcompose.presenter.screens.main.MyDrawer
//
//@Composable
//fun HomeComponent() {
//    androidx.compose.material3.Surface(
//        color = MaterialTheme.colorScheme.background
//    ) {
//        // 1
//        val drawerState = rememberAnimatedDrawerState(
//            drawerWidth = 280.dp,
//        )
//        // 2
//        val scope = rememberCoroutineScope()
//        // 3
//        AnimatedDrawer(
//            modifier = Modifier.fillMaxSize(),
//            // 4
//            state = drawerState,
//            // 5
//            drawerContent = {
//                MyDrawer(
//                    modifier = Modifier.fillMaxSize(),
//                    onCloseClick = {
//                        // 6
//                        scope.launch { drawerState.close() }
//                    }
//                )
//            },
//            // 7
//            content = {
//                MainComponent(
//                    modifier = Modifier.fillMaxSize(),
//                    onOpenClick = {
//                        // 8
//                        scope.launch { drawerState.open() }
//                    }
//                )
//            }
//        )
//    }
//}