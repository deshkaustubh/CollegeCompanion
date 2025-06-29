package tech.kaustubhdeshpande.collegecompanion.screens.academicEssentials.gpa101

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun FormulaCardSGPA() {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SGPA =",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Grade Points × Credits",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorScheme.onSurface
                )

                Divider(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .width(180.dp)
                        .height(1.dp),
                    color = colorScheme.onSurface.copy(alpha = 0.4f)
                )

                Text(
                    text = "Total Credits",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorScheme.onSurface
                )
            }
        }
    }
}


@Composable
fun FormulaCardCGPA() {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "CGPA =",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Faux fraction layout
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "(SGPA × Credits) + …",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
                    color = colorScheme.onSurface
                )

                Divider(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .width(180.dp)
                        .height(1.dp),
                    color = colorScheme.onSurface.copy(alpha = 0.4f)
                )

                Text(
                    text = "Total Credits",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
                    color = colorScheme.onSurface
                )
            }
        }
    }
}


@Composable
fun GradePointTable() {
    Column {
        Text(
            "🎓 Grade to Point Mapping",
            style = MaterialTheme.typography.titleSmall,
            color = colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(8.dp))

        val grades = listOf(
            "O" to 10, "A" to 9, "B" to 8, "C" to 7,
            "D" to 6, "E" to 5, "P" to 4, "F" to 0
        )

        grades.forEach { (grade, point) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Grade: $grade", color = colorScheme.onBackground)
                Text("Points: $point", color = colorScheme.onBackground)
            }
        }
    }
}

@Composable
fun MythBustedCard() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.error.copy(alpha = 0.1f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "🚨 GPA Myth Busted",
                style = MaterialTheme.typography.titleMedium,
                color = colorScheme.error,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "“CGPA is just the average of all your SGPAs.”\n=> Nope. That’s not how it works.",
                style = MaterialTheme.typography.bodyMedium,
                color = colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Each semester carries different credit weights. That means some semesters matter more in your CGPA. You calculate it like this:",
                style = MaterialTheme.typography.bodySmall,
                color = colorScheme.onSurface
            )
            Spacer(Modifier.height(8.dp))

            CgpaFormulaCard()
        }
    }
}

@Composable
fun CgpaFormulaCard() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "CGPA = ",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "(SGPA₁ × Credits₁) + (SGPA₂ × Credits₂) +..",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(vertical = 4.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Total Credits",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
