package uz.madatbek.zoomradcompose.data

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("first-name")
    val firstName:String,
    @SerializedName("last-name")
    val lastName:String,
    @SerializedName("gender-type")
    val genderType:String,
    @SerializedName("born-date")
    val bornDate:Long
)