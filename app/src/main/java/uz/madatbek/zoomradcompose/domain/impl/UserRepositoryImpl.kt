package uz.madatbek.zoomradcompose.domain.impl

import kotlinx.coroutines.flow.Flow
import uz.madatbek.zoomradcompose.data.UserRequest
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.api.HomeApi
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.StringData
import uz.madatbek.zoomradcompose.data.sourse.remote.user.UserResponse
import uz.madatbek.zoomradcompose.domain.UserRepository
import uz.madatbek.zoomradcompose.utils.emitWith
import uz.madatbek.zoomradcompose.utils.safetyFlow
import uz.madatbek.zoomradcompose.utils.toResultData
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    val homeApi: HomeApi
):UserRepository {
    override fun getFullUserInfo(): Flow<Result<UserResponse>> = safetyFlow{
        homeApi.getFullUserInfo(MyShar.getAccessToken())
            .toResultData()
            .emitWith()
    }

    override fun updateUserInfo(user: UserRequest): Flow<Result<StringData>> = safetyFlow{
        homeApi.updateUserInfo(MyShar.getAccessToken(),user)
            .toResultData()
            .emitWith()
    }
}