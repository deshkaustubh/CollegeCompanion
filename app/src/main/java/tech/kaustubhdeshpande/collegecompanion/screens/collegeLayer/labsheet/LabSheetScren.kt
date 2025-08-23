package tech.kaustubhdeshpande.collegecompanion.screens.collegeLayer.labsheet

import android.annotation.SuppressLint
import android.os.SystemClock
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.analytics.FirebaseAnalytics
import tech.kaustubhdeshpande.collegecompanion.ui.theme.Internship1ProjectTheme

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabSheetScreen(
    navigateBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color(0xFF0a3579)
    val navigationBarColor = Color(0xFFe5f2fb)

    SideEffect {
        systemUiController.setStatusBarColor(color = statusBarColor)
        systemUiController.setNavigationBarColor(color = navigationBarColor)
    }

    // --- Firebase Analytics Tracking ---
    val context = LocalContext.current
    val firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    val enterTime = SystemClock.elapsedRealtime()

    // Log screen view event
    LaunchedEffect(Unit) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, android.os.Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, "Lab Evaluation Sheet")
        })
    }

    // Log time spent when leaving
    DisposableEffect(Unit) {
        onDispose {
            val exitTime = SystemClock.elapsedRealtime()
            val durationMs = exitTime - enterTime
            val durationSec = durationMs / 1000
            firebaseAnalytics.logEvent("screen_time_spent", android.os.Bundle().apply {
                putString("screen_name", "Lab Evaluation Sheet")
                putLong("duration_sec", durationSec)
            })
        }
    }
    // --- End Analytics Tracking ---

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lab Evaluation Sheet",
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
                        onClick = { navigateBack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Menu",
                            tint = Color.Black,
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        AndroidView(
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    settings.allowFileAccess = true

                    // Load your HTML file
                    loadUrl("file:///android_asset/index.html")
                }
            })
    }
}
//C:\Users\ksd15\AndroidStudioProjects\CollegeCompanion\app\src\main\assets\index.html

@Preview
@Composable
fun LabSheetPreview() {
    Internship1ProjectTheme {
        LabSheetScreen { }
    }
}