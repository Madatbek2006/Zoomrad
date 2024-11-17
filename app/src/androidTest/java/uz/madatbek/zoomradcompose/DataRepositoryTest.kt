package uz.madatbek.zoomradcompose



class DataRepositoryTest {
//    var register:Register?=null
//    var dataRepository:DataRepository?=null
//    @Before
//    fun before(){
//        register=object :Register{
//            override suspend fun singUpUser(data: SingUpUserData): Response<SMSTokenData> {
//                TODO("Not yet implemented")
//            }
//
//            override suspend fun singInUser(data: SingInUserData): Response<SMSTokenData> {
//                TODO("Not yet implemented")
//            }
//
//            override suspend fun verifySingUpSMS(data: SMSData): Response<TokensData> {
//                TODO("Not yet implemented")
//            }
//
//            override suspend fun verifySingInSMS(data: SMSData): Response<TokensData> {
//                TODO("Not yet implemented")
//            }
//
//            override suspend fun updateToken(refreshToken: RefreshTokenData): Response<TokensData> {
//                val ref="spesokfmbpsokgbtpsrae45q34awqg"
//                if (refreshToken.refreshToken==ref){
//                    return Response.success(TokensData(ref,UUID.randomUUID().toString()))
//                }
//                return Response.error(401,"IsInValid refreshToken".toResponseBody())
//            }
//        }
//        dataRepository=object :DataRepository{
//            override fun getLocationsType(type: String): List<MarkerData> {
//                TODO("Not yet implemented")
//            }
//
//            override fun getAccessToken(): Flow<Result<TokensData>> = channelFlow{
//                var response:Response<TokensData>?=register!!.updateToken(refreshToken = RefreshTokenData("spesokfmbpsokgbtpsrae45q34awqg"))
//
//                response?.let {
//                    if (it.isSuccessful && it.body() != null) {
//                        trySend(Result.success(TokensData(accessToken = it.body()!!.accessToken, refreshToken = it.body()!!.refreshToken)))
//                    }
//                    else {
//                        val data = gson.fromJson(it.errorBody()!!.string(), Exception::class.java)
//                        trySend(Result.failure(data))
//
//                    }
//                }
//                awaitClose{
//                    response=null
//                }
//            }
//
//        }
//    }
//    @Test
//    fun getAccessToken(){
////        dataRepository!!.getAccessToken().onEach {
////            it.onSuccess {
////                (it.accessToken+"success test").myLog()
////            }
////
////            it.onFailure {
////                (it.message+"failure test").myLog()
////            }
////        }.launchIn(CoroutineScope(Job()+Dispatchers.Main))
//    }
//
//
//
//    @After
//    fun after(){
//
//    }
}