package uz.madatbek.zoomradcompose.data.sourse.remote.singIn

import com.google.gson.annotations.SerializedName

data class SingInUserData(
    @SerializedName("phone")
    val phone:String,
    @SerializedName("password")
    val password:String,
)