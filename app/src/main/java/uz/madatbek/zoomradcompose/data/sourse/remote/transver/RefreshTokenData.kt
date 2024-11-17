package uz.madatbek.zoomradcompose.data.sourse.remote.transver

import com.google.gson.annotations.SerializedName

data class RefreshTokenData (
    @SerializedName("refresh-token")
    val refreshToken:String
)