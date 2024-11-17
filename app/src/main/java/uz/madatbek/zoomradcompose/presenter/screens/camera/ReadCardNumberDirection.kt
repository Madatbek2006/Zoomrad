package uz.madatbek.zoomradcompose.presenter.screens.camera

import uz.madatbek.zoomradcompose.presenter.screens.addcard.AddCardScreen
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import javax.inject.Inject

//interface ReadCardNumberDirection {
//    suspend fun back()
//    suspend fun backWithData(cardNumber: String, expirationDate: String)
//
//    suspend fun backWithCardNumber(cardNumber: String)
//}
//
//class ReadCardNumberDirectionImpl @Inject constructor(
//    private val appNavigator: AppNavigator
//) : ReadCardNumberDirection {
//    override suspend fun back() {
//        appNavigator.back()
//    }
//
//    override suspend fun backWithData(cardNumber: String, expirationDate: String) {
//        appNavigator.back()
////        appNavigator.replace(
////            AddCardScreen(cardNumber = cardNumber, expirationDate = expirationDate)
////        )
//    }
//
//    override suspend fun backWithCardNumber(cardNumber: String) {
//        appNavigator.back()
//    }
//}