package uz.madatbek.zoomradcompose.data.sourse.remote.transver

import com.google.gson.annotations.SerializedName

data class TransferRequest(
    val type:String,
    @SerializedName("sender-id")
    val senderId:String,
    @SerializedName("receiver-pan")
    val receiverPan:String,
    val amount:Long
)



/*
{
    "type": "third-card",
    "sender-id": "7",
    "receiver-pan": "1234567898765432",
    "amount": 100100
}
*/