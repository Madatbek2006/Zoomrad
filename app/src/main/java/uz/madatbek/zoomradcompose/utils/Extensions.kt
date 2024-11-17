package uz.madatbek.zoomradcompose.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.reduce
import retrofit2.Response
import uz.madatbek.zoomradcompose.data.sourse.remote.singup.StringData
import uz.madatbek.zoomradcompose.presenter.screens.branchs.MarkerData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.experimental.ExperimentalTypeInference


fun String.myLog(tag:String="TTT") = Log.d(tag, this)
fun String.onlyLetters() = all { it.isLetter() }
fun String.myShortToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun Context.checkPermission(array: List<String>, blockSuccess: () -> Unit) {
    "checkPermission".myLog()
    var isValid = true
    for (i in array.indices) {
        if (!isHavePermission(array[i]) && isValid) {
            isValid = false
            openSettings()
        }
    }
    if (isValid) {
        blockSuccess()
    }
}

fun Context.isHavePermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

fun FragmentActivity.requestPermission(permission: Array<String>, rCode: Int = 0) {
    ActivityCompat.requestPermissions(
        this,
        permission,
        rCode
    )
}

fun Context.openSettings() {
    val intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = Uri.parse("package:${this.packageName}")
    startActivity(intent)
}



fun Context.setLocale(languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
    val configuration = Configuration(this.resources.configuration)
    configuration.setLocale(locale)
    this.resources.updateConfiguration(configuration, this.resources.displayMetrics)
}


fun getCurrentLanguage(): String {
    return Locale.getDefault().language
}

fun <T> Response<T>.toResultData(): Result<T> {
    try {
        val gson = Gson()
        if (this.isSuccessful && this.body() != null) {
            return Result.success(this.body()!! as T)
        } else {
            val data: StringData? =
                gson.fromJson(this.errorBody()?.string() ?: "Unknown error", StringData::class.java)
            return (Result.failure(Exception(data?.message ?: "Unknown error")))
        }
    } catch (ex: Exception) {
        return (Result.failure(Exception(ex?.message ?: "Unknown error")))
    }
}

context(FlowCollector<Result<T>>)
suspend fun <T> Result<T>.emitWith() {
    emit(this)
}

@OptIn(ExperimentalTypeInference::class)
fun <T> safetyFlow(@BuilderInference block: suspend FlowCollector<Result<T>>.() -> Unit): Flow<Result<T>> =
    flow(block).flowOn(Dispatchers.IO).catch {
        emit(Result.failure(it))
    }

suspend inline fun <T : Any, E : Any> SimpleSyntax<T, E>.postUiState(value: T) {
    reduce { value }
}


fun String.toManyFormat(): String {
    return when (this.length) {
        in 0..3 -> this
        4 -> {
            substring(0, 1) + " " + substring(1, length)
        }

        5 -> {
            substring(0, 2) + " " + substring(2, length)
        }

        6 -> {
            substring(0, 3) + " " + substring(3, length)
        }

        7 -> {
            substring(0, 1) + " " + substring(1, 4) + " " + substring(4, length)
        }

        8 -> {
            substring(0, 2) + " " + substring(2, 5) + " " + substring(5, length)
        }

        9 -> {
            substring(0, 3) + " " + substring(3, 6) + " " + substring(6, length)
        }

        10 -> {
            substring(0, 1) + " " + substring(1, 4) + " " + substring(6, 9) + " " + substring(
                9,
                length
            )
        }

        else -> "∞"

    }
}

fun String.toCardPan(): String =
    when (this.length) {
        in 0..4 -> this
        in 5..8 -> substring(0, 4) + " " + substring(4, this.length)
        in 9..12 -> substring(0, 4) + " " + substring(4, 8) + " " + substring(8, this.length)
        else -> substring(0, 4) + " " + substring(4, 8) + " " + substring(8, 12) + " " + substring(
            12,
            this.length
        )
    }

fun String.toDate(): String = when (this.length) {
    in 0..1 -> this
    else -> substring(0, 2) + "\\" + substring(2, this.length)
}

fun Context.openMap(data: MarkerData) {
    val uri = Uri.parse("geo:${data.lat},${data.lng}?z=${12}")
    val mapIntent = Intent(Intent.ACTION_VIEW, uri)
    mapIntent.resolveActivity(packageManager)?.let {
        startActivity(Intent.createChooser(mapIntent, "Открыть с помощью"))
    }
}

fun Long.formatTimestamp(format: String = "yyyy-MM-dd HH:mm:ss"): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.format(Date(this))
}

fun String.toHideCard(): String {
    return substring(0, 4)+" "+substring(4, 6) + "** **** " + substring(12, this.length)
}

fun String.toPhoneFormat(): String {
    return substring(0, 4) + " " + substring(4, 6) + " " + substring(6, 9) + " " + substring(
        9,
        11
    ) + " " + substring(11, this.length)
}

fun String.containsOnlyNumbers(): Boolean {
    val regex = Regex("^\\d+\$")
    return this.matches(regex)
}

@Composable
fun drawComponentToBitmap(component: @Composable () -> Unit, width: Int, height: Int): Bitmap {
    // Создаем пустой Bitmap
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    // Создаем ComposeView для компоновки и рисования на нем
    val composeView = ComposeView(LocalContext.current).apply {
        setContent {
            component()
        }
    }

    // Измеряем и компонуем ComposeView
    composeView.measure(
        View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
    )
    composeView.layout(0, 0, width, height)

    // Рисуем компонент на Canvas
    composeView.draw(canvas)

    return bitmap
}


