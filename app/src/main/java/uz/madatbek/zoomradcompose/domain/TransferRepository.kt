package uz.madatbek.zoomradcompose.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.SMSTokenData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.StringData
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferDetail
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferHistoryResponse
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferRequest
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferVerifyRequest

interface TransferRepository {
    fun transferFOrCard(transferRequest: TransferRequest):Flow<Result<SMSTokenData>>

    fun transferVerify(transferVerify:TransferVerifyRequest):Flow<Result<StringData>>

    fun getTransferHistory(size:Int,currentPage:Int):Flow<PagingData<TransferDetail>>
}

//  suspend fun getTransferHistory(
//        @Query("size") size: Int,
//        @Query("current-page") currentPage: Int,
//        @Header("Authorization") authorization: String
//    ): Response<TransferHistoryResponse>