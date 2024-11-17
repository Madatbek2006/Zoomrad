package uz.madatbek.zoomradcompose.data.sourse.remote.api

import BaseChildResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.SMSTokenData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.StringData
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.GetCardOwnerByPanData
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.GetFeeData
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.ResultFeeData
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferDetail
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferHistoryResponse
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferRequest
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferVerifyRequest

interface TransferApi {
    @POST("mobile-bank/v1/transfer/card-owner")
    suspend fun getCardOwnerByPan(@Body card: GetCardOwnerByPanData, @Header("Authorization") authorization:String): Response<GetCardOwnerByPanData>
    @POST("mobile-bank/v1/transfer/card-owner")
    suspend fun getFree(@Body getFee:GetFeeData,@Header("Authorization") authorization:String):Response<ResultFeeData>


    @POST("mobile-bank/v1/transfer/transfer")
    suspend fun transferForCart(@Body transferRequest:TransferRequest,@Header("Authorization") authorization:String):Response<SMSTokenData>

    @POST("mobile-bank/v1/transfer/transfer/verify")
    suspend fun transferVerify(@Body transferVerifyRequest:TransferVerifyRequest,@Header("Authorization") authorization:String):Response<StringData>

    @GET("mobile-bank/v1/transfer/history")
    suspend fun getTransferHistory(
        @Query("size") size: Int,
        @Query("current-page") currentPage: Int,
        @Header("Authorization") authorization: String
    ): Response<TransferHistoryResponse>
}

