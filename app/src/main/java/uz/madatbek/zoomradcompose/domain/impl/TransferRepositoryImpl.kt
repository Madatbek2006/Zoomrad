package uz.madatbek.zoomradcompose.domain.impl

import android.view.PixelCopy.Request
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.data.sourse.remote.api.TransferApi
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.SMSTokenData
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.StringData
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferDetail
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferHistoryResponse
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferRequest
import uz.madatbek.zoomradcompose.data.sourse.remote.transver.TransferVerifyRequest
import uz.madatbek.zoomradcompose.domain.TransferRepository
import uz.madatbek.zoomradcompose.utils.emitWith
import uz.madatbek.zoomradcompose.utils.safetyFlow
import uz.madatbek.zoomradcompose.utils.toResultData
import uz.mobile.plumapp.data.sources.TestPaginationSource
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
    private val transferApi: TransferApi
):TransferRepository {
    override fun transferFOrCard(transferRequest: TransferRequest): Flow<Result<SMSTokenData>> = safetyFlow{
        transferApi.transferForCart(transferRequest = transferRequest, authorization = MyShar.getAccessToken())
            .toResultData()
            .emitWith()
    }

    override fun transferVerify(transferVerify: TransferVerifyRequest): Flow<Result<StringData>> = safetyFlow{
        transferApi.transferVerify(transferVerify,MyShar.getAccessToken())
            .toResultData()
            .emitWith()
    }

    override fun getTransferHistory(size: Int, currentPage: Int): Flow<PagingData<TransferDetail>> =
        Pager(
            config = PagingConfig(size), pagingSourceFactory = { TestPaginationSource(api = transferApi) },
        ).flow


}