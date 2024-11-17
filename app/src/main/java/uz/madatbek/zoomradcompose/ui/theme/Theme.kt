package uz.madatbek.zoomradcompose.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.utils.myLog

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1E88E5), // Цвет иконок и текстов
    onPrimary = Color.White, // Цвет текста на primary цвете
    primaryContainer = Color(0xFF1565C0), // Цвет фона контейнеров
    onPrimaryContainer = Color.White, // Цвет текста на primary контейнерах
    inversePrimary = Color(0xFFBB86FC), // Цвет для инверсных элементов
    secondary = Color(0xFF03DAC6),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF018786),
    onSecondaryContainer = Color.White,
    tertiary = Color(0xFFBB86FC),
    onTertiary = Color.Black,
    tertiaryContainer = Color(0xFF6200EE),
    onTertiaryContainer = Color.White,
    background = Color(0xFF1C1C1E), // Основной фон
    onBackground = Color.White, // Цвет текста на основном фоне
    surface = Color(0xFF121212), // Фон для поверхностей (карточки и т.д.)
    onSurface = Color.White, // Цвет текста на поверхностях
    surfaceVariant = Color(0xFF2B2C2E),
    onSurfaceVariant = Color(0xFFCFD8DC),
    surfaceTint = Color(0xFF1E88E5),
    inverseSurface = Color(0xFF303030),
    inverseOnSurface = Color.White,
    error = Color(0xFFCF6679),
    onError = Color.Black,
    errorContainer = Color(0xFFB00020),
    onErrorContainer = Color.White,
    outline = Color(0xFFBB86FC),
    outlineVariant = Color(0xFF6200EE),
    scrim = Color(0x66000000)
)

private val NightColorScheme = darkColorScheme(
    primary = Color(0xFF1E88E5),          // Цвет иконок и текстов
    onPrimary = Color.White,              // Цвет текста на primary цвете
    primaryContainer = Color(0xFF1565C0), // Цвет фона контейнеров
    onPrimaryContainer = Color.White,     // Цвет текста на primary контейнерах
    inversePrimary = Color(0xFFBB86FC),   // Цвет для инверсных элементов
    secondary = Color(0xFF03DAC6),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF018786),
    onSecondaryContainer = Color.White,
    tertiary = Color(0xFFBB86FC),
    onTertiary = Color.Black,
    tertiaryContainer = Color(0xFF6200EE),
    onTertiaryContainer = Color.White,
    background = Color(0xFF26303A),       // Основной фон
    onBackground = Color.White,           // Цвет текста на основном фоне
    surface = Color(0xFF121212),          // Фон для поверхностей (карточки и т.д.)
    onSurface = Color.White,              // Цвет текста на поверхностях
    surfaceVariant = Color(0xFF465260),
    onSurfaceVariant = Color(0xFFCFD8DC),
    surfaceTint = Color(0xFF1E88E5),
    inverseSurface = Color(0xFF303030),
    inverseOnSurface = Color.White,
    error = Color(0xFFCF6679),
    onError = Color.Black,
    errorContainer = Color(0xFFB00020),
    onErrorContainer = Color.White,
    outline = Color(0xFFBB86FC),
    outlineVariant = Color(0xFF6200EE),
    scrim = Color(0x66000000)
)

val LightColorScheme = ColorScheme(

    primary =
    Color(0xFF6200EE)
//    Color(0xFFF6F5F5)

    ,
    surface = Color.White,
    background = Color(0xFFF6F5F5),
    onPrimary = Color(0xFFFFFFFF),

    primaryContainer =
    Color(0xFFBB86FC)
//    Color(0xFFF6F5F5)
    ,
    onPrimaryContainer = Color(0xFF3700B3),
    inversePrimary = Color(0xFF3700B3),
    secondary = Color(0xFF03DAC6),
    onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0xFF018786),
    onSecondaryContainer = Color(0xFF03DAC6),
    tertiary = Color(0xFF018786),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFF03DAC6),
    onTertiaryContainer = Color(0xFF00BCD4),
    onBackground = Color(0xFF000000),
    onSurface = Color(0xFF000000),
    //cardView
    surfaceVariant =
//    Color(0xFF37474F)
    Color.White
    ,
    onSurfaceVariant = Color(0xFFB0BEC5),
    surfaceTint = Color(0xFF6200EE),
    inverseSurface = Color(0xFF000000),
    inverseOnSurface = Color(0xFFFFFFFF),
    error = Color(0xFFCF6679),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFB00020),
    onErrorContainer = Color(0xFFCF6679),
    outline = Color(0xFFB0BEC5),
    outlineVariant = Color(0xFF37474F),
    scrim = Color(0xFF000000)
)

@Composable
fun ZoomradTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    currentTheme:String=MyShar.getTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        MyShar.getTheme()!=ThemeType.SYSTEM&&dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current

            "MyLod theme=> ${MyShar.getTheme()}".myLog()
            when(currentTheme){

                ThemeType.LIGHT->{
                    "theme=>LIGHT".myLog()
                    LightColorScheme
                }
                ThemeType.DARK->{
                    "theme=>DARK".myLog()
                   DarkColorScheme
                }
                ThemeType.NIGHT->{
                    "theme=>NIGHT".myLog()
                    NightColorScheme
                }


                else -> {
                    LightColorScheme
                }

            }



        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    "view.isInEditMode${view.isInEditMode}".toString().myLog()
    if (!view.isInEditMode) {
        "view.isInEditMode${view.isInEditMode}".toString().myLog()
        SideEffect {
            val window = (view.context as Activity).window

            window.statusBarColor =
            colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                when(colorScheme){
                    LightColorScheme->true
                    else->false
                }
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )

}

object ThemeType{
    const val DARK="dark"
    const val NIGHT="night"
    const val LIGHT="light"
    const val SYSTEM="system"
}