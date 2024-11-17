package uz.madatbek.zoomradcompose.domain

import uz.madatbek.zoomradcompose.presenter.screens.branchs.MarkerData

interface DataRepository {
    fun getLocationsType(type:String):List<MarkerData>
}