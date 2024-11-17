package uz.madatbek.zoomradcompose.data.sourse.remote.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.madatbek.zoomradcompose.data.sourse.remote.singIn.SingInUserData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.SMSData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.SingUpUserData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.StringData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.SMSTokenData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.TokensData
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.RefreshTokenData

interface Register {
     @POST("mobile-bank/v1/auth/sign-up")
     suspend fun singUpUser(@Body data: SingUpUserData): Response<SMSTokenData>

     @POST("mobile-bank/v1/auth/sign-in")
     suspend fun singInUser(@Body data: SingInUserData): Response<SMSTokenData>

     @POST("mobile-bank/v1/auth/sign-up/verify")
     suspend fun verifySingUpSMS(@Body data:SMSData):Response<TokensData>

     @POST("mobile-bank/v1/auth/sign-in/verify")
     suspend fun verifySingInSMS(@Body data:SMSData):Response<TokensData>

     @POST("mobile-bank/v1/auth/update-token")
     fun updateToken(@Body refreshToken:RefreshTokenData):Call<TokensData>
}


/*

interface MyApi {
    @POST("api/v1/register")
    fun singUpUser(@Body data:SingUpUserData):Call<StringData>

    @POST("api/v1/login")
    fun loginUser(@Body data:LoginData):Call<TokenData>

    @POST("api/v1/register/verify")
    fun smsCode(@Body data: SMSCodeData):Call<TokenData>

    @POST("api/v1/contact")
    fun addContact(@Body data:CreateContactRequest,@Header("token") token: String):Call<ContactResponse>

    @PUT("api/v1/contact")
    fun editContact(@Body data:ContactResponse,@Header("token") token:String):Call<ContactResponse>

    @DELETE("api/v1/contact")
    fun deleteContact(@Query("id") id:Int,@Header("token") token: String):Call<IdData>

    @GET("api/v1/contact")
    fun getAllContact(@Header("token") token: String):Call<List<ContactResponse>>

}
 */