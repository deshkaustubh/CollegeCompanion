package tech.kaustubhdeshpande.collegecompanion.screens.dashboard


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.delay
import tech.kaustubhdeshpande.collegecompanion.R
import tech.kaustubhdeshpande.collegecompanion.data.ScreenItem
import tech.kaustubhdeshpande.collegecompanion.ui.theme.Internship1ProjectTheme
import java.time.LocalDate

// Complete Punch card with background gradients
@RequiresApi(Build.VERSION_CODES.O)
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
                    Row {

                        Image(
                            painter = painterResource(id = R.drawable.collegecompanionsplash),
                            contentDescription = "College Companion",
                            modifier = Modifier.size(100.dp)
                        )

                        MotivationalQuoteBanner()
                    }
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
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
            .height(120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(15.dp),
        border = CardDefaults.outlinedCardBorder(enabled = true),
        elevation = CardDefaults.cardElevation(8.dp),
        onClick = { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFe3f2fd))
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
                tint = Color(0xFF1565C0),
                modifier = Modifier
//                    .width(46.dp)
//                    .height(44.dp)
                    .size(46.dp)
                    .padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = optionCardText,
                fontSize = 12.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 2.dp, start = 4.dp, end = 4.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


// option card grid
@Composable
fun LayerSection(
    title: String,
    items: List<ScreenItem>,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            modifier = modifier.padding(start = 8.dp, bottom = 8.dp, end = 8.dp, top = 8.dp),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 0.dp, max = 600.dp), // prevents inner scroll
            userScrollEnabled = false, // disable nested scroll
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            items(items) { item ->
                DashboardOptionCard(
                    optionIcon = item.icon,
                    optionCardText = item.title,
                    onClick = { onClick(item.title) }
                )
            }
        }
    }
}


val dailyQuotes = listOf(
    "GPA? Please. I built a flying suit in a cave… with a box of scraps.",
    "Success isn't luck. It's attitude… and maybe a hint of nanotech.",
    "Deadlines are just opportunities with better PR.",
    "Sleep is great — but so is a 4.0.",
    "If you're not breaking something, you're probably not building anything.",
    "You can calculate SGPA. I’ll calculate the probability of multiverse collapse.",
    "Not all heroes wear capes. Some craft perfect leave letters.",
    "Don’t let perfection be the enemy of done. Ship it.",
    "Overthinking is fine — until the reactor melts down.",
    "You’re not behind. You’re just in stealth mode.",
    "The difference between a dropout and a legend? Focus. And maybe lasers.",
    "Procrastinate if you must… just make the comeback look effortless.",
    "Style it. Ship it. Walk away while it explodes in the background.",
    "Let your attendance be questionable. Never your ambition.",
    "Innovation is just rebellion with a blueprint.",
    "The tools don’t make the genius. But they *do* help.",
    "When in doubt, upgrade your mindset. And your UI.",
    "If it scares you a little, you’re doing it right.",
    "Letters, GPA, excuses — all data. What you do with it? That’s the story.",
    "No magic. Just math and momentum.",
    "Keep your builds clean. And your intentions cleaner.",
    "Sometimes the best feature is hitting 'Send'.",
    "You're not an imposter. You're just pre-release.",
    "Vision without execution? That’s a hallucination.",
    "Let failure happen. Just make it beta-worthy.",
    "Study like your AI assistant's watching.",
    "Save the drama for your draft folder.",
    "Logs exist so we don’t repeat the chaos. Use them.",
    "Hack the system, but debug your mind.",
    "You’re not just surviving college. You’re prototype-testing greatness.",
    "Rewrite the script. Be the update."
)


@Composable
fun TypewriterQuote(
    text: String,
    delayMillis: Long = 100L,
    pauseMillis: Long = 2500L
) {
    var visibleText by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        while (true) {
            visibleText = ""
            text.forEachIndexed { i, _ ->
                delay(delayMillis)
                visibleText = text.take(i + 1)
            }
            delay(pauseMillis) // Pause before restarting
        }
    }

    Text(
        text = visibleText,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MotivationalQuoteBanner(modifier: Modifier = Modifier) {
    val today = LocalDate.now().dayOfMonth
    val quote = dailyQuotes.getOrNull(today - 1) ?: "You showed up — that’s step one."

    Surface(
        color = Color.Transparent,
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 4.dp,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(Modifier.padding(12.dp)) {
            TypewriterQuote(quote)
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun DashboardComposablePreview() {
    Internship1ProjectTheme {
        DashboardPunchCard()
    }
}


