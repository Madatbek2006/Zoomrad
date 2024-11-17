package uz.madatbek.zoomradcompose.domain.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.api.HomeApi
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TotalBalanceResponse
import uz.madatbek.zoomradcompose.domain.HomeRepository
import uz.madatbek.zoomradcompose.utils.emitWith
import uz.madatbek.zoomradcompose.utils.safetyFlow
import uz.madatbek.zoomradcompose.utils.toResultData
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeApi: HomeApi
):HomeRepository {

    override suspend fun getTotalBalance() = withContext(Dispatchers.IO){
        homeApi.getTotalBalance(MyShar.getAccessToken())
            .toResultData()
    }
}