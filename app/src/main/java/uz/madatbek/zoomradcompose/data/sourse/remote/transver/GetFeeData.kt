package uz.madatbek.zoomradcompose.data.sourse.remote.transver

import com.google.gson.annotations.SerializedName

data class GetFeeData(
    @SerializedName("sender-id")
    val senderId:String,
    val receiver:String,
    val amount:Int
)