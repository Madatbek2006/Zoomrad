package uz.madatbek.zoomradcompose.presenter.screens.profile


import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.UserRequest
import uz.madatbek.zoomradcompose.ui.components.LoadingComponent
import uz.madatbek.zoomradcompose.ui.components.SuccessComponent
import uz.madatbek.zoomradcompose.ui.components.TopBar
import uz.madatbek.zoomradcompose.ui.components.MyEditText
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.components.UnidentifiedClient
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        val context= LocalContext.current
        val viewModel=getViewModel<ProfileViewModel>()

        val uiState =viewModel.collectAsState()
        ZoomradTheme {
            ProfileComponent(
                uiState,
                viewModel::onEventDispatchers,
                IsValidData(true)
            )
        }
        val sideEffect = viewModel.container.sideEffectFlow.collectAsState(initial = null)

        LaunchedEffect(sideEffect.value) {
            when (val effect = sideEffect.value) {
                is ProfileContract.SideEffect.Toast -> {
                    Toast.makeText(context,effect.message, Toast.LENGTH_SHORT).show()
                }

                else -> {

                }
            }
        }
    }
}

data class IsValidData(
    var bool:Boolean
)

@Composable
fun ProfileComponent(
    uiState:State<ProfileContract.UIState>,
   onEventDispatchers:(ProfileContract.Intent)->Unit,
    isValidData: IsValidData
) {
//    var isValid =true
    val name=remember {
        mutableStateOf("")
    }
    val firstName = remember {
        mutableStateOf("")
    }
    val lastName = remember {
        mutableStateOf("")
    }
    MySurface {
    when(val state=uiState.value){
        is ProfileContract.UIState.InitUIState->{
            if (isValidData.bool){
                firstName.value=if (state.data.firsName=="Test") "" else state.data.firsName
                lastName.value=if (state.data.lastName=="Test") "" else state.data.lastName
                name.value="${firstName.value} ${lastName.value}"
                isValidData.bool=false
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TopBar(onClickBack = { onEventDispatchers(ProfileContract.Intent.OnCLickBack)}, titleCenter = stringResource(id = R.string.profile_screen_txt))
                Spacer(modifier = Modifier.fillMaxHeight(0.05f))
                Box(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(140.dp)
                    .clip(RoundedCornerShape(300.dp))
                    .clickable {

                    }
                ) {
                    Image(
                        modifier = Modifier
                            .width(128.dp)
                            .align(Alignment.TopCenter)
                            .clip(RoundedCornerShape(300.dp)),
                        painter = painterResource(id = R.drawable.img_user),
                        contentDescription = null
                    )
                    Box(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                            .clip(RoundedCornerShape(360.dp))
                            .background(
                                color = colorResource(
                                    id = R.color.zumrat
                                )
                            )
                            .align(Alignment.BottomCenter)
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(6.dp)
                                .align(Alignment.Center),
                            painter = painterResource(id = R.drawable.ic_cam),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp),
                    text = name.value,
                    fontSize = 24.sp
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 32.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(colorResource(id = R.color.zumrat_exception))
                ) {
                    UnidentifiedClient(
                        modifier = Modifier.align(Alignment.Center),
                        textSize = 12,
                        iconSize = 16
                    )
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    MyEditText(
                        modifier = Modifier.fillMaxSize(), textState = firstName.value,
                        placeHolder = "Имя",
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Bold,
                        )
                        ,
                        onValueChange = {
                            firstName.value=it
                        }
                    )
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    MyEditText(
                        modifier = Modifier.fillMaxSize(), textState = lastName.value,
                        placeHolder = "Фамилия",
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Bold,
                        ),
                        onValueChange = {
                            lastName.value=it
                        }
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(48.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            text = "Дата рождения",
                            fontSize = 12.sp
                        )
                        Text(

                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(16.dp),
                            text = "22",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold

                        )
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(16.dp),
                            text = "Марта",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            text = "2006",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Icon(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .width(24.dp)
                            .height(24.dp),
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription =null,
                        tint = colorResource(id = R.color.zumrat)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = colorResource(id = R.color.zumrat))
                        .clickable {
                            onEventDispatchers(
                                ProfileContract.Intent.OnClickCheck(
                                    user = UserRequest(
                                        firstName = firstName.value,
                                        lastName = lastName.value,
                                        genderType = "0",
                                        bornDate = 0
                                    )
                                )
                            )
                            isValidData.bool = true
                        }
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Готово",
                        color = Color.White
                    )
                }

            }
        }
        ProfileContract.UIState.Success->{
            SuccessComponent(title =  R.string.profile_screen_txt, isSuccess = true) {
                onEventDispatchers(ProfileContract.Intent.OnCLickBack)
            }
        }
        ProfileContract.UIState.Progress->{
            LoadingComponent(modifier = Modifier.fillMaxSize())
        }

    }


    }
}


@Preview
@Composable
fun ProfilePreview() {
//    ProfileComponent(){}
}