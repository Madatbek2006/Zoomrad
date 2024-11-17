package uz.madatbek.zoomradcompose.presenter.screens.cardsettings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.CardData
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.UpdateCard
import uz.madatbek.zoomradcompose.ui.components.LoadingComponent
import uz.madatbek.zoomradcompose.ui.components.SuccessComponent
import uz.madatbek.zoomradcompose.presenter.screens.addcard.data
import uz.madatbek.zoomradcompose.ui.components.CardComponent
import uz.madatbek.zoomradcompose.ui.components.DotIndicator
import uz.madatbek.zoomradcompose.ui.components.MyEditText
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.utils.visualtransformation.CardNumberVisualTransformation
import uz.madatbek.zoomradcompose.utils.visualtransformation.DateVisualTransformation

class CardSettingsScreen(private val cardData: CardData):Screen {
    @Composable
    override fun Content() {
        val viewModel=getViewModel<CardSettingsViewModel>()
        CardSettingsComponent(cardData,uiState =viewModel.collectAsState() , onEventDispatchers =viewModel::onEventDispatchers )
    }
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun CardSettingsComponent(cardData: CardData,uiState: State<CardSettingsContract.UIState>, onEventDispatchers:(CardSettingsContract.Intent)->Unit){
    val cardNumber= remember {
        mutableStateOf("000800080008"+cardData.pan)
    }
    val cardName= remember {
        mutableStateOf(cardData.name)
    }
    val cardDate= remember {
        mutableStateOf("${if (cardData.expiredMonth>9)cardData.expiredMonth else "0"+cardData.expiredMonth}${cardData.expiredYear%2000}")
    }
    val state= rememberPagerState {
        return@rememberPagerState data.size
    }


    MySurface(
        modifier = Modifier.fillMaxSize()
    ) {
        when(uiState.value){
            CardSettingsContract.UIState.Default->{
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier
                            .width(56.dp)
                            .height(56.dp)
                            .align(Alignment.CenterStart)
                            .clip(RoundedCornerShape(300.dp))
                            .clickable {
                                onEventDispatchers(CardSettingsContract.Intent.OnBackScreen)
                            }
                        ){
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
                            text = stringResource(id = R.string.edit_card), modifier = Modifier.align(
                                Alignment.Center),
                        )
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 4.dp)
                                .clickable {
                                    onEventDispatchers(CardSettingsContract.Intent.OnBackScreen)
                                },
                            text = stringResource(id = R.string.addCard_screen_skip),
                            fontSize = 12.sp,
                        )
                    }
                    HorizontalPager(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .fillMaxHeight(0.2f),
                        state = state
                    ) {
                        CardComponent(modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 32.dp),
                            data = data[it],
                            cardName, cardNumber, cardDate
                        )
                    }
                    DotIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 8.dp),
                        totalDots = 7, selectedIndex =state,
                        dotSize = 16,
                        activeColor = colorResource(id = R.color.zumrat2),
                        padding = 6
                    )

                    Text(modifier = Modifier.padding(start = 16.dp, top = 8.dp), text = stringResource(id = R.string.addCard_screen_card_number), fontSize = 10.sp)

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                            .padding(horizontal = 16.dp)
                            .height(56.dp)
                            .clip(
                                RoundedCornerShape(16.dp)
                            )
                    ){
                        MyEditText(
                            modifier = Modifier.fillMaxWidth(),
                            textState =cardNumber.value,
                            placeHolder = stringResource(id = R.string.addCard_screen_card_number),
                            textStyle = TextStyle(
                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            onValueChange = {
                                if (it.length>=17)return@MyEditText
                                cardNumber.value=it
                            },
                            visualTransformation = CardNumberVisualTransformation
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                                text = stringResource(id = R.string.addCard_screen_expiration_date),
                                fontSize = 10.sp
                            )

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp)
                                    .padding(horizontal = 16.dp)
                                    .height(56.dp)
                                    .clip(
                                        RoundedCornerShape(16.dp)
                                    )
                            ){
                                MyEditText(
                                    modifier = Modifier,
                                    textState =cardDate.value,
                                    placeHolder = "ММ/ГГ",
                                    textStyle = TextStyle(
                                    ),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    onValueChange = {
                                        if(it.length>=5)return@MyEditText
                                        cardDate.value=it
                                    },
                                    visualTransformation = DateVisualTransformation
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.weight(2f)
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                                text = stringResource(id = R.string.addCard_screen_card_name),
                                fontSize = 10.sp
                            )

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp)
                                    .padding(end = 16.dp)
                                    .height(56.dp)
                                    .clip(
                                        RoundedCornerShape(16.dp)
                                    )
                            ){
                                MyEditText(
                                    modifier = Modifier.fillMaxWidth(),
                                    textState =cardName.value,
                                    placeHolder = stringResource(id = R.string.addCard_screen_card_name),
                                    textStyle = TextStyle(
                                    ),
                                    onValueChange = {
                                        cardName.value=it
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp)
                            .padding(horizontal = 16.dp)
                            .height(56.dp)
                            .clip(
                                RoundedCornerShape(16.dp)
                            )
                            .background(

                                color = if (cardNumber.value.length == 16 && cardDate.value.length == 4 && cardName.value.length > 3){
                                    colorResource(id = R.color.zumrat)
                                }else{
                                    Color.Gray
                                }
                            )
                            .clickable {
                                if (cardNumber.value.length == 16 && cardDate.value.length == 4 && cardName.value.length > 3) {
                                    onEventDispatchers(
                                        CardSettingsContract.Intent.UpdateCard(
//                                            AddCardData(
//                                                pan = cardNumber.value.toString(),
//                                                expiredYear = (cardDate.value
//                                                    .substring(2)
//                                                    .toInt() + 2000).toString(),
//                                                expiredMonth = cardDate.value.substring(1, 2),
//                                                name = cardName.value
//                                            )
                                            card = UpdateCard(cardData.id, name = cardName.value,0,true)
                                        )
                                    )
                                    MyShar.setThemeCard(cardNumber.value,state.currentPage)
                                }
                            }
                    ){
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = stringResource(id = R.string.addCard_screen_add_card),
                            color = Color.White
                        )
                    }
                }
            }
            CardSettingsContract.UIState.Progress->{ LoadingComponent(Modifier.fillMaxSize())
            }
            CardSettingsContract.UIState.Success->{
                SuccessComponent(title = R.string.edit_card, isSuccess = true) {
                    onEventDispatchers(CardSettingsContract.Intent.OnBackScreen)
                }
            }
            CardSettingsContract.UIState.Fail->{
                SuccessComponent(title = R.string.edit_card, isSuccess = false) {
                    onEventDispatchers(CardSettingsContract.Intent.OnBackScreen)
                }
            }
        }

    }
}