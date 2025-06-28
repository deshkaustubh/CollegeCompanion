package tech.kaustubhdeshpande.collegecompanion.screens.cgpaCalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


data class CgpaSemester(
    val sgpa: String,
    val credits: String
)

class CGPAViewModel : ViewModel() {
    var semesters by mutableStateOf(
        listOf(CgpaSemester(sgpa = "", credits = ""))
    )
        private set

    fun updateSemester(index: Int, sgpa: String? = null, credits: String? = null) {
        semesters = semesters.toMutableList().apply {
            this[index] = this[index].copy(
                sgpa = sgpa ?: this[index].sgpa,
                credits = credits ?: this[index].credits
            )
        }
    }

    fun addSemester() {
        semesters = semesters + CgpaSemester("", "")
    }

    fun removeSemester(index: Int) {
        if (semesters.size > 1) {
            semesters = semesters.toMutableList().apply { removeAt(index) }
        }
    }

    fun calculateCGPA(): Double {
        val weightedTotal = semesters.sumOf {
            val s = it.sgpa.toDoubleOrNull() ?: 0.0
            val c = it.credits.toIntOrNull() ?: 0
            s * c
        }

        val totalCredits = semesters.sumOf {
            it.credits.toIntOrNull() ?: 0
        }

        return if (totalCredits > 0)
            String.format("%.2f", weightedTotal / totalCredits).toDouble()
        else 0.0
    }
}
