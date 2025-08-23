package tech.kaustubhdeshpande.collegecompanion.screens.academicEssentials.gpa101


import android.os.SystemClock
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.analytics.FirebaseAnalytics

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GPA101Screen(
    navigateBack: () -> Unit,
    onCgpaCalculatorClick: () -> Unit,
    onSgpaCalculatorClick: () -> Unit
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

    LaunchedEffect(Unit) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, android.os.Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, "GPA 1-0-1")
        })
    }

    DisposableEffect(Unit) {
        onDispose {
            val exitTime = SystemClock.elapsedRealtime()
            val durationMs = exitTime - enterTime
            firebaseAnalytics.logEvent("screen_time_spent", android.os.Bundle().apply {
                putString("screen_name", "GPA 1-0-1")
                putLong("duration_ms", durationMs)
            })
        }
    }
// --- End Analytics Tracking ---

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "GPA 1-0-1",
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
                }
            )
        }) { innerPadding ->
        Gpa101Content(
            modifier = Modifier.padding(innerPadding),
            onCgpaCalculatorClick = { onCgpaCalculatorClick() },
            onSgpaCalculatorClick = { onSgpaCalculatorClick() }
        )
    }
}


@Composable
fun Gpa101Content(
    modifier: Modifier = Modifier,
    onCgpaCalculatorClick: () -> Unit,
    onSgpaCalculatorClick: () -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        MythBustedCard()

        Spacer(Modifier.height(20.dp))

        Text(
            "📘 Why GPA 101 Exists",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "Instead of guessing how your score is calculated, this screen clears things up. It's your shortcut to understanding how SGPA and CGPA really work.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(Modifier.height(32.dp))

        Text(
            "📗 What is SGPA?",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "SGPA tells how you did in one semester. It depends on your grades and how many credits each subject carried.",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(12.dp))

        FormulaCardSGPA()

        Spacer(Modifier.height(20.dp))

        GradePointTable()

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onSgpaCalculatorClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("🎯 Try SGPA Calculator", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(Modifier.height(24.dp))

        Text(
            "📘 What is CGPA?",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "CGPA shows your average across all semesters. But bigger semesters (more credits) count more than smaller ones.",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(12.dp))

        FormulaCardCGPA()

        Spacer(Modifier.height(32.dp))


        Button(
            onClick = { onCgpaCalculatorClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("📊 Try CGPA Calculator", style = MaterialTheme.typography.bodyMedium)
        }
    }
}


