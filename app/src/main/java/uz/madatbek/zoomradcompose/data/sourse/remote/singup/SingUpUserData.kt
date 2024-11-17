package uz.madatbek.zoomradcompose.data.sourse.remote.singup

import com.google.gson.annotations.SerializedName

data class SingUpUserData(
    @SerializedName("phone")
    val phone:String,
    @SerializedName("password")
    val password:String,
    @SerializedName("first-name")
    val firstName:String,
    @SerializedName("last-name")
    val lastName:String,
    @SerializedName("born-date")
    val bornDate:String,
    @SerializedName("gender")
    val gender:String
)

/*
{
    "phone": "+998993946280",
    "password": "qwerty",
    "first-name": "Muhammadali",
    "last-name": "Rahimberganov",
    "born-date": "969822000000",
    "gender": "0"
}
 */