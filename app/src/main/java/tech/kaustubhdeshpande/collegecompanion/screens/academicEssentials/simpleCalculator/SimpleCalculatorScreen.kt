package tech.kaustubhdeshpande.collegecompanion.screens.academicEssentials.simpleCalculator

import android.app.Activity
import android.os.Build
import android.os.SystemClock
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.analytics.FirebaseAnalytics

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleCalculatorScreen(
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    val window = (context as? Activity)?.window
    val primary = MaterialTheme.colorScheme.primary
    val onPrimary = MaterialTheme.colorScheme.onPrimary
    val navigationBarColor = Color(0xFFe5f2fb)
    val lightStatusBarIcons = onPrimary.luminance() < 0.5
    val lightNavigationBarIcons = navigationBarColor.luminance() > 0.5
    val isAndroid14OrAbove = Build.VERSION.SDK_INT >= 34

    // Set system bar colors: status bar = onPrimary, navigation = custom
    LaunchedEffect(lightStatusBarIcons, lightNavigationBarIcons, isAndroid14OrAbove) {
        window?.let {
            if (!isAndroid14OrAbove) {
                @Suppress("DEPRECATION")
                it.statusBarColor = primary.toArgb() // status bar = onPrimary
                @Suppress("DEPRECATION")
                it.navigationBarColor = navigationBarColor.toArgb()
                WindowCompat.getInsetsController(it, it.decorView).apply {
                    isAppearanceLightStatusBars = lightStatusBarIcons
                    isAppearanceLightNavigationBars = lightNavigationBarIcons
                }
            } else {
                it.statusBarColor = android.graphics.Color.TRANSPARENT
                it.navigationBarColor = android.graphics.Color.TRANSPARENT
                WindowCompat.getInsetsController(it, it.decorView).apply {
                    isAppearanceLightStatusBars = lightStatusBarIcons
                    isAppearanceLightNavigationBars = lightNavigationBarIcons
                }
            }
        }
    }
    // --- Firebase Analytics Tracking ---
    val firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    val enterTime = SystemClock.elapsedRealtime()

    LaunchedEffect(Unit) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, android.os.Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, "Simple Calculator")
        })
    }

    DisposableEffect(Unit) {
        onDispose {
            val exitTime = SystemClock.elapsedRealtime()
            val durationMs = exitTime - enterTime
            firebaseAnalytics.logEvent("screen_time_spent", android.os.Bundle().apply {
                putString("screen_name", "Simple Calculator")
                putLong("duration_ms", durationMs)
            })
        }
    }
// --- End Analytics Tracking ---

    var lastBackPressTime by rememberSaveable { mutableStateOf(0L) }
    val debounceInterval = 500L // milliseconds

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.background(primary).statusBarsPadding(),
                title = {
                    Text(
                        text = "Calculator",
                        style = MaterialTheme.typography.headlineLarge,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            val now = SystemClock.elapsedRealtime()
                            if (now - lastBackPressTime > debounceInterval) {
                                lastBackPressTime = now
                                navigateBack()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Menu",
                            tint = primary,
                        )
                    }
                }
            )
        }) { innerPadding ->
        SimpleCalculatorContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun SimpleCalculatorContent(modifier: Modifier = Modifier) {
    var expression by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("") }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFe3f2fd)) // Theme-friendly background
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CalculatorDisplay(expression = expression, result = result)

        CalculatorKeypad { key ->
            when (key) {
                "=" -> result = evaluateExpression(expression)
                "C" -> {
                    expression = ""; result = ""
                }

                "⌫" -> if (expression.isNotEmpty()) expression = expression.dropLast(1)
                else -> expression += key
            }
        }
    }
}
