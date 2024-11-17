package uz.madatbek.zoomradcompose.presenter.screens.main.pages.transver


import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.TransferItem1Data
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.CardData
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.GetCardOwnerByPanData
import uz.madatbek.zoomradcompose.presenter.screens.transfer.TransferUIData
import uz.madatbek.zoomradcompose.ui.components.CardComponentForView
import uz.madatbek.zoomradcompose.ui.components.DotIndicator
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.utils.myLog
import uz.madatbek.zoomradcompose.utils.visualtransformation.CardNumberVisualTransformation
import uz.madatbek.zoomradcompose.utils.visualtransformation.MoneyVisualTransformation

object TransferPage : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(id = R.string.transfer)
            val icon =
                rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_transfer))

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
        val viewModel = getViewModel<TransferViewModel>()
        val uiState = viewModel.collectAsState()
        TransferComponent(cardList = remember {
            mutableStateOf(cardList)
        }, uiState, viewModel::onEventDispatchers)
        viewModel.collectSideEffect {
            when (it) {
                is TransferContract.SideEffect.Toast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        LaunchedEffect(key1 = Unit) {
            viewModel.loadCards()
        }
    }

    val cardList = emptyList<CardData>()

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TransferComponent(
    cardList: MutableState<List<CardData>>,
    uiState: State<TransferContract.UIState>,
    onEventDispatchers: (TransferContract.Intent) -> Unit
) {
    var title by remember {
        mutableStateOf(R.string.transfer_page_min_sum)
    }
//    R.string.transfer_page_comissiya_free
    R.string.transfer_page_limit_txt
    val textState = remember { mutableStateOf(if(MyShar.isValid())"000800080008"+MyShar.getChaseCard() else "") }
    val costText = remember { mutableStateOf("") }

    if (textState.value.length == 16) {
        "onEventDispatchers".myLog()
        onEventDispatchers(
            TransferContract.Intent.GetCardByPan(
                    GetCardOwnerByPanData(textState.value)
            )
        )
    }
    val isSuccess = remember {
        mutableStateOf(false)
    }

    val cardName = remember {
        mutableStateOf("")
    }
    val state = rememberPagerState {
        return@rememberPagerState cardList.value.size
    }
    when (val data = uiState.value) {
        is TransferContract.UIState.Text -> {
            cardName.value = data.txt
            isSuccess.value = data.isSuccess
        }

        is TransferContract.UIState.LoadAllCards -> {
            cardList.value = data.data
        }
    }
    MySurface(
        modifier = Modifier.fillMaxSize(),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            TransferEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 16.dp)
                    .clip(
                        RoundedCornerShape(8.dp)
                    ),
                text = textState
            )
            if (textState.value.length == 16) {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                    text = if (cardName.value.isEmpty()) stringResource(id = R.string.transfer_page_wait) else cardName.value,
                    fontSize = 10.sp,
                    color = if (isSuccess.value) colorResource(id = R.color.zumrat) else colorResource(
                        id = R.color.zumrat_exception
                    )
                )
            }


            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 32.dp),
                text = stringResource(id = R.string.transfer_page_transfer_amount),
                fontSize = 12.sp
            )
            TextField(
                value = costText.value,
                onValueChange = {
                    if (it.length > 12) return@TextField
                    if (it.all { it.isDigit() }) {
                        costText.value = it
                    }
                },
                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.transfer_page_sum),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.zumrat)
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent, // Устанавливаем прозрачный цвет контейнера
                    unfocusedIndicatorColor = Color.Transparent, // Цвет индикатора, когда поле не в фокусе
                    focusedIndicatorColor = Color.Transparent  // Цвет индикатора, когда поле в фокусе
                ),
                visualTransformation = MoneyVisualTransformation()
            )
            if (textState.value.length==16){
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = stringResource(id = title),
                    color =
                    if (title==R.string.transfer_page_limit_txt){
                        colorResource(id = R.color.zumrat_exception)
                    }else{
                        colorResource(id = R.color.zumrat)
                    },
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center
                )
                var amount=0
                if (costText.value.isNotEmpty()){
                    amount=costText.value.toInt()
                }
                if (amount< 5000){

                    title=R.string.transfer_page_min_sum
                }else if (amount>15000000){
                    title=R.string.transfer_page_limit_txt
                }else{
                    title=R.string.transfer_page_comissiya_free
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ) {
                TransferItem(
                    modifier = Modifier.weight(1f),
                    data = TransferItem1Data(
                        R.drawable.ic_credit,
                        R.string.transfer_page_item_1
                    ),
                    onCLick = {
                        onEventDispatchers(TransferContract.Intent.OnClickItem1(it))
                    }
                )
                TransferItem(
                    modifier = Modifier.weight(1f),
                    data = TransferItem1Data(
                        R.drawable.ic_credit,
                        R.string.transfer_page_item_2
                    ),
                    onCLick = {
                        onEventDispatchers(TransferContract.Intent.OnClickItem1(it))
                    }
                )
            }
            if (cardList.value.isEmpty()) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .padding(top = 56.dp)
                        .height(128.dp)
                        .clip(
                            RoundedCornerShape(16.dp)
                        )
                        .background(colorResource(id = R.color.zumrat))
                ) {
                    Column(modifier = Modifier.align(Alignment.Center)) {
                        Image(
                            modifier = Modifier
                                .height(24.dp)
                                .width(24.dp)
                                .clip(RoundedCornerShape(300.dp))
                                .background(Color.White)
                                .padding(6.dp)
                                .align(Alignment.CenterHorizontally)
                                .clickable(
                                    onClick = {
                                        onEventDispatchers(TransferContract.Intent.OpenAddScreen)
                                    },
                                    indication = rememberRipple(
                                        bounded = false,
                                        radius = 20.dp,
                                    ),
                                    interactionSource = remember {
                                        MutableInteractionSource()
                                    }
                                ),
                            painter = painterResource(id = R.drawable.plus),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(colorResource(id = R.color.zumrat))
                        )
                        Text(
                            text = stringResource(id = R.string.transfer_page_add_card),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 8.dp),
                            color = Color.White
                        )
                    }
                }
            } else {
                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .padding(top = 56.dp)
                        .height(128.dp),
                    state = state
                ) {
                    CardComponentForView(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 32.dp),
                        data = cardList.value[it],
                    )
                }
                DotIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp),
                    totalDots = cardList.value.size, selectedIndex = state,
                    dotSize = 16,
                    activeColor = colorResource(id = R.color.zumrat2),
                    padding = 6
                )
            }


            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                color = if (isSuccess.value && costText.value.isNotEmpty() && cardList.value.isNotEmpty() && costText.value.toInt() >= 5000 && costText.value.toInt() <= 15000000) colorResource(
                                    id = R.color.zumrat
                                ) else Color.Gray
                            )
                            .clickable {
                                if (isSuccess.value && costText.value.isNotEmpty() && cardList.value.isNotEmpty() && costText.value.toInt() >= 5000 && costText.value.toInt() <= 15000000) {
                                    onEventDispatchers(
                                        TransferContract.Intent.OpenTransferScreen(
                                            data = TransferUIData(
                                                data = cardList.value[state.currentPage],
                                                name = cardName.value,
                                                amount = costText.value.toInt(),
                                                recipientCard = textState.value,
                                            )
                                        )
                                    )
                                }
                            }
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.Center),
                            text = stringResource(id = R.string.transfer_page_continue),

                            color = Color.White

                        )
                    }
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 16.dp),
                        text = stringResource(id = R.string.transfer_page_click_continue1),
                        fontSize = 10.sp
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = stringResource(id = R.string.transfer_page_click_continue2),
                        fontSize = 10.sp,
                        color = colorResource(id = R.color.zumrat)
                    )

                    Box(modifier = Modifier.height(64.dp))
                }
            }
        }
    }
}

@Composable
fun TransferItem(
    modifier: Modifier,
    data: TransferItem1Data,
    onCLick: ((TransferItem1Data) -> Unit)
) {
    Box(
        modifier = modifier
            .height(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onCLick(data)
            },
//                onClick = {
//
//                }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 12.dp)
                    .padding(vertical = 12.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .padding(12.dp),
                    painter = painterResource(id = data.icon), contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        color = colorResource(
                            id = R.color.zumrat
                        )
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp),
            ) {
                Text(

                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = stringResource(id = data.name),
                    fontSize = 10.sp
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferEditText(modifier: Modifier, text: MutableState<String>) {
    Card(
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            TextField(
                value = text.value,
                onValueChange = { newText ->
                    if (newText.length >= 17) return@TextField
                    text.value = newText
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = {
//                if (textState.value.isEmpty()){
                    Text(text = "Номер карты получателя", fontSize = 16.sp)
//                }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp, end = 56.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent, // Устанавливаем прозрачный цвет контейнера
                    unfocusedIndicatorColor = Color.Transparent, // Цвет индикатора, когда поле не в фокусе
                    focusedIndicatorColor = Color.Transparent  // Цвет индикатора, когда поле в фокусе
                ),
                visualTransformation = CardNumberVisualTransformation
            )
            Image(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.ic_scan),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorResource(id = R.color.zumrat))
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostEditTExt(modifier: Modifier, text: MutableState<String>) {
    TextField(
        value = text.value,
        onValueChange = { newText ->
            text.value = newText

        },
        label = {
//                if (textState.value.isEmpty()){
            Text(text = "0 сум", fontSize = 24.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
//                }
        },
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent, // Устанавливаем прозрачный цвет контейнера
            unfocusedIndicatorColor = Color.Transparent, // Цвет индикатора, когда поле не в фокусе
            focusedIndicatorColor = Color.Transparent  // Цвет индикатора, когда поле в фокусе
        )
    )
}


@Preview
@Composable
fun TransferPreview() {
    TransferComponent(
        remember {
            mutableStateOf(emptyList())
        },
        uiState = remember {
            mutableStateOf(TransferContract.UIState.InitUIState)
        }
    ) {

    }
}