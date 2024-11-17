package uz.madatbek.zoomradcompose.domain

import kotlinx.coroutines.flow.Flow
import uz.madatbek.zoomradcompose.data.sourse.remote.singIn.SingInUserData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.SMSData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.SingUpUserData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.StringData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.SMSTokenData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.TokensData

interface LoginRepository {
    fun singUpUser(singUpData:SingUpUserData):Flow<Result<SMSTokenData>>
    fun singInUser(singUpData:SingInUserData):Flow<Result<SMSTokenData>>
    fun smsSingUpVerify(singUpData:SMSData):Flow<Result<TokensData>>
    fun smsSingInVerify(singInData:SMSData):Flow<Result<TokensData>>
}