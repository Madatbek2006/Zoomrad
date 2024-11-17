package uz.madatbek.zoomradcompose.domain.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import uz.madatbek.zoomradcompose.NetworkStatus
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.api.CardApi
import uz.madatbek.zoomradcompose.data.sourse.remote.api.Register
import uz.madatbek.zoomradcompose.data.sourse.remote.api.TransferApi
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.AddCardData
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.UpdateCard
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.StringData
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.GetCardOwnerByPanData
import uz.madatbek.zoomradcompose.domain.CardRepository
import uz.madatbek.zoomradcompose.utils.emitWith
import uz.madatbek.zoomradcompose.utils.safetyFlow
import uz.madatbek.zoomradcompose.utils.toResultData
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    val cardApi: CardApi,
    val register: Register,
    private val transferApi: TransferApi
):CardRepository {
    override suspend fun addCard(card: AddCardData)= withContext(Dispatchers.IO){
        cardApi.addCard(MyShar.getAccessToken(), card)
            .toResultData()
            .map { StringData(it.message) }
    }
    override suspend fun getCardOwnerByPan(card: GetCardOwnerByPanData)= withContext(Dispatchers.IO){
        transferApi.getCardOwnerByPan(authorization =MyShar.getAccessToken() , card = card)
            .toResultData()
    }
    override suspend fun getAllCards() = withContext(Dispatchers.IO){
        cardApi.getAllCards(authorization =MyShar.getAccessToken())
            .toResultData()
    }
    override suspend fun deleteCard(id: String)= withContext(Dispatchers.IO){
        cardApi.deleteCard(id = id,authorization = MyShar.getAccessToken())
            .toResultData()
    }

    override suspend fun updateCard(card: UpdateCard) = withContext(Dispatchers.IO){
        cardApi.updateCard(card = card, authorization = MyShar.getAccessToken())
            .toResultData()
    }
}