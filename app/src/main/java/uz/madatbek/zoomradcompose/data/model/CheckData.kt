package uz.madatbek.zoomradcompose.data.model

class CheckData(
    val recipient:String,
    val toCardPan:String,
    val fromCardPan:String,
    val amount:Int,
    val time:Long,
    val merchant:String,
    val terminal:String,
    val commission:Int=0,
    val status:String
)