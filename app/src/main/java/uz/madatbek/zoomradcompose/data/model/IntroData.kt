package uz.madatbek.zoomradcompose.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class IntroData(
    @StringRes val title:Int,
    @DrawableRes val icon:Int,
    @StringRes val message:Int,
)