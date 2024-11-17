package uz.madatbek.zoomradcompose.data.sourse.remote.cards

import com.google.gson.annotations.SerializedName

data class UpdateCard(
    val id:Int,
    val name:String,
    @SerializedName("theme-type")
    val themeType:Int,
    @SerializedName("is-visible")
    val isVisible:Boolean,

)