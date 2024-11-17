package uz.madatbek.zoomradcompose.ui.components
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.madatbek.zoomradcompose.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DotIndicator(
    modifier: Modifier,
    totalDots: Int,
    selectedIndex: PagerState,
    activeColor: Color = Color.Blue,
    inactiveColor: Color = Color.Gray,
    dotSize: Int = 8,  // размер точки в dp
    padding:Int=4
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalDots) { iteration ->
            Box(
                modifier = Modifier
                    .padding(padding.dp)
                    .width(dotSize.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(300.dp))
                    .background(
                        color = if (iteration == selectedIndex.currentPage) activeColor else inactiveColor,
                    )
            )
        }
    }
}

@Composable
fun UnidentifiedClient(
    modifier: Modifier,
    textSize: Int = 10,
    textAlign: TextAlign? = null,
    iconSize: Int = 8
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFC55250))
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(vertical = 12.dp)
                .padding(start = 12.dp),
            text = "Неидентифицированный клиент",
            fontSize = textSize.sp,
            color = Color.White,
            textAlign = textAlign
        )
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .size(iconSize.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = R.drawable.ic_question_mark), contentDescription = null,
            tint = Color.White
        )

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyEditText(
    modifier: Modifier,
    textSize: Int = 16,
    textAlign: TextAlign? = null,
    color: Color = Color.Black,
    textState: String,
    maxLength: Int = -1,
    textStyle: TextStyle = LocalTextStyle.current,
    placeHolder:String="",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default ,
    onValueChange:((String)->Unit),
            visualTransformation:VisualTransformation=VisualTransformation.None
) {
    TextField(
        visualTransformation =visualTransformation ,
        modifier = modifier,
        value = textState,
        onValueChange = {
            onValueChange(it)
        }
//        if (onValueChange!=null)
//            onValueChange()
//        else {
//
//            {
//                if (maxLength == -1) textState.value = it
//                else if (maxLength > it.length) {
//                    textState.value = it
//                }
//            }
//        },
                ,
        placeholder ={
            if (placeHolder!=""){
                Text(
                    fontSize = textSize.sp,
                    text =placeHolder,
                )
            }
        },
        textStyle =textStyle,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent, // Устанавливаем прозрачный цвет контейнера
            unfocusedIndicatorColor = Color.Transparent, // Цвет индикатора, когда поле не в фокусе
            focusedIndicatorColor = Color.Transparent,  // Цвет индикатора, когда поле в фокусе
            cursorColor = colorResource(id = R.color.zumrat),
        ),
        keyboardOptions=keyboardOptions
    )
}
@Composable
fun SimpleSwitch() {
    var isChecked by remember { mutableStateOf(false) }

    Row(modifier = Modifier.padding(16.dp)) {
        Text(
            text = if (isChecked) "Включено" else "Выключено",
            modifier = Modifier.padding(end = 8.dp)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            colors = SwitchDefaults.colors(

            ),
        )
    }
}




@Preview
@Composable
fun SwitchPreview(){
    SimpleSwitch()
}



