package uz.madatbek.zoomradcompose.data.sourse.remote.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.AddCardData
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.AddCardResponse
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.CardData
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.UpdateCard
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.StringData

interface CardApi {
    @GET("mobile-bank/v1/card")
    suspend fun getAllCards(@Header("Authorization") authorization:String): Response<List<CardData>>

    @POST("mobile-bank/v1/card")
    suspend fun addCard(@Header("Authorization") authorization:String,@Body card:AddCardData):Response<AddCardResponse>

    @PUT("mobile-bank/v1/card")
    suspend fun updateCard(@Body card: UpdateCard,@Header("Authorization") authorization:String):Response<StringData>

    @DELETE("mobile-bank/v1/card/{id}")
    suspend fun deleteCard(@Path("id") id: String, @Header("Authorization") authorization:String):Response<StringData>
}