package tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.letterGenerator


import android.os.SystemClock
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.analytics.FirebaseAnalytics
import tech.kaustubhdeshpande.collegecompanion.ui.theme.Internship1ProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LetterLabHomeScreen(
    navigateBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color(0xFF0a3579)
    val navigationBarColor = Color(0xFFe5f2fb)

    SideEffect {
        systemUiController.setStatusBarColor(color = statusBarColor)
        systemUiController.setNavigationBarColor(color = navigationBarColor)
    }

    // --- Firebase Analytics Tracking ---
    val context = LocalContext.current
    val firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    val enterTime = SystemClock.elapsedRealtime()

    LaunchedEffect(Unit) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, android.os.Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, "Letter Lab")
        })
    }

    DisposableEffect(Unit) {
        onDispose {
            val exitTime = SystemClock.elapsedRealtime()
            val durationMs = exitTime - enterTime
            firebaseAnalytics.logEvent("screen_time_spent", android.os.Bundle().apply {
                putString("screen_name", "Letter Lab")
                putLong("duration_ms", durationMs)
            })
        }
    }
// --- End Analytics Tracking ---

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .statusBarsPadding(),
                title = {
                    Text(
                        text = "Letter Lab",
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
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }) { innerPadding ->
        LetterLabContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun LetterLabContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var selectedTemplate by remember { mutableStateOf<LetterTemplate?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 🔍 Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search Letter Type") },
            modifier = Modifier.fillMaxWidth(),
            shape = CircleShape,
        )

        Spacer(modifier = Modifier.height(16.dp))

        // If a letter is selected, show its form
        selectedTemplate?.let { template ->
            LetterFormRenderer(
                template = template,
                onBack = { selectedTemplate = null },
                onCopy = {
                    Toast.makeText(context, "📋 Copied to clipboard!", Toast.LENGTH_SHORT).show()
                }
            )
        } ?: run {
            // 🧾 Card Grid (List)
            val filteredTemplates = letterTemplates.filter {
                val query = searchQuery.text.trim().lowercase()
                query.isBlank() || it.title.lowercase().contains(query) ||
                        it.tags.any { tag -> tag.contains(query) } ||
                        it.description.lowercase().contains(query)
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(filteredTemplates.size) { index ->
                    val item = filteredTemplates[index]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(8.dp)
                            )
                            .clickable { selectedTemplate = item },
                        elevation = CardDefaults.cardElevation(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFbbdefb)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text(
                                "${item.emoji} ${item.title}",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = Color(0xFF0D47A1)
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(item.description, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun LetterLabHomeScreenPreview() {
    Internship1ProjectTheme {
        LetterLabHomeScreen { }
    }
}