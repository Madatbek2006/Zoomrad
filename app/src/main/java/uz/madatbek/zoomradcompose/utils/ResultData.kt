import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class ResultData<T> {

    var value:T?=null
    @SinceKotlin("1.3")
    inline fun <T> Result<T>.onFailure(action: (exception: Throwable) -> Unit): Result<T> {

        exceptionOrNull()?.let { action(it) }
        return this
    }

    @SinceKotlin("1.3")
    inline fun <reified T> Result<T>.onSuccess(action: (value: T) -> Unit): Result<T> {

        if (isSuccess) {
            action(value as T)
        }
        return this
    }
}
data class ErrorResponse(
    val status:Int?,
    val name:String?,
    val massage:String?
)

data class BaseChildResponse<T>(
    val data:BaseResult<T>?,
    val success:Boolean?,
    val error:ErrorResponse?

)


data class BaseResult<T>(
    val pagination:Pagination,
    val results:List<T>
)

data class Pagination(
    val page:Int,
    val pageCount:Int,
    val pageSize:Int,
    val total:Int
)