package tech.kaustubhdeshpande.collegecompanion.screens.sgpacalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class SgpaSubject(
    val credit: String,
    val grade: String
)

class SGPAViewModel : ViewModel() {
    var subjects by mutableStateOf(
        listOf(SgpaSubject(credit = "", grade = ""))
    )
        private set

    val gradePointMap = mapOf(
        "O" to 10, "A" to 9, "B" to 8,
        "C" to 7, "D" to 6, "E" to 5,
        "P" to 4, "F" to 0
    )

    fun updateSubject(index: Int, credit: String? = null, grade: String? = null) {
        subjects = subjects.toMutableList().apply {
            this[index] = this[index].copy(
                credit = credit ?: this[index].credit,
                grade = grade ?: this[index].grade
            )
        }
    }

    fun addSubject() {
        subjects = subjects + SgpaSubject(credit = "", grade = "")
    }

    fun removeSubject(index: Int) {
        if (subjects.size > 1) {
            subjects = subjects.toMutableList().apply { removeAt(index) }
        }
    }

    fun calculateSGPA(): Double {
        val totalCreditPoints = subjects.sumOf { subject ->
            val credit = subject.credit.toIntOrNull() ?: 0
            val point = gradePointMap[subject.grade.uppercase()] ?: 0
            credit * point
        }

        val totalCredits = subjects.sumOf {
            it.credit.toIntOrNull() ?: 0
        }

        return if (totalCredits > 0)
            (totalCreditPoints.toDouble() / totalCredits).let {
                "%.2f".format(it).toDouble()
            }
        else 0.0
    }
}
