package tech.kaustubhdeshpande.collegecompanion.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val PrimaryLight = Color(0xFFEBEDF2)
val OnPrimaryLight = Color(0xFF0F1417)
val PrimaryDark = Color(0xFF2B3640)
val OnPrimaryDark = Color(0xFFFFFFFF)

// Secondary Colors
val Secondary = Color(0xFFFAFAFA)
val OnSecondary = Color(0xFF5C738A)
val SecondaryDark = Color(0xFF1F262E)
val OnSecondaryDark = Color(0xFF9EADBF)


// Background & Surface
val Background = Color(0xFFFFFFF2)

val BackgroundDark = Color(0xFF141A1F)
val OnBackground = Color(0xFF0F1417)
val OnBackgroundDark = Color(0xFFFFFFFF)
val Surface = Color(0xFFFAFAFA)
val SurfaceDark = Color(0xFF1F262E)
val OnSurface = Color(0xFFFAFAFA)
val OnSurfaceDark = Color(0xFF9EADBF)


// New color palate
val DarkPrimary = Color(0xFF1E88E5)  // Deep Blue
val DarkPrimaryVariant = Color(0xFF1565C0)  // Darker Blue

val DarkSecondary = Color(0xFF43A047)  // Green
val DarkSecondaryVariant = Color(0xFF2E7D32)  // Darker Green

val DarkTertiary = Color(0xFFFFC107)  // Amber
val DarkTertiaryVariant = Color(0xFFFFA000)  // Darker Amber

val LightPrimary = Color(0xFF2196F3)  // Bright Blue
val LightPrimaryVariant = Color(0xFF1976D2)  // Darker Blue

val LightSecondary = Color(0xFF4CAF50)  // Green
val LightSecondaryVariant = Color(0xFF388E3C)  // Darker Green

val LightTertiary = Color(0xFFFFC107)  // Amber
val LightTertiaryVariant = Color(0xFFFFA000)  // Darker Amber


private val LightColorScheme = lightColorScheme(

    primary = Color(0xFF0e47a1),
    secondary = Color(0xFFfd9701),
    tertiary = Color(0xFF4db24f),
    surface = Color(0xFFfefefe),
    background = Color(0xFFe5f2fb),

    onPrimary = Color(0xFFf4ffff),
    onSecondary = Color(0xFFfffbcc),
    onSurface = Color.Black,
    onBackground = OnBackground

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val DarkColorScheme = darkColorScheme(

    primary = PrimaryDark,
    secondary = SecondaryDark,
    tertiary = Pink40,
    background = BackgroundDark,
    surface = SurfaceDark,

    onPrimary = OnPrimaryDark,
    onSecondary = OnSecondaryDark,
    onSurface = OnSurfaceDark,
    onBackground = OnBackgroundDark,
)

@Composable
fun Internship1ProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}