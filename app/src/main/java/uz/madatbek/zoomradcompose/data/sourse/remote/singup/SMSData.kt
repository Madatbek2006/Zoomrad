package uz.madatbek.zoomradcompose.data.sourse.remote.singup

import com.google.gson.annotations.SerializedName

data class SMSData(
    @SerializedName("token")
    val token:String,
    @SerializedName("code")
    val code:String
)