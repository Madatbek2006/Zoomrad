package uz.madatbek.zoomradcompose.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.madatbek.zoomradcompose.data.sourse.remote.AuthRef
import uz.madatbek.zoomradcompose.data.sourse.remote.api.CardApi
import uz.madatbek.zoomradcompose.data.sourse.remote.api.HomeApi
import uz.madatbek.zoomradcompose.data.sourse.remote.api.Register
import uz.madatbek.zoomradcompose.data.sourse.remote.api.TransferApi
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InternetModule {
    @[Provides Singleton]
    fun provideGson(): Gson = Gson()

    @[Provides Singleton]
    fun provideOkHttp(register: Provider<Register>) : OkHttpClient {
        return OkHttpClient.Builder()
            .authenticator(AuthRef(register))
            .build()
    }

    @[Provides Singleton]
    fun provideRetrofit(okHttpClient : OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://195.158.16.140/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @[Provides Singleton]
    fun provideRegisterApi(retrofit: Retrofit): Register = retrofit.create(Register::class.java)

    @[Provides Singleton]
    fun provideCardApi(retrofit: Retrofit): CardApi = retrofit.create(CardApi::class.java)

    @[Provides Singleton]
    fun provideTransferApi(retrofit: Retrofit): TransferApi = retrofit.create(TransferApi::class.java)

    @[Provides Singleton]
    fun provideHomeApi(retrofit: Retrofit): HomeApi = retrofit.create(HomeApi::class.java)
}