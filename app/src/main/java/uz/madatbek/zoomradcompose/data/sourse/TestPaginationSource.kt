package uz.mobile.plumapp.data.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.api.TransferApi
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferDetail
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferHistoryResponse
import uz.madatbek.zoomradcompose.utils.myLog
import uz.madatbek.zoomradcompose.utils.toResultData

class TestPaginationSource(private val api: TransferApi) : PagingSource<Int, TransferDetail>() {

    override fun getRefreshKey(state: PagingState<Int, TransferDetail>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1) ?: state.closestPageToPosition(
                anchor
            )?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TransferDetail> {
        val page = params.key ?: 1
       api.getTransferHistory(10, page,MyShar.getAccessToken())
            .toResultData()
            .onSuccess {
              return LoadResult.Page(
                  data = it.child,
                  nextKey = if (it.totalPages>page)page.plus(1) else null,
                  prevKey = if (page>1)page.minus(1)else null
              )
            }
            .onFailure {
                return LoadResult.Error(
                    it
                )
            }
        return LoadResult.Error(
            Exception("Unknown Error!!")
        )
    }

}


//class RepostitoryImpl @Inject constructor(private val api: TestApi) {
//
//    fun getHistory(size: Int, pageCount: Int): Flow<PagingData<ApiResponse>> =
//        Pager(
//            config = PagingConfig(size), pagingSourceFactory = { TestPaginationSource(api = api) },
//        ).flow
//}

//class ViewModelImpl @Inject constructor(private val impl: RepostitoryImpl) : ViewModel() {
//    fun getHistory(size, pageCount: Int): Flow<PagingData<ApiResponse>> =
//        impl.getHistory(size = size, pageCount = pageCount).cachedIn(viewModelScope)
//}


data class ApiResponse(
    val name: String,
    val time: String,
    val location: String
)