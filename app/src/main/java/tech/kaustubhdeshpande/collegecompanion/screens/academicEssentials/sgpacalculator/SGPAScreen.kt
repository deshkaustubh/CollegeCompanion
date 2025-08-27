package tech.kaustubhdeshpande.collegecompanion.screens.academicEssentials.sgpacalculator


import android.app.Activity
import android.os.Build
import android.os.SystemClock
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.analytics.FirebaseAnalytics

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SGPAScreen(
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
            putString(FirebaseAnalytics.Param.SCREEN_NAME, "SGPA Calculator")
        })
    }

    DisposableEffect(Unit) {
        onDispose {
            val exitTime = SystemClock.elapsedRealtime()
            val durationMs = exitTime - enterTime
            firebaseAnalytics.logEvent("screen_time_spent", android.os.Bundle().apply {
                putString("screen_name", "SGPA Calculator")
                putLong("duration_ms", durationMs)
            })
        }
    }
// --- End Analytics Tracking ---

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.background(primary).statusBarsPadding(),
                title = {
                    Text(
                        text = "SGPA Calculator",
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
                            tint = primary,
                        )
                    }
                }
            )
        }) { innerPadding ->
        SGPAContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun SGPAContent(modifier: Modifier = Modifier) {
    val viewModel: SGPAViewModel = viewModel()

    val scroll = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .padding(16.dp)
    ) {
        Text(
            "SGPA Calculator",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))

        viewModel.subjects.forEachIndexed { index, subject ->
            SubjectInputRow(
                subject = subject,
                onCreditChange = { viewModel.updateSubject(index, credit = it) },
                onGradeChange = { viewModel.updateSubject(index, grade = it) },
                onRemove = { viewModel.removeSubject(index) }
            )
            Spacer(Modifier.height(8.dp))
        }

        Button(
            onClick = { viewModel.addSubject() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(" +1  Add Subject", fontWeight = FontWeight.Medium)
        }

        Spacer(Modifier.height(24.dp))

        SgpaResultCard(sgpa = viewModel.calculateSGPA())

        InstructionsBlock()
    }
}



