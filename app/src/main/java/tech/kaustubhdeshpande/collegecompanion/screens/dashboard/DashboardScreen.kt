package tech.kaustubhdeshpande.collegecompanion.screens.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.kaustubhdeshpande.collegecompanion.data.ScreenDataSource
import tech.kaustubhdeshpande.collegecompanion.ui.theme.Internship1ProjectTheme


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardContent(
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 24.dp) // to avoid FAB or bottom nav
    ) {
        DashboardPunchCard() // 🔥 Your top banner

        Spacer(modifier = Modifier.height(16.dp))

        LayerSection(
            title = "Academic Essentials",
            items = ScreenDataSource.academicEssentials,
            onClick = onCardClick,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LayerSection(
            title = "Letters & Communication",
            items = ScreenDataSource.documentsAndComms,
            onClick = onCardClick,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LayerSection(
            title = "College Toolkit",
            items = ScreenDataSource.collegeLayer,
            onClick = onCardClick,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LayerSection(
            title = "Focus & Enrichment",
            items = ScreenDataSource.focusAndExtras,
            onClick = onCardClick,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = buildAnnotatedString {
                append("Crafted by ")
                pushStringAnnotation(tag = "URL", annotation = "https://kaustubhdeshpande.tech/")
                withStyle(SpanStyle(color = Color(0xFF0D47A1), fontWeight = FontWeight.Bold)) {
                    append("Kaustubh Deshpande")
                }
                pop()
            },
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .clickable {
                    uriHandler.openUri("https://kaustubhdeshpande.tech/")
                }
        )

    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardScreen(modifier: Modifier = Modifier, onCardClick: (String) -> Unit) {
    Scaffold(
        topBar = { DashboardTopAppBar() }
    ) { innerPadding ->
        DashboardContent(onCardClick = onCardClick, modifier = Modifier.padding(innerPadding))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun DashboardScreenPreview() {
    Internship1ProjectTheme {
        DashboardScreen { }
    }
}