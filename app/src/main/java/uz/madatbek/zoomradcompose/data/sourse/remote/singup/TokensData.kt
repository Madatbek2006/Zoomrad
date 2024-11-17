package uz.madatbek.zoomradcompose.data.sourse.remote.singup

import com.google.gson.annotations.SerializedName

data class TokensData(
    @SerializedName("refresh-token")
    val refreshToken:String,
    @SerializedName("access-token")
    val accessToken:String
)