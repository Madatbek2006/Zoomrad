package uz.madatbek.zoomradcompose.data.sourse.remote.transver

import com.google.gson.annotations.SerializedName

data class TotalBalanceResponse(
    @SerializedName("total-balance")
    val totalBalance:Long
)