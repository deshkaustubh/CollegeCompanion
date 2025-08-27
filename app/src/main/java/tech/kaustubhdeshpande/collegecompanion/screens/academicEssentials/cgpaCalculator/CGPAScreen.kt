package tech.kaustubhdeshpande.collegecompanion.screens.academicEssentials.cgpaCalculator

import android.os.Build
import android.os.SystemClock
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.analytics.FirebaseAnalytics

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CGPAScreen(
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
            putString(FirebaseAnalytics.Param.SCREEN_NAME, "CGPA Calculator")
        })
    }
    DisposableEffect(Unit) {
        onDispose {
            val exitTime = SystemClock.elapsedRealtime()
            val durationMs = exitTime - enterTime
            firebaseAnalytics.logEvent("screen_time_spent", android.os.Bundle().apply {
                putString("screen_name", "CGPA Calculator")
                putLong("duration_ms", durationMs)
            })
        }
    }
    // --- End Analytics Tracking ---

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.background(color = primary).statusBarsPadding(),
                title = {
                    Text(
                        text = "CGPA Calculator",
                        style = MaterialTheme.typography.headlineLarge,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = onPrimary,        // Top bar = primary
                    titleContentColor = primary    // Title = onPrimary
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navigateBack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Menu",
                            tint = primary           // Icon = onPrimary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(Modifier.fillMaxSize()) {
            if (isAndroid14OrAbove) {
                // Paint onPrimary behind the status bar (edge‑to‑edge)
                Box(
                    Modifier
                        .background(onPrimary)
                        .windowInsetsTopHeight(WindowInsets.statusBars)
                        .fillMaxWidth()
                )
                // Paint navigationBarColor behind the navigation bar (edge‑to‑edge)
                Box(
                    Modifier
                        .background(navigationBarColor)
                        .windowInsetsBottomHeight(WindowInsets.navigationBars)
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                )
            }
            // Main content
            val viewModel: CGPAViewModel = viewModel()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Spacer(Modifier.height(12.dp))

                viewModel.semesters.forEachIndexed { index, sem ->
                    SemesterInputRow(
                        semester = sem,
                        onSgpaChange = { viewModel.updateSemester(index, sgpa = it) },
                        onCreditsChange = { viewModel.updateSemester(index, credits = it) },
                        onRemove = { viewModel.removeSemester(index) }
                    )
                    Spacer(Modifier.height(8.dp))
                }

                Button(
                    onClick = { viewModel.addSemester() },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(" +1 Add Semester", fontWeight = FontWeight.Medium)
                }

                Spacer(Modifier.height(24.dp))

                CgpaResultCard(cgpa = viewModel.calculateCGPA())
                CgpaInstructionsBlock()
            }
        }
    }
}