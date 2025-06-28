package tech.kaustubhdeshpande.collegecompanion.screens.academicEssentials.sgpacalculator


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SubjectInputRow(
    subject: SgpaSubject,
    onCreditChange: (String) -> Unit,
    onGradeChange: (String) -> Unit,
    onRemove: () -> Unit
) {
    val gradeOptions = listOf(
        "O" to 10, "A" to 9, "B" to 8, "C" to 7,
        "D" to 6, "E" to 5, "P" to 4, "F" to 0
    )

    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.outline, shape = MaterialTheme.shapes.small)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = subject.credit,
            onValueChange = onCreditChange,
            label = { Text("Credits") },
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(Modifier.width(8.dp))

        Box(Modifier.weight(1f)) {
            OutlinedTextField(
                value = if (subject.grade.isBlank()) "" else "${subject.grade} – ${gradeOptions.toMap()[subject.grade]}",
                onValueChange = {},
                readOnly = true,
                enabled = false, // disables keyboard and keeps visual feedback
                label = { Text("Grade") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                gradeOptions.forEach { (grade, point) ->
                    DropdownMenuItem(
                        text = { Text("$grade – $point") },
                        onClick = {
                            onGradeChange(grade)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.width(8.dp))

        IconButton(onClick = onRemove) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Remove",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun SgpaResultCard(sgpa: Double) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        "Your SGPA",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = when {
                            sgpa >= 9 -> "🔥 Outstanding!"
                            sgpa >= 8 -> "🌟 Great work!"
                            sgpa >= 7 -> "👍 Keep it up"
                            else -> "📘 Let’s improve"
                        },
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    text = String.format("%.2f", sgpa),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun InstructionsBlock() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outline)
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "🧭 How to Use This Calculator",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(8.dp))

        val instructions = listOf(
            "Start by adding subjects—every row is a subject in your semester.",
            "Enter the credit value for each subject (like 4, 3, or 2).",
            "Pick the grade you received (A, B, etc.) from the dropdown.",
            "Add or remove rows anytime using the +1 or Delete button.",
            "As you update values, your SGPA will calculate automatically at the bottom.",
            "Final SGPA is shown with up to 2 decimal places (rounded)."
        )

        instructions.forEach { tip ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text("•", modifier = Modifier.padding(end = 6.dp))
                Text(
                    text = tip,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

