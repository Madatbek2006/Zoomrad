package uz.madatbek.zoomradcompose.data.sourse.remote.cards

import com.google.gson.annotations.SerializedName

data class AddCardData(
    @SerializedName("pan")
    val pan:String,
    @SerializedName("expired-year")
    val expiredYear:String,
    @SerializedName("expired-month")
    val expiredMonth:String,
    @SerializedName("name")
    val name:String
)