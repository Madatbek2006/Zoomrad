package uz.madatbek.zoomradcompose.data.sourse.local

import android.content.Context
import android.content.SharedPreferences
import uz.madatbek.zoomradcompose.data.model.LoginData
import uz.madatbek.zoomradcompose.ui.theme.ThemeType
import uz.madatbek.zoomradcompose.utils.myLog

object MyShar {
    private lateinit var sharedPreferences:SharedPreferences
    fun init(context: Context){
        sharedPreferences=context.getSharedPreferences("Zoomrad",Context.MODE_PRIVATE)
    }

    fun setUserLoginData(loginData:LoginData){
        sharedPreferences.edit().putString("userPhone",loginData.phone).apply()
        sharedPreferences.edit().putString("userPassword",loginData.password).apply()
        sharedPreferences.edit().putString("userPINCode",loginData.pinCode).apply()
    }

    fun getUserLoginData():LoginData?{
        val phone= sharedPreferences.getString("userPhone","")?:""
        val password= sharedPreferences.getString("userPassword","")?:""
        val pinCode= sharedPreferences.getString("userPINCode","")?:""
        return LoginData(phone, pinCode,password)
    }

    fun setUserSMSToken(token:String){
        sharedPreferences.edit().putString("token",token).apply()
    }

    fun getSMSToken():String{
        return sharedPreferences.getString("token","")?:""
    }

    fun setRefreshToken(refreshToken:String){
        sharedPreferences.edit().putString("refreshToken",refreshToken).apply()
    }

    fun getRefreshToken():String{
        return sharedPreferences.getString("refreshToken","")?:""
    }

    fun setAccessToken(accessToken:String){

        "setAccessToken".myLog()
        sharedPreferences.edit().putString("accessToken",accessToken).apply()
    }

    fun getAccessToken():String{
        return sharedPreferences.getString("accessToken","")?:""
    }


    fun isAusUser():Boolean{
        return sharedPreferences.getBoolean("auzUser",false)
    }
    fun setAusUser(boolean: Boolean=true){
        sharedPreferences.edit().putBoolean("auzUser",boolean).apply()
    }


    fun isSingIn():Boolean{
        return sharedPreferences.getBoolean("singInUser",false)
    }
    fun setIsSingInUser(boolean: Boolean){
        sharedPreferences.edit().putBoolean("singInUser",boolean).apply()
    }
    fun setLanguage(language:String){
        sharedPreferences.edit().putString("language",language).apply()
    }
    fun getLanguage():String{
        return sharedPreferences.getString("language","ru")?:"ru"
    }


    fun setTransferVerifyToken(token:String){
        sharedPreferences.edit().putString("TransferVerifyToken",token).apply()
    }

    fun getTransferVerifyToken():String{
        return sharedPreferences.getString("TransferVerifyToken","")?:""
    }

    fun getTheme():String=sharedPreferences.getString("app_theme",ThemeType.LIGHT)?:ThemeType.LIGHT

    fun setTheme(theme:String){
        sharedPreferences.edit().putString("app_theme",theme).apply()
    }

    fun setChooseCard(pan:String){
        sharedPreferences.edit().putString("ChooseCard",pan).apply()
        sharedPreferences.edit().putBoolean("isChooseCard",true).apply()
    }

    fun getChaseCard():String{
        sharedPreferences.edit().putBoolean("isChooseCard",false).apply()
        return sharedPreferences.getString("ChooseCard","")?:"0008"
    }

    fun isValid():Boolean{
        return sharedPreferences.getBoolean("isChooseCard",false)
    }


    fun setThemeCard(cardId:String,theme:Int){
        sharedPreferences.edit().putInt("ThemeCard$cardId",theme).apply()
    }

    fun getThemeCard(cardId:String):Int{
        return sharedPreferences.getInt("ThemeCard$cardId",0)
    }

    fun setIsHaveNetwork(networkState:Boolean){
        sharedPreferences.edit().putBoolean("networkState",networkState).apply()
    }

    fun isHaveNetwork():Boolean{
        return sharedPreferences.getBoolean("networkState",false)
    }
}

//AIzaSyCfNVrfaKfZrljIIQHLWhuDF6YT6PaQX9w