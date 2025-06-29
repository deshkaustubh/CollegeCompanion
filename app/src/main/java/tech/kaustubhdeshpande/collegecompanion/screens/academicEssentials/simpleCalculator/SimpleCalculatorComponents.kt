package tech.kaustubhdeshpande.collegecompanion.screens.academicEssentials.simpleCalculator


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.objecthunter.exp4j.ExpressionBuilder


@Composable
fun CalculatorDisplay(expression: String, result: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = expression,
                fontSize = 20.sp,
                color = Color(0xFF1976D2),
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = result,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1),
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun CalculatorKeypad(onKeyPress: (String) -> Unit) {
    val keys = listOf(
        listOf("1", "2", "3", "−"),
        listOf("7", "8", "9", "÷"),
        listOf("4", "5", "6", "×"),
        listOf(".", "0", "^", "+"),
        listOf("sin", "cos", "tan", "⌫"), // Empty strings = visual placeholders
        listOf("C", "(", ")", "=") // Empty strings = visual placeholders
    )

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        keys.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                row.forEach { key ->
                    if (key.isNotBlank()) {
                        CalculatorKey(key = key, modifier = Modifier.weight(1f)) {
                            onKeyPress(key)
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorKey(
    key: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFbbdefb)
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier.aspectRatio(1f)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = key,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF0D47A1),
                textAlign = TextAlign.Center
            )
        }
    }
}


fun evaluateExpression(expr: String): String {
    return try {
        val sanitized = expr
            .replace("×", "*")
            .replace("÷", "/")
            .replace("−", "-")

        val balanced = autoBalanceParentheses(sanitized)

        val result = ExpressionBuilder(balanced).build().evaluate()

        if (result.isNaN() || result.isInfinite()) "Error"
        else if (result % 1.0 == 0.0) result.toInt().toString()
        else result.toString()
    } catch (e: Exception) {
        "Error"
    }
}

fun autoBalanceParentheses(expression: String): String {
    val openCount = expression.count { it == '(' }
    val closeCount = expression.count { it == ')' }
    return if (openCount > closeCount) {
        expression + ")".repeat(openCount - closeCount)
    } else {
        expression
    }
}

