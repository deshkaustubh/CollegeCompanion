package tech.kaustubhdeshpande.collegecompanion.screens.focusAndExtras.pomodoro

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class TimerPhase { FOCUS, BREAK }

data class PomodoroState(
    val timeRemaining: Long = 25 * 60,
    val isRunning: Boolean = false,
    val sessionCount: Int = 0,
    val phase: TimerPhase = TimerPhase.FOCUS,
    val focusDuration: Int = 25, // minutes
    val breakDuration: Int = 5   // minutes
)

class PomodoroViewModel : ViewModel() {
    var state = mutableStateOf(PomodoroState())
        private set

    private var timerJob: Job? = null


    fun startTimer() {
        if (state.value.isRunning) return
        timerJob = viewModelScope.launch {
            state.value = state.value.copy(isRunning = true)
            while (state.value.timeRemaining > 0 && state.value.isRunning) {
                delay(1000)
                state.value = state.value.copy(timeRemaining = state.value.timeRemaining - 1)
            }
            if (state.value.timeRemaining == 0L) handlePhaseSwitch()
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
        state.value = state.value.copy(isRunning = false)
    }

    fun resetTimer() {
        timerJob?.cancel()
        val current = state.value
        val resetSeconds = when (current.phase) {
            TimerPhase.FOCUS -> current.focusDuration * 60
            TimerPhase.BREAK -> current.breakDuration * 60
        }
        state.value = current.copy(
            timeRemaining = resetSeconds.toLong(),
            isRunning = false
        )
    }


    private fun handlePhaseSwitch() {
        val current = state.value
        val nextPhase =
            if (current.phase == TimerPhase.FOCUS) TimerPhase.BREAK else TimerPhase.FOCUS
        val nextDuration = when (nextPhase) {
            TimerPhase.FOCUS -> current.focusDuration
            TimerPhase.BREAK -> current.breakDuration
        }
        val updatedCount =
            if (nextPhase == TimerPhase.FOCUS) current.sessionCount + 1 else current.sessionCount

        state.value = current.copy(
            phase = nextPhase,
            timeRemaining = nextDuration * 60L,
            isRunning = false,
            sessionCount = updatedCount
        )
    }

    fun getFormattedTime(): String {
        val minutes = state.value.timeRemaining / 60
        val seconds = state.value.timeRemaining % 60
        return "%02d:%02d".format(minutes, seconds)
    }

    fun getSidekickMessage(): String {
        val current = state.value
        val total =
            if (current.phase == TimerPhase.FOCUS) current.focusDuration * 60 else current.breakDuration * 60
        val percent = current.timeRemaining.toFloat() / total.coerceAtLeast(1)
        return when (current.phase) {
            TimerPhase.FOCUS -> when {
                percent > 0.75 -> "🔒 Locked in. Let’s crush this."
                percent > 0.4 -> "⏳ Stay with it—you’re flowing."
                else -> "🔥 Final stretch. Don’t touch your phone now!"
            }

            TimerPhase.BREAK -> when {
                percent > 0.6 -> "💤 Breathe. You’ve earned it."
                else -> "🌿 Time to return with clarity."
            }
        }
    }

    fun updateFocusDurationRaw(input: String) {
        val clamped = input.toIntOrNull()?.coerceIn(5, 240) ?: 25
        state.value = state.value.copy(focusDuration = clamped)
    }

    fun updateBreakDurationRaw(input: String) {
        val clamped = input.toIntOrNull()?.coerceIn(1, 60) ?: 5
        state.value = state.value.copy(breakDuration = clamped)
    }

    var focusInput = mutableStateOf("25")
        private set

    var breakInput = mutableStateOf("5")
        private set

    fun onFocusInputChanged(text: String) {
        focusInput.value = text
    }

    fun onBreakInputChanged(text: String) {
        breakInput.value = text
    }

    fun commitFocusDuration() {
        val value = focusInput.value.toIntOrNull()?.coerceIn(5, 240)
        value?.let {
            val current = state.value
            val updateTime =
                if (!current.isRunning && current.phase == TimerPhase.FOCUS) it * 60L else current.timeRemaining
            state.value = current.copy(focusDuration = it, timeRemaining = updateTime)
        } ?: run {
            focusInput.value = state.value.focusDuration.toString()
        }
    }


    fun commitBreakDuration() {
        val value = breakInput.value.toIntOrNull()?.coerceIn(1, 60)
        value?.let {
            val current = state.value
            val updateTime =
                if (!current.isRunning && current.phase == TimerPhase.BREAK) it * 60L else current.timeRemaining
            state.value = current.copy(breakDuration = it, timeRemaining = updateTime)
        } ?: run {
            breakInput.value = state.value.breakDuration.toString()
        }
    }

    init {
        focusInput.value = state.value.focusDuration.toString()
        breakInput.value = state.value.breakDuration.toString()
    }
}