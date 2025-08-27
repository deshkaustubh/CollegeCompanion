package tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.letterGenerator


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.ZoneId
import java.time.Instant

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LetterFormRenderer(
    template: LetterTemplate,
    onCopy: () -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val fieldValues = remember { mutableStateMapOf<String, String>() }
    // ✅ Wrap form in scrollable Column
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Fill Details",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFe5f2fb),
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )
        template.requiredFields.forEach { field ->
            val isDateField = field.equals("fromDate", true) || field.equals("toDate", true) || field.equals("preferredDate", ignoreCase = true)
            if (isDateField) {
                var showDatePicker by remember(field) { mutableStateOf(false) }
                val dateValue = fieldValues[field] ?: ""
                val datePickerState = rememberDatePickerState(
                    initialSelectedDateMillis = dateValue
                        .takeIf { it.isNotBlank() }
                        ?.let {
                            try {
                                LocalDate.parse(it, dateFormatter).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
                            } catch (_: Exception) {
                                null
                            }
                        }
                )
                OutlinedTextField(
                    value = dateValue,
                    onValueChange = {},
                    label = { Text(field.replaceFirstChar { it.uppercase() }) },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker = true }) {
                            Icon(Icons.Filled.DateRange, contentDescription = "Pick date")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { showDatePicker = true }
                )
                if (showDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            TextButton(onClick = {
                                val millis = datePickerState.selectedDateMillis
                                if (millis != null) {
                                    val selectedDate = Instant.ofEpochMilli(millis)
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDate()
                                    fieldValues[field] = selectedDate.format(dateFormatter)
                                }
                                showDatePicker = false
                            }) { Text("OK") }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDatePicker = false }) { Text("Cancel") }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }
            } else {
                OutlinedTextField(
                    value = fieldValues[field] ?: "",
                    onValueChange = {
                        fieldValues[field] = it
                    },
                    label = { Text(field.replaceFirstChar { it.uppercase() }) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                )
            }
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
