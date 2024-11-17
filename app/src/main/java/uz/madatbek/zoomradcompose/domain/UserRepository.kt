package uz.madatbek.zoomradcompose.domain

import kotlinx.coroutines.flow.Flow
import uz.madatbek.zoomradcompose.data.UserRequest
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.StringData
import uz.madatbek.zoomradcompose.data.sourse.remote.user.UserResponse

interface UserRepository {
    fun getFullUserInfo():Flow<Result<UserResponse>>


    fun updateUserInfo(user: UserRequest):Flow<Result<StringData>>
}