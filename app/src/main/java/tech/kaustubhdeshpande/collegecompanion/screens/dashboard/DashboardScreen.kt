package tech.kaustubhdeshpande.collegecompanion.screens.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.kaustubhdeshpande.collegecompanion.data.ScreenDataSource
import tech.kaustubhdeshpande.collegecompanion.ui.theme.Internship1ProjectTheme


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardContent(
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
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
            modifier = Modifier.padding(start = 8.dp, end =8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LayerSection(
            title = "Letters & Communication",
            items = ScreenDataSource.documentsAndComms,
            onClick = onCardClick,
            modifier = Modifier.padding(start = 8.dp, end =8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LayerSection(
            title = "College Toolkit",
            items = ScreenDataSource.collegeLayer,
            onClick = onCardClick,
            modifier = Modifier.padding(start = 8.dp, end =8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LayerSection(
            title = "Focus & Enrichment",
            items = ScreenDataSource.focusAndExtras,
            onClick = onCardClick,
            modifier = Modifier.padding(start = 8.dp, end =8.dp)
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardScreen(modifier: Modifier = Modifier, onCardClick: (String) -> Unit) {
    Scaffold(
        topBar = { DashboardTopAppBar() }
    ) {innerPadding->
        DashboardContent(onCardClick = onCardClick, modifier = Modifier.padding(innerPadding))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun DashboardScreenPreview() {
    Internship1ProjectTheme {
        DashboardScreen {  }
    }
}