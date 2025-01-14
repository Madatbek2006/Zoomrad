package uz.madatbek.zoomradcompose.domain.impl

import uz.madatbek.zoomradcompose.data.sourse.remote.api.Register
import uz.madatbek.zoomradcompose.domain.DataRepository
import uz.madatbek.zoomradcompose.presenter.screens.branchs.MarkerData
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val register: Register
):DataRepository {
    val regionData=listOf("Филиалы","Центры К.О","Банкоматы","Обменные пункты")
    private val data= hashMapOf(
        Pair(this.regionData[0], listOf(
            MarkerData(41.3099366, 69.2776974,"ОПЕРУ/Головной офис","Город Ташкент, Мирабадский район, проспект Амира\nТемура","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            MarkerData(41.320798,69.253456,"Мирабадский филиал","Ташкент, Шайхантахурский район, улица Навои, 27","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            MarkerData(41.3226406, 69.2405880,"Shayxontohur","Shayxontoxur tumani, A.Navoiy ko'chasi, 40-uy","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            MarkerData(41.3428122, 69.3378751,"IT-Park","город Ташкент, Мироз-Улугбекский район, улица\nМуминова","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            MarkerData(37.2375998, 67.2929891,"Сурхандарьинский филиал","Сурхандарьинская область, город Термез, улица\nАлишера Навои","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            MarkerData(39.6558598, 66.9613883,"Самаркандский филиал","Самаркандская область, город Самарканд, улица\nБостонсарай","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            MarkerData(42.4631751, 59.6167191,"Каракалпакстанский филиал","Республика Каракалпакстан, г.Нукус, ул. Исламе\nКаримова 102","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            MarkerData(38.836516,65.794238,"Кашкадарьинский филиал","Кашкадарьинская область, город Карши\nМустакилликский филиал","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            MarkerData(38.8381720, 65.8018358,"АТ Qarshi Shaxar Aloqabank filali","Qashqadaryo viloyati, Qarshi shahri, Mustaqillik\nshohko'chasi","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            MarkerData(40.1402740, 65.3622338,"Навоийский филиал","Чавоийская область, Карманинский район, сельский\nсовет жителей Янги Арик","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            MarkerData(40.9924748, 71.6469898,"Наманганский филиал","аманганская область, город Наманган, улица\nТуракургана, 75","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            MarkerData(41.5687457, 60.6311148,"Хорезмский филиал","горезмская область, город Ургенч, проспект\nАль-Хорезми, 106","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            MarkerData(40.1308736, 67.8254941,"Джизакский филиал","Джизакская область, город Джизак,\nШ. Улица Рашидова","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            MarkerData(40.5578530, 70.9316628,"Кокандский филиал","Ферганская область, город Коканд, улица Туркестан,1","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            MarkerData(40.3863248, 71.7723516,"Ферганский филиал","рерганская область, город Фергана, улица\nБурханиддина Маргинони, 69А","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            MarkerData(39.7591465, 64.4286806,"Бухарский филиал","Бухарская область, город Бухара, улица Зульфия","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            MarkerData(40.7540718, 72.3343374,"Андижанский филиал","Андижанская область, город Андижан, улица Пахтакора 11","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            ),))
    init {
        data.put(this.regionData[1],
            listOf(
                MarkerData(40.2131988, 69.2651064,"Бекобод","Гашкентская область, Бекобадский район, улица А.Темура","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
                MarkerData(41.2204569, 69.3343467,"O'rtachirchiq kXKM","Уртачирчикский р., проспект Бектемира, 217","Время работы:с 9:00 до18:00\nВыходные дни:Суббота-Воскресенье"),
            )
        )
    }
    override fun getLocationsType(type: String): List<MarkerData> =data[type]?:emptyList()


}