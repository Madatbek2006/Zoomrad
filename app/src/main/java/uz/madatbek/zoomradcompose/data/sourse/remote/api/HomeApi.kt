package uz.madatbek.zoomradcompose.data.sourse.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import uz.madatbek.zoomradcompose.data.UserRequest
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.CardData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.StringData
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TotalBalanceResponse
import uz.madatbek.zoomradcompose.data.sourse.remote.user.UserResponse

interface HomeApi {

    @GET("mobile-bank/v1/home/user-info/details")
    suspend fun getFullUserInfo(@Header("Authorization") authorization:String): Response<UserResponse>


    @PUT("mobile-bank/v1/home/user-info")
    suspend fun updateUserInfo(@Header("Authorization") authorization:String,@Body userRequest: UserRequest): Response<StringData>

    @GET("mobile-bank/v1/home/total-balance")
    suspend fun getTotalBalance(@Header("Authorization") authorization:String):Response<TotalBalanceResponse>
}