package uz.madatbek.zoomradcompose.domain.impl

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.api.Register
import uz.madatbek.zoomradcompose.data.sourse.remote.singIn.SingInUserData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.SMSData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.SingUpUserData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.StringData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.SMSTokenData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.TokensData
import uz.madatbek.zoomradcompose.domain.LoginRepository
import uz.madatbek.zoomradcompose.utils.emitWith
import uz.madatbek.zoomradcompose.utils.myLog
import uz.madatbek.zoomradcompose.utils.toResultData
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api:Register
):LoginRepository {
    override fun singUpUser(singUpData: SingUpUserData): Flow<Result<SMSTokenData>> = flow{
        api.singUpUser(singUpData)
            .toResultData()
            .onSuccess { MyShar.setUserSMSToken(it.token) }
            .emitWith()
    }

    override  fun singInUser(singUpData: SingInUserData): Flow<Result<SMSTokenData>> = flow {
        api.singInUser(singUpData)
            .toResultData()
            .onSuccess { MyShar.setUserSMSToken(it.token) }
            .emitWith()
    }

    override fun smsSingUpVerify(singUpData: SMSData): Flow<Result<TokensData>> = flow{
        api.verifySingUpSMS(singUpData)
            .toResultData()
            .emitWith()
    }

    override fun smsSingInVerify(singInData: SMSData): Flow<Result<TokensData>> = flow{
        api.verifySingInSMS(singInData)
            .toResultData()
            .emitWith()
    }
}