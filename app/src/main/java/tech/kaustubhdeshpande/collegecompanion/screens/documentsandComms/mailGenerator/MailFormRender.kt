package tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.mailGenerator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.mailGenerator.copyToClipboard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MailFormRenderer(
    template: MailTemplate,
    onBack: () -> Unit,
    onCopySubject: () -> Unit,
    onCopyBody: () -> Unit
) {
    val context = LocalContext.current
    var fieldValues by remember {
        mutableStateOf(template.requiredFields.associateWith { "" })
    }
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    Column(
        Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("❮❮ Back to templates")
        }

        Spacer(Modifier.height(8.dp))
        Text(
            "${template.emoji} ${template.title}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium
        )

        Spacer(Modifier.height(12.dp))

        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            template.requiredFields.forEach { field ->
                val isDateField = field.equals("fromDate", true) || field.equals("toDate", true) || field.equals("preferredDate", true) || field.equals("lastDate", true) || field.equals("returnDate", true)
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
                    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialMillis)
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
                                        fieldValues = fieldValues.toMutableMap().apply {
                                            put(field, dateFormatter.format(cal.time))
                                        }
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
                            fieldValues = fieldValues.toMutableMap().apply { put(field, it) }
                        },
                        label = { Text(field.replaceFirstChar { it.uppercase() }) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                        shape = MaterialTheme.shapes.medium
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            val subject = template.generateSubject(fieldValues)
            val body = template.generateBody(fieldValues)

            Text(
                "📌 Subject:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
            MailPreviewBox(subject)

            Spacer(Modifier.height(12.dp))

            Text(
                "📄 Email Body:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
            MailPreviewBox(body)

            Spacer(Modifier.height(24.dp))
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = {
                    copyToClipboard(context, template.generateSubject(fieldValues))
                    onCopySubject()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Copy Subject")
            }

            Button(
                onClick = {
                    copyToClipboard(context, template.generateBody(fieldValues))
                    onCopyBody()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Copy Body")
            }
        }
    }
}
