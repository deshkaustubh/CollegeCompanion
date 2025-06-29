package tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.letterGenerator


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LetterFormRenderer(
    template: LetterTemplate,
    onBack: () -> Unit,
    onCopy: () -> Unit
) {
    var fieldValues by remember {
        mutableStateOf(template.requiredFields.associateWith { "" })
    }
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(12.dp)) {
        TextButton(onClick = onBack) {
            Text("❮❮ Back", fontWeight = FontWeight.Medium)
        }

        Spacer(Modifier.height(8.dp))

        Text(
            "${template.emoji} ${template.title}",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Medium
        )

        Spacer(Modifier.height(12.dp))

        // ✅ Wrap form in scrollable Column
        Column(
            modifier = Modifier
                .weight(1f) // Take remaining space
                .verticalScroll(rememberScrollState())
        ) {
            template.requiredFields.forEach { field ->

                OutlinedTextField(
                    value = fieldValues[field] ?: "",
                    onValueChange = {
                        fieldValues = fieldValues.toMutableMap().apply { put(field, it) }
                    },
                    label = { Text(field.replaceFirstChar { it.uppercase() }) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                "📄 Generated Letter:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.height(4.dp))
            LetterPreviewBox(template.generateLetter(fieldValues))

            Spacer(Modifier.height(16.dp))
        }

        // 🧷 Pinned to bottom
        Button(
            onClick = {
                copyToClipboard(context, template.generateLetter(fieldValues))
                onCopy()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Copy Letter")
        }
    }
}
