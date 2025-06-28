package tech.kaustubhdeshpande.collegecompanion.screens.dashboard


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.kaustubhdeshpande.collegecompanion.R
import tech.kaustubhdeshpande.collegecompanion.data.ScreenItem
import tech.kaustubhdeshpande.collegecompanion.ui.theme.Internship1ProjectTheme

// Complete Punch card with background gradients
@Composable
fun DashboardPunchCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
//                            Color(0xFF1E1E1E), // Dark top bar color
                            Color(0xFF1976D2), // Medium blue
                            Color(0xFF00AAFF),  // Light blue at the bottom
//                            Color(0xFF0057B7) // Deep blue transition
//                            Color(0xFF80D8FF) // Softer, pastel blue
                        )
                    )
                )
                .padding(end = 24.dp, start = 24.dp, bottom = 32.dp)
        ) {
            // Inner Box with Whitish-Blue Fading Effect
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                    .height(140.dp) // Adjust height as needed
                    .padding(top = 8.dp)
                    .align(Alignment.TopCenter) // Position over the gradient
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFEFEFEF).copy(alpha = 0.7f),  // Fades out at the bottom
                                Color(0xFFEFEFEF).copy(alpha = 0.5f), // Light whitish-blue at the top
                                Color(0xFFEFEFEF).copy(alpha = 0.3f) // Slightly deeper blue in the middle
                            )
                        )
                    )
                    .padding(top = 8.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)
            ) {
                // main element -> Punch card
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 32.dp,
                                bottomEnd = 32.dp,
                                topStart = 1.dp,
                                topEnd = 1.dp
                            )
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFdaebfd)
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.collegecompanionsplash),
                        contentDescription = "College Companion",
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }
    }
}


// top bar of the dashboard screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopAppBar() {
    val scope = rememberCoroutineScope()
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,//MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = {
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    "College Companion",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    "Your Academic Side Kick",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        },
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.collegecompanionsplash),
                contentDescription = "App Logo",
                modifier = Modifier.size(80.dp),
                tint = Color.Unspecified
            )
        }
    )
}


// Option card element
@Composable
fun DashboardOptionCard(
    optionIcon: ImageVector,
    optionCardText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(bottom = 8.dp, top = 8.dp)
            .width(140.dp)
            .height(120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        onClick = { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Icon(
                imageVector = optionIcon,
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
//                    .width(46.dp)
//                    .height(44.dp)
                    .size(46.dp)
                    .padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = optionCardText,
                fontSize = 10.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 2.dp, start = 4.dp, end = 4.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


// option card Grid

@Composable
fun ScreenGrid(
    items: List<ScreenItem>,
    modifier: Modifier = Modifier,
    onCardClick: (String) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // or GridCells.Adaptive(minSize = 140.dp)
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items) { item ->
            DashboardOptionCard(
                optionIcon = item.icon,
                optionCardText = item.title,
                onClick = { onCardClick(item.title) },
            )
        }
    }
}

@Preview
@Composable
private fun DashboardComposablePreview() {
    Internship1ProjectTheme {
        DashboardTopAppBar()
    }
}


