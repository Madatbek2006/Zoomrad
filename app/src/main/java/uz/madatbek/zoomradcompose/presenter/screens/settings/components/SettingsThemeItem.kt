package uz.madatbek.zoomradcompose.presenter.screens.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.ui.theme.ThemeType

@Composable
fun SettingsThemeItem(type:String,res:Int,isCheck:Boolean,onClick:(type:String)->Unit) {
    Row(
        modifier = Modifier
            .padding(start = 72.dp)
            .fillMaxWidth()
            .clickable {
                onClick(type)
            }
    ){
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = stringResource(id = res)
        )


        if(isCheck){
            Icon(
                modifier = Modifier.align(Alignment.CenterVertically).padding(start = 8.dp),
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                tint = colorResource(id = R.color.zumrat)
            )
        }
    }
}
@Composable
fun SettingsLanguageItem(type:String,txt:String,isCheck:Boolean,onClick:(type:String)->Unit) {
    Row(
        modifier = Modifier
            .padding(start = 72.dp)
            .clickable {
                onClick(type)
            }
    ){
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = txt
        )


        if(isCheck){
            Icon(
                modifier = Modifier.align(Alignment.CenterVertically).padding(start = 8.dp),
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                tint = colorResource(id = R.color.zumrat)
            )
        }
    }
}

@Preview
@Composable
fun SettingsThemeItemPreview() {
    SettingsThemeItem(type = ThemeType.LIGHT, res = R.string.settings_theme_txt1, isCheck = true, onClick ={} )
}