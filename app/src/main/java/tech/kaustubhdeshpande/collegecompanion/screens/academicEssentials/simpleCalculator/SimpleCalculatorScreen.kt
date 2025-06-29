package tech.kaustubhdeshpande.collegecompanion.screens.academicEssentials.simpleCalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleCalculatorScreen(
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Calculator",
                        style = MaterialTheme.typography.headlineLarge,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navigateBack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Menu",
                            tint = Color.Black,
                        )
                    }
                }
            )
        }) { innerPadding ->
        SimpleCalculatorContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun SimpleCalculatorContent(modifier: Modifier = Modifier) {
    var expression by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("") }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFe3f2fd)) // Theme-friendly background
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CalculatorDisplay(expression = expression, result = result)

        CalculatorKeypad { key ->
            when (key) {
                "=" -> result = evaluateExpression(expression)
                "C" -> {
                    expression = ""; result = ""
                }

                "⌫" -> if (expression.isNotEmpty()) expression = expression.dropLast(1)
                else -> expression += key
            }
        }
    }
}


