package uz.madatbek.zoomradcompose.data.sourse.remote.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("first-name")
    val firsName:String,
    @SerializedName("last-name")
    val lastName:String,
    @SerializedName("phone")
    val phone:String,
    @SerializedName("born-date")
    val bornDate:Long,
    @SerializedName("gender")
    val gender:Int
)