package tech.kaustubhdeshpande.collegecompanion.screens.focusAndExtras.pomodoro

import android.os.SystemClock
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.analytics.FirebaseAnalytics

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PomodoroScreen(
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

    LaunchedEffect(Unit) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, android.os.Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, "Pomodoro Timer")
        })
    }

    DisposableEffect(Unit) {
        onDispose {
            val exitTime = SystemClock.elapsedRealtime()
            val durationMs = exitTime - enterTime
            firebaseAnalytics.logEvent("screen_time_spent", android.os.Bundle().apply {
                putString("screen_name", "Pomodoro Timer")
                putLong("duration_ms", durationMs)
            })
        }
    }
// --- End Analytics Tracking ---

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.background(MaterialTheme.colorScheme.primary).statusBarsPadding(),
                title = {
                    Text(
                        text = "Pomodoro",
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
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            )
        }) { innerPadding ->
        PomodoroContent(modifier = Modifier.padding(innerPadding))
    }
}


@Composable
fun PomodoroContent(modifier: Modifier = Modifier) {
    val viewModel: PomodoroViewModel = viewModel()
    val state = viewModel.state.value
    val formattedTime = viewModel.getFormattedTime()
    val message = viewModel.getSidekickMessage()

    val totalDuration = when (state.phase) {
        TimerPhase.FOCUS -> state.focusDuration * 60
        TimerPhase.BREAK -> state.breakDuration * 60
    }
    val progress = state.timeRemaining.toFloat() / totalDuration.coerceAtLeast(1)


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "🍅 Pomodoro Mode",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))

        Text(
            text = if (state.phase == TimerPhase.FOCUS) "🎯 Focus Session" else "🌴 Break Time",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium
        )

        Spacer(Modifier.height(20.dp))

        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.size(180.dp),
            color = if (state.phase == TimerPhase.FOCUS) Color(0xFFe74c3c) else Color(0xFF3498db),
            strokeWidth = 12.dp,
            trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
        )

        Text(formattedTime, fontSize = 48.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Text(message, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)

        Spacer(Modifier.height(24.dp))

        // ⏱ Timer Customization
        Text("Customize Your Timers", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedTextField(
                value = viewModel.focusInput.value,
                onValueChange = { viewModel.onFocusInputChanged(it) },
                label = { Text("Focus (min)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .onFocusChanged { if (!it.isFocused) viewModel.commitFocusDuration() },
                enabled = !state.isRunning,
                trailingIcon = {
                    IconButton(onClick = { viewModel.commitFocusDuration() }) {
                        Icon(Icons.Default.Check, contentDescription = "Apply Focus Time")
                    }
                }
            )

            OutlinedTextField(
                value = viewModel.breakInput.value,
                onValueChange = { viewModel.onBreakInputChanged(it) },
                label = { Text("Break (min)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .onFocusChanged { if (!it.isFocused) viewModel.commitBreakDuration() },
                enabled = !state.isRunning,
                trailingIcon = {
                    IconButton(onClick = { viewModel.commitBreakDuration() }) {
                        Icon(Icons.Default.Check, contentDescription = "Apply Break Time")
                    }
                }
            )
        }

        Spacer(Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    if (state.isRunning) viewModel.pauseTimer() else viewModel.startTimer()
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
            ) {
                Text(if (state.isRunning) "Pause" else "Start")
            }

            Button(
                onClick = { viewModel.resetTimer() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Reset")
            }
        }

        Spacer(Modifier.height(32.dp))
        Text(
            "🔥 Sessions Completed: ${state.sessionCount}",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

