package uz.madatbek.zoomradcompose.data.sourse.remote

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.api.Register
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.RefreshTokenData
import uz.madatbek.zoomradcompose.utils.myLog
import javax.inject.Inject
import javax.inject.Provider


class AuthRef @Inject constructor(
     private val registerApi: Provider<Register>
) :Authenticator {




    override fun authenticate(route: Route?, response: Response): Request {
        "salom".myLog()
        if (response.code>400){
            return refreshToken(response=response)
        }else{
            return response.request
        }
    }


    private  fun refreshToken(response: Response):Request{
        val newTokens=registerApi.get().updateToken(RefreshTokenData( MyShar.getRefreshToken())).execute()
        if (newTokens.isSuccessful){
            val refToken=newTokens.body()?.refreshToken?:""
            val accessToken=newTokens.body()?.accessToken?:""
            MyShar.setRefreshToken(refreshToken = refToken)
            MyShar.setAccessToken(accessToken = "Bearer ${accessToken}")
            return  response.request.newBuilder().removeHeader("Authorization").addHeader("Authorization", MyShar.getAccessToken()).build()
        }
        return response.request
    }
}