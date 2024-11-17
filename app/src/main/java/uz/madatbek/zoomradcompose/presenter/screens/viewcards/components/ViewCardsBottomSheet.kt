package uz.madatbek.zoomradcompose.presenter.screens.viewcards.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.presenter.screens.viewcards.ViewCardsContract
import uz.madatbek.zoomradcompose.ui.components.MySurface

@Composable
fun ViewCardsBottomSheet(onClickText:(Int)->Unit) {

    MySurface {
        Column(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp)))
        {
            Box(modifier = Modifier
                .padding(top = 16.dp)
                .width(32.dp)
                .height(8.dp)
                .clip(RoundedCornerShape(300.dp))
                .background(Color.Gray)
                .align(Alignment.CenterHorizontally))
            data.forEach {
                BottomSheetText(modifier = Modifier.fillMaxWidth(),data = it) {res->
                    onClickText(it)
                }
            }

        }

    }
}

val data= arrayListOf(
    R.string.addCard_screen_botrom_sheet_monitoring,
    R.string.addCard_screen_botrom_sheet_card_settings,
    R.string.addCard_screen_botrom_sheet_rekvizits,
    R.string.addCard_screen_botrom_sheet_make_the_main_one,
    R.string.addCard_screen_botrom_sheet_delete_card

)

@Composable
fun BottomSheetText(modifier: Modifier=Modifier, data:Int, color:Color= Color.Unspecified, onClick:(res:Int)->Unit) {

    Box(modifier = modifier
        .clickable (
           onClick={
               onClick(data)
           }
        )
    ){
        Text(modifier =Modifier.padding(16.dp).align(Alignment.Center),
            text = stringResource(id = data),
            fontWeight = FontWeight.Bold,
            color=color
        )

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(1.dp)
            .align(Alignment.BottomCenter)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant
            ))
    }
}


@Preview
@Composable
fun AddCardsBottomSheetPreview() {
    ViewCardsBottomSheet{

    }
}
