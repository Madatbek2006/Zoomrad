package uz.madatbek.zoomradcompose.presenter.screens.viewcards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.TransferItem1Data
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.CardData
import uz.madatbek.zoomradcompose.ui.components.LoadingComponent
import uz.madatbek.zoomradcompose.presenter.screens.viewcards.components.DeleteCardDialog
import uz.madatbek.zoomradcompose.presenter.screens.viewcards.components.ViewCardsBottomSheet
import uz.madatbek.zoomradcompose.ui.components.CardComponentForView
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
import uz.madatbek.zoomradcompose.utils.myLog


class ViewCardsScreen:Screen {
    @Composable
    override fun Content() {
        val viewModel=getViewModel<ViewCardsViewModel>()
        ZoomradTheme {
            ViewCardsComponent(viewModel.collectAsState().value,viewModel::onEventDispatcher)
        }

        LaunchedEffect(key1 = null) {
            viewModel.loadCards()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ViewCardsComponent(uiState: ViewCardsContract.UIState,onEventDispatchers:(ViewCardsContract.Intent)->Unit) {
    val cardData= remember {
        mutableStateOf(CardData(0,"",0,"","",1,1,1,false))
    }
    MySurface(
        modifier = Modifier.fillMaxSize()
    ) {
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val coroutineScope = rememberCoroutineScope()

        if (uiState.progress){
            LoadingComponent(modifier = Modifier.fillMaxSize())
        }else{
            ModalBottomSheetLayout(
                sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                sheetState =sheetState ,
                sheetContent = {
                    ViewCardsBottomSheet{
                        coroutineScope.launch { sheetState.hide() }
                        onEventDispatchers(ViewCardsContract.Intent.BottomSheetAction(it,cardData.value))
                    }
                }) {
                Box(modifier = Modifier.fillMaxSize()){
                    Column {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Box(modifier = Modifier
                                .width(56.dp)
                                .height(56.dp)
                                .align(Alignment.CenterStart)
                                .clickable(
                                    onClick = {
                                        onEventDispatchers(ViewCardsContract.Intent.OnCLickBack)
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
                                        .padding(16.dp)
                                        .align(Alignment.Center),
                                    painter = painterResource(id = R.drawable.ic_back_ios),
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.zumrat)
                                )
                            }

                            Text(
                                text = stringResource(id = R.string.view_cards_my_cards),
                                modifier = Modifier.align(Alignment.Center),
                                color = Color.Gray
                            )
                        }

                        LazyColumn(
                            modifier = Modifier
                                .padding(bottom = 80.dp),
                            contentPadding = PaddingValues(bottom = 80.dp),
                        ) {
                            items(uiState.data) {
                                "cardData=> ${it.id}".myLog()
                                CardComponentForView(
                                    modifier = Modifier
                                        .padding(top = 16.dp)
                                        .fillMaxSize()
                                        .padding(horizontal = 32.dp),
                                    data = it
                                ){
                                    cardData.value=it
                                    coroutineScope.launch {  sheetState.show() }
                                }

                            }
                        }
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)) {
                        ViewCardsItem(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp, end = 8.dp),
                            data = TransferItem1Data(
                                R.drawable.ic_virtual_card,
                                R.string.view_cards_add_virtual_card
                            ),
                            onCLick = {})
                        ViewCardsItem(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp, end = 16.dp),
                            data = TransferItem1Data(
                                R.drawable.ic_add_card,
                                R.string.view_cards_add_virtual_card
                            ),
                            onCLick = { onEventDispatchers(ViewCardsContract.Intent.OpenAddScreen) })
                    }
                }

            }
        }
        DeleteCardDialog(showDialog = uiState.dialog, onDismiss = {
            onEventDispatchers(ViewCardsContract.Intent.DismissDialog)
        }
        ) {
            onEventDispatchers(ViewCardsContract.Intent.DeleteCard(cardData.value.id.toString()))
        }
    }
}

@Composable
fun ViewCardsItem(
    modifier: Modifier,
    data: TransferItem1Data,
    onCLick: (() -> Unit)
) {
    Card(
        modifier = modifier
            .height(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onCLick()
            } ,

    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 12.dp)
                    .padding(vertical = 12.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(12.dp),
                painter = painterResource(id = data.icon), contentDescription = null,
                colorFilter = ColorFilter.tint(
                    color = colorResource(
                        id = R.color.zumrat
                    )
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp),
            ) {
                Text(

                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = stringResource(id =data.name),
                    fontSize = 10.sp
                )
            }
        }

    }
}



val data= arrayListOf(
    CardData(1,"",1222,"","0008 0008 0008 0008",324124,134,R.drawable.img_card4,true),
    CardData(1,"",1222,"","0008 0008 0008 0008",324124,134,R.drawable.img_card4,true),
    CardData(1,"",1222,"","0008 0008 0008 0008",324124,134,R.drawable.img_card4,true),
)
@Preview
@Composable
fun ViewCardsPreview() {
    ViewCardsComponent(ViewCardsContract.UIState(data = data)){

    }
}
