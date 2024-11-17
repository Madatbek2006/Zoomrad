package uz.madatbek.zoomradcompose.domain

import kotlinx.coroutines.flow.Flow
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.AddCardData
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.CardData
import uz.madatbek.zoomradcompose.data.sourse.remote.cards.UpdateCard
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.StringData
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.GetCardOwnerByPanData

interface CardRepository {
    suspend fun addCard(card:AddCardData):Result<StringData>

    suspend fun getCardOwnerByPan(card:GetCardOwnerByPanData):Result<GetCardOwnerByPanData>

    suspend fun getAllCards():Result<List<CardData>>

    suspend fun deleteCard(id:String):Result<StringData>

    suspend fun updateCard(card: UpdateCard):Result<StringData>

}