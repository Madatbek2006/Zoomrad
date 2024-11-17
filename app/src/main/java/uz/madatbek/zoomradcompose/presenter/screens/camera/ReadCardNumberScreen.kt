//package uz.madatbek.zoomradcompose.presenter.screens.camera
//
//import android.Manifest
//import androidx.camera.core.ExperimentalGetImage
//import androidx.camera.view.CameraController
//import androidx.camera.view.LifecycleCameraController
//import androidx.camera.view.PreviewView
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.DisposableEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.core.content.ContextCompat
//import androidx.lifecycle.Lifecycle
//import androidx.lifecycle.LifecycleEventObserver
//import cafe.adriel.voyager.core.screen.Screen
//import cafe.adriel.voyager.hilt.getViewModel
//import com.google.accompanist.permissions.ExperimentalPermissionsApi
//import com.google.accompanist.permissions.rememberPermissionState
//import org.orbitmvi.orbit.compose.collectAsState
//import uz.madatbek.zoomradcompose.R
//import uz.madatbek.zoomradcompose.utils.card_number_reader.CardScanData
//import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
//import uz.madatbek.zoomradcompose.utils.card_number_reader.ImageAnalyzer
//import uz.madatbek.zoomradcompose.utils.containsOnlyNumbers
//
//@ExperimentalGetImage
//class ReadCardNumberScreen : Screen {
//    @Composable
//    override fun Content() {
//        ZoomradTheme {
//            val viewModel: ReadCardNumberContract.Model = getViewModel<ReadCardNumberModel>()
//            ReadCardNumberContent(
//                viewModel.collectAsState().value, viewModel::onEventDispatcher
//            )
//        }
//    }
//}
//
//
//@androidx.annotation.OptIn(ExperimentalGetImage::class)
//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//fun ReadCardNumberContent(
//    uiState: ReadCardNumberContract.UIState, onEventDispatcher: (ReadCardNumberContract.Intent) -> Unit
//) {
//    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val context = LocalContext.current
//    val listener: (CardScanData) -> Unit = {
//        //Toast.makeText(context, "LISTENER: $it", Toast.LENGTH_SHORT).show()
//
//        val number = it.number
//            .replace("\\s+".toRegex(), "")
//
//        val date = it.date
//            .replace("\\s+".toRegex(), "")
//            .replace("/", "")
//
//        if (
//            number.containsOnlyNumbers()
//            && date.containsOnlyNumbers()
//            && number.length == 16
//            && date.length == 4
//        ) { onEventDispatcher(ReadCardNumberContract.Intent.BackWithData(number, date)) }
//    }
//
//    var isFlashOn by remember { mutableStateOf(false) }
//    val analyzer by remember { mutableStateOf(ImageAnalyzer(listener)) }
//
//    DisposableEffect(key1 = null, effect = {
//        val observer = LifecycleEventObserver { _, event ->
//            if (event == Lifecycle.Event.ON_RESUME) cameraPermissionState.launchPermissionRequest()
//        }
//
//        lifecycleOwner.lifecycle.addObserver(observer)
//        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
//    })
//
//    val controller = remember {
//        LifecycleCameraController(context).apply {
//            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
//            setImageAnalysisAnalyzer(ContextCompat.getMainExecutor(context), analyzer)
//        }
//    }
//
//
//    Scaffold { paddingValues ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//        ) {
//            AndroidView(
//                modifier = Modifier.fillMaxSize(),
//                factory = {
//                    PreviewView(it).apply {
//                        this.controller = controller
//                        controller.bindToLifecycle(lifecycleOwner)
//                    }
//                },
//            )
//
//            Icon(
//                painter = painterResource(id = R.drawable.ic_back_ios),
//                contentDescription = null,
//                tint = Color.White,
//                modifier = Modifier
//                    .padding(start = 12.dp, top = 12.dp)
//                    .size(24.dp)
//                    .align(Alignment.TopStart)
//                    .clickable(interactionSource = remember { MutableInteractionSource() },
//                        indication = null,
//                        onClick = {
//                            onEventDispatcher(ReadCardNumberContract.Intent.Back)
//                        })
//            )
//
//            Icon(
//                painter = painterResource(
//                    id = if (isFlashOn) R.drawable.flash_on else R.drawable.flash_off
//                ),
//                contentDescription = null,
//                tint = Color.White,
//                modifier = Modifier
//                    .padding(end = 12.dp, top = 12.dp)
//                    .size(24.dp)
//                    .align(Alignment.TopEnd)
//                    .clickable(interactionSource = remember { MutableInteractionSource() },
//                        indication = null,
//                        onClick = {
//                            isFlashOn = !isFlashOn
//                            controller.enableTorch(isFlashOn)
//                        })
//            )
//
//            Column(
//                Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Box(
//                    modifier = Modifier
//                        .height(220.dp)
//                        .fillMaxWidth()
//                        .padding(12.dp)
//                        .border(
//                            border = BorderStroke(width = 2.dp, color = Color.White),
//                            shape = RoundedCornerShape(12.dp)
//                        )
//                )
//
//                Text(
//                    text = stringResource(R.string.scan_card), color = Color.White
//                )
//            }
//        }
//    }
//}
//
//@[Composable Preview]
//fun ReadCardNumberPreview() {
//    ReadCardNumberContent(
//        ReadCardNumberContract.UIState,
//        {},
//    )
//}