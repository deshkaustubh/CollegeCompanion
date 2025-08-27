package tech.kaustubhdeshpande.collegecompanion.screens.dashboard

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import tech.kaustubhdeshpande.collegecompanion.data.ScreenDataSource
import tech.kaustubhdeshpande.collegecompanion.ui.theme.Internship1ProjectTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardScreen(onCardClick: (String) -> Unit) {
    val context = LocalContext.current
    val window = (context as? Activity)?.window
    val primary = MaterialTheme.colorScheme.primary
    val navigationBarColor = Color(0xFFe5f2fb)
    val lightStatusBarIcons = primary.luminance() > 0.5
    val lightNavigationBarIcons = navigationBarColor.luminance() > 0.5
    val isAndroid14OrAbove = Build.VERSION.SDK_INT >= 34
    // Set icon contrast to match your primary scrim
    LaunchedEffect(lightStatusBarIcons, lightNavigationBarIcons, isAndroid14OrAbove) {
        window?.let {
            if (!isAndroid14OrAbove) {
                // For Android 13 and below, set system bar colors via window
                @Suppress("DEPRECATION")
                it.statusBarColor = primary.toArgb()
                @Suppress("DEPRECATION")
                it.navigationBarColor = navigationBarColor.toArgb()
                WindowCompat.getInsetsController(it, it.decorView).apply {
                    isAppearanceLightStatusBars = lightStatusBarIcons
                    isAppearanceLightNavigationBars = lightNavigationBarIcons
                }
            } else {
                // For Android 14+, set system bars to transparent for Compose to draw behind
                it.statusBarColor = android.graphics.Color.TRANSPARENT
                it.navigationBarColor = android.graphics.Color.TRANSPARENT
                WindowCompat.getInsetsController(it, it.decorView).apply {
                    isAppearanceLightStatusBars = lightStatusBarIcons
                    isAppearanceLightNavigationBars = lightNavigationBarIcons
                }
            }
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing
    ) { innerPadding ->
        Box(Modifier.fillMaxSize()) {
            if (isAndroid14OrAbove) {
                // Paint primary behind the status bar (edge‑to‑edge)
                Box(
                    Modifier
                        .background(primary)
                        .windowInsetsTopHeight(WindowInsets.statusBars)
                        .fillMaxWidth()
                )
                // Paint navigationBarColor behind the navigation bar (edge‑to‑edge)
                Box(
                    Modifier
                        .background(navigationBarColor)
                        .windowInsetsBottomHeight(WindowInsets.navigationBars)
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                )
            }
            DashboardContent(
                onCardClick = onCardClick,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

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

        TopSection()

        Spacer(modifier = Modifier.height(24.dp))

        LayerSection(
            title = "College Toolkit",
            items = ScreenDataSource.collegeLayer,
            onClick = onCardClick,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LayerSection(
            title = "Letters & Communication",
            items = ScreenDataSource.documentsAndComms,
            onClick = onCardClick,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LayerSection(
            title = "Academic Essentials",
            items = ScreenDataSource.academicEssentials,
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
                append(" Privacy sealed by — ")
                pushStringAnnotation(
                    tag = "privacy",
                    annotation = "https://kaustubhdeshpande.tech/privacypolicy-collegecompanion"
                )
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF0D47A1),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("@Kaustubh Deshpande")
                }
                pop()
            },
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .clickable {
                    uriHandler.openUri("https://kaustubhdeshpande.tech/privacypolicy-collegecompanion")
                }
        )

    }
}


@Preview
@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun DashboardScreenPreview() {
    Internship1ProjectTheme {
        DashboardScreen { }
    }
}
