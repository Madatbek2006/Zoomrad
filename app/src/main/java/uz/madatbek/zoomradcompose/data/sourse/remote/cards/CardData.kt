package uz.madatbek.zoomradcompose.data.sourse.remote.cards

import com.google.gson.annotations.SerializedName
import retrofit2.http.POST

data class CardData(
    val id:Int,
    val name:String,
    val amount:Int,
    val owner:String,
    val pan:String,
    @SerializedName("expired-year")
    val expiredYear:Int,
    @SerializedName("expired-month")
    val expiredMonth:Int,
    @SerializedName("theme-type")
    val themeType:Int,
    @SerializedName("is-visible")
    val isVisible:Boolean
)