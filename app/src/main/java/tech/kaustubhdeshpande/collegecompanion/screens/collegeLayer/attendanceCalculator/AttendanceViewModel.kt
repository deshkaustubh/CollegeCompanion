package tech.kaustubhdeshpande.collegecompanion.screens.collegeLayer.attendanceCalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


data class AttendanceState(
    val totalClasses: String = "",
    val attendedClasses: String = "",
    val targetPercentage: String = "75",
    val futureClasses: String = "",
    val futureAttended: String = ""

)


class AttendanceViewModel : ViewModel() {
    var state by mutableStateOf(AttendanceState())
        private set

    fun updateTotal(total: String) {
        state = state.copy(totalClasses = total)
    }

    fun updateAttended(attended: String) {
        state = state.copy(attendedClasses = attended)
    }

    fun updateTarget(target: String) {
        state = state.copy(targetPercentage = target)
    }

    fun calculatePercentage(): Double {
        val total = state.totalClasses.toIntOrNull() ?: return 0.0
        val attended = state.attendedClasses.toIntOrNull() ?: return 0.0
        return if (total > 0) (attended.toDouble() / total) * 100 else 0.0
    }

    fun classesYouCanBunk(): Any {
        val attended = state.attendedClasses.toIntOrNull() ?: return 0
        val total = state.totalClasses.toIntOrNull() ?: return 0
        val target = state.targetPercentage.toDoubleOrNull() ?: return 75.0

        var possibleTotal = total
        while (possibleTotal < 999) {
            val percent = (attended.toDouble() / possibleTotal) * 100
            if (percent < target) break
            possibleTotal++
        }
        return possibleTotal - total - 1
    }

    fun classesToAttendNonStop(): Any {
        val attended = state.attendedClasses.toIntOrNull() ?: return 0
        val total = state.totalClasses.toIntOrNull() ?: return 0
        val target = state.targetPercentage.toDoubleOrNull() ?: return 75.0

        var newAttended = attended
        var newTotal = total
        while (newTotal < 999) {
            val percent = (newAttended.toDouble() / newTotal) * 100
            if (percent >= target) break
            newAttended++
            newTotal++
        }
        return newAttended - attended
    }

    fun sidekickVerdict(): String {
        val percent = calculatePercentage()
        return when {
            percent >= 90 -> "🛡 HERO ZONE — Your attendance is a fortress."
            percent >= 75 -> "✅ SAFE ZONE — A bunk or two won’t hurt."
            percent >= 60 -> "⚠ CAREFUL — You’re walking a fine line."
            else -> "🚨 DANGER ZONE — Time to attend every class!"
        }
    }

    fun updateFuture(total: String, attending: String) {
        state = state.copy(futureClasses = total, futureAttended = attending)
    }

    fun projectedAttendance(): Double {
        val currentTotal = state.totalClasses.toIntOrNull() ?: return 0.0
        val currentAttended = state.attendedClasses.toIntOrNull() ?: return 0.0
        val future = state.futureClasses.toIntOrNull() ?: return 0.0
        val futureAttend = state.futureAttended.toIntOrNull() ?: return 0.0
        val newTotal = currentTotal + future
        val newAttended = currentAttended + futureAttend
        return if (newTotal > 0) (newAttended.toDouble() / newTotal) * 100 else 0.0
    }

    fun verdictAfterScenario(): String {
        val projected = projectedAttendance()
        val target = state.targetPercentage.toDoubleOrNull() ?: 75.0
        return when {
            projected >= target -> "🧘 You’ll be above $target%. Feel free to chill."
            projected >= target - 5 -> "🫣 Close call. Maybe attend 1 more for safety?"
            else -> "🛑 Warning! You’ll still be under target. Don’t skip."
        }
    }
}
