package uz.madatbek.zoomradcompose.domain

import kotlinx.coroutines.flow.Flow
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TotalBalanceResponse

interface HomeRepository {
    suspend fun getTotalBalance(): Result<TotalBalanceResponse>
}