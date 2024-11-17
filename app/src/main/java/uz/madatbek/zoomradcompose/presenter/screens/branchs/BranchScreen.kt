package uz.madatbek.zoomradcompose.presenter.screens.branchs

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.presenter.screens.branchs.components.GoogleMapItem
import uz.madatbek.zoomradcompose.presenter.screens.branchs.components.GoogleMapItem2
import uz.madatbek.zoomradcompose.presenter.screens.branchs.components.GoogleMapItem3
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
import uz.madatbek.zoomradcompose.utils.myLog
import uz.madatbek.zoomradcompose.utils.openMap

class BranchScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<BranchViewModel>()
        ZoomradTheme {
            MySurface {
                MyGoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    viewModel::onEventDispatchers,
                    uiState = viewModel.collectAsState(),
                )
            }
        }
    }

}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyGoogleMap(
    modifier: Modifier,
    onEventDispatchers: (BranchContract.Intent) -> Unit,
    uiState: State<BranchContract.UIState>
) {
    val isCard= remember { mutableStateOf(false) }
    val context = LocalContext.current
    val posLs = remember { mutableStateOf((uiState.value as BranchContract.UIState.InitUIState).data) }
    val myMarkerBitmap by lazy { bitmapDescriptorFromVector(context,R.drawable.ic_map_marker) }
    val regionType = remember { mutableStateOf(regionData[0]) }
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    val currentMarkerData= remember { mutableStateOf(MarkerData(posLs.value[0].lat,posLs.value[0].lng,"","","")) }
    val cameraPos = rememberCameraPositionState { position = CameraPosition.fromLatLngZoom(LatLng(currentMarkerData.value.lat, currentMarkerData.value.lng), 17f) }
    val isClickPos= remember { mutableStateOf(false) }
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
           GoogleMapItem2(data = currentMarkerData.value){
               coroutineScope.launch { sheetState.hide() }
               context.openMap(it)
           }
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(modifier = modifier) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier
                        .width(56.dp)
                        .height(56.dp)
                        .align(Alignment.CenterStart)
                        .clickable(
                            onClick = {
                                onEventDispatchers(BranchContract.Intent.OnClickBack)
                            },
                            indication = rememberRipple(
                                bounded = false,
                                radius = 36.dp
                            ),
                            interactionSource = remember { MutableInteractionSource() } // Источник взаимодействия
                        )
                    ) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp)
                                .align(Alignment.Center),
                            painter = painterResource(id = R.drawable.ic_back_ios),
                            contentDescription = null,
                            tint = colorResource(id = R.color.zumrat)
                        )
                    }

                    Text(
                        text = regionType.value, modifier = Modifier.align(Alignment.Center),
                        color = Color.Gray
                    )

                    Box(modifier = Modifier
                        .width(56.dp)
                        .height(56.dp)
                        .align(Alignment.CenterEnd)
                        .clickable(
                            onClick = {
                                isCard.value = !isCard.value
                            },
                            indication = rememberRipple(
                                bounded = false,
                                radius = 36.dp
                            ),
                            interactionSource = remember { MutableInteractionSource() } // Источник взаимодействия
                        )
                    ) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp)
                                .align(Alignment.Center),
                            painter = painterResource(id =if(!isCard.value)R.drawable.ic_map else R.drawable.ic_menu_for_more),
                            contentDescription = null,
                            tint = colorResource(id = R.color.zumrat)
                        )
                    }
                }

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))){
                    Box(modifier = Modifier.fillMaxSize()){
                        Text(modifier = Modifier
                            .padding(start = 16.dp)
                            .padding(top = 8.dp)
                            .padding(bottom = 8.dp)
                            .align(Alignment.CenterStart),

                            text = "Все",
                            fontSize = 12.sp
                        )
                        Icon(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .width(16.dp)
                                .height(16.dp)
                                .align(Alignment.CenterEnd),
                            painter = painterResource(id = R.drawable.arrow_down),
                            contentDescription = null,
                            tint = colorResource(id = R.color.zumrat)
                        )
                    }
                }
                LazyRow(modifier=Modifier.padding(bottom = 16.dp)){
                    items(regionData){
                        GoogleMapItem(isCheck =regionType.value==it, data = it){data->
                            regionType.value=data
                            onEventDispatchers(BranchContract.Intent.ClickType(data))
                        }
                    }
                }
            }
            if (isCard.value){
                GoogleMap(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    cameraPositionState = cameraPos,
                    uiSettings = MapUiSettings(myLocationButtonEnabled = true, zoomControlsEnabled = false),
                    properties = MapProperties(isMyLocationEnabled = true, isBuildingEnabled = true, isTrafficEnabled = true),
                ) {
                    posLs.value.forEach {markerData->
                        Marker(
                            state = MarkerState(position = LatLng(markerData.lat, markerData.lng)),
                            onClick = {
                                currentMarkerData.value=markerData
                                coroutineScope.launch { sheetState.show() }
                                isClickPos.value=true
                                return@Marker true
                            },
                            icon = myMarkerBitmap,
                        )
                    }
                }
                "isClickPos.value=> ${isClickPos.value}".myLog()
                if (isClickPos.value){

                    LaunchedEffect(key1 = currentMarkerData.value) {
                        cameraPos.animate(CameraUpdateFactory.newLatLngZoom(LatLng(currentMarkerData.value.lat,currentMarkerData.value.lng), 17f))
                        isClickPos.value=false
                        coroutineScope.launch { sheetState.show() }
                    }
                }
            } else{
                LazyColumn(
                    modifier= Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ){
                    items(posLs.value){
                        GoogleMapItem3(data = it){
                            isCard.value=true
                            currentMarkerData.value=it
                            isClickPos.value=true
                        }
                    }
                }
            }

        }
    }
    when (val state=uiState.value) {
        is BranchContract.UIState.InitUIState -> {
            posLs.value = state.data

        }
    }
}

data class MarkerData(
    val lat: Double,
    val lng: Double,
    val name: String,
    val adress:String,
    val time:String
)






@Preview
@Composable
fun GoogleMapPreview() {
    MyGoogleMap(
        modifier = Modifier.fillMaxSize(),
        {},
        remember {
            mutableStateOf(BranchContract.UIState.InitUIState(emptyList()))
        }
    )
}


val regionData=listOf("Филиалы","Центры К.О","Банкоматы","Обменные пункты")
fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    return ContextCompat.getDrawable(context, vectorResId)?.run {
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        draw(android.graphics.Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}
