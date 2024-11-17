package uz.madatbek.zoomradcompose.data.sourse.remote.transver

import com.google.gson.annotations.SerializedName

data class TransferHistoryResponse(
    @SerializedName("total-elements") val totalElements: Int,
    @SerializedName("total-pages") val totalPages: Int,
    @SerializedName("current-page") val currentPage: Int,
    val child: List<TransferDetail>
)

data class TransferDetail(
    val type: String,
    val from: String,
    val to: String,
    val amount: Int,
    val time: Long
)