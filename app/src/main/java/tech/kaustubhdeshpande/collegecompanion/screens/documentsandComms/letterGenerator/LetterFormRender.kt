package tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.letterGenerator


import android.os.Build
import android.os.SystemClock
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.mailGenerator.copyToClipboard
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LetterFormRenderer(
    template: LetterTemplate,
    onCopy: () -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val fieldValues = remember { mutableStateMapOf<String, String>() }
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Debounce state for copy and back buttons
        var lastCopyClickTime by remember { mutableStateOf(0L) }
        var lastBackClickTime by remember { mutableStateOf(0L) }
        val debounceInterval = 500L // milliseconds

        TextButton(onClick = {
            val now = SystemClock.elapsedRealtime()
            if (now - lastBackClickTime > debounceInterval) {
                lastBackClickTime = now
                onBack()
            }
        }) {
            Text("❮❮ Back to templates")
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Fill Details",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))
        template.requiredFields.forEach { field ->
            val isDateField = field.equals("fromDate", true) || field.equals(
                "toDate",
                true
            ) || field.equals("preferredDate", true) || field.equals(
                "lastDate",
                true
            ) || field.equals("returnDate", true)
            if (isDateField) {
                var showDatePicker by remember(field) { mutableStateOf(false) }
                val dateValue = fieldValues[field] ?: ""
                val initialMillis = dateValue.takeIf { it.isNotBlank() }?.let {
                    try {
                        val cal = Calendar.getInstance()
                        cal.time = dateFormatter.parse(it) ?: cal.time
                        cal.timeInMillis
                    } catch (_: Exception) {
                        null
                    }
                }
                val datePickerState =
                    rememberDatePickerState(initialSelectedDateMillis = initialMillis)
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
                                    val cal = Calendar.getInstance()
                                    cal.timeInMillis = millis
                                    fieldValues[field] = dateFormatter.format(cal.time)
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
        Button(
            onClick = {
                val now = SystemClock.elapsedRealtime()
                if (now - lastCopyClickTime > debounceInterval) {
                    lastCopyClickTime = now
                    copyToClipboard(context, template.generateLetter(fieldValues))
                    onCopy()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Copy Letter")
        }
    }
}
