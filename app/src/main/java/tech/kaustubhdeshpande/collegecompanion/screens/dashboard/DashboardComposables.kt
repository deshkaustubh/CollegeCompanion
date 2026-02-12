package tech.kaustubhdeshpande.collegecompanion.screens.dashboard


import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
    IconBackgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(bottom = 8.dp, top = 0.dp, start = 8.dp, end = 8.dp)
            .width(120.dp)
//            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
            .height(140.dp)
            .shadow(8.dp, shape = RoundedCornerShape(16.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
//        border = CardDefaults.outlinedCardBorder(enabled = true),
        elevation = CardDefaults.cardElevation(12.dp),
        onClick = { onClick() },
        colors = CardDefaults.cardColors(Color.White)
//        colors = CardDefaults.cardColors(containerColor = Color(0xFFe3f2fd))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Icon(
                imageVector = optionIcon,
                contentDescription = null,
//                tint = Color(0xFF1565C0),
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
//                    .width(46.dp)
//                    .height(44.dp)
                    .size(56.dp)
                    .background(IconBackgroundColor, shape = RoundedCornerShape(16.dp))
                    .padding(14.dp)
            )
//            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = optionCardText,
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(bottom = 8.dp, start = 2.dp, end = 0.dp),
                maxLines = 2,
//                color = MaterialTheme.colorScheme.primary
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
//            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
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
                    onClick = { onClick(item.title) },
                    IconBackgroundColor = item.backgroundColor,
                )
            }
        }
    }
}


val dailyQuotes = listOf(
    // Day 1–5: Legendary Openers
    "Mogambo khush hua!",                                   // Mr. India — iconic villain line :contentReference[oaicite:1]{index=1}
    "Kitne aadmi the?",                                     // Sholay — cult classic :contentReference[oaicite:2]{index=2}
    "Mere paas maa hai.",                                   // Deewar — emotional blockbuster line :contentReference[oaicite:3]{index=3}
    "Babumoshai, zindagi badi honi chahiye, lambi nahi.",    // Anand — life lesson :contentReference[oaicite:4]{index=4}
    "Bade bade deshon mein aisi chhoti chhoti baatein hoti rehti hain… Senorita.", // DDLJ — everyday iconic :contentReference[oaicite:5]{index=5}

    // Day 6–10: Friendship & Love Classics
    "Dosti ka ek usool hai madam — no sorry, no thank you.", // Maine Pyaar Kiya — friendship classic :contentReference[oaicite:6]{index=6}
    "Beta, tumse na ho payega.",                            // Gangs of Wasseypur — popular meme quote :contentReference[oaicite:7]{index=7}
    "Aaj khush to bahut hoge tum.",                         // Deewaar — heartfelt line :contentReference[oaicite:8]{index=8}
    "Uska to na bad luck hi kharab hai.",                    // Rangeela — frequently quoted :contentReference[oaicite:9]{index=9}
    "Hum jahan khade hote hain, line wahi se shuru hoti hai.", // Kaalia — swagger quote :contentReference[oaicite:10]{index=10}

    // Day 11–15: Powerful & Fun
    "Don ko pakadna mushkil hi nahi, namumkin hai.",       // Don — confidence line :contentReference[oaicite:11]{index=11}
    "Ek chutki sindoor ki keemat tum kya jaano Ramesh Babu?",// Om Shanti Om — meme favorite :contentReference[oaicite:12]{index=12}
    "Tareekh pe tareekh, tareekh pe tareekh!",              // Damini — dramatic complaint :contentReference[oaicite:13]{index=13}
    "Aloo le lo… kaande le lo…",                            // Om Shanti Om — comic style :contentReference[oaicite:14]{index=14}
    "Pushpa, I hate tears!",                                // Amar Prem — classic vintage line :contentReference[oaicite:15]{index=15}

    // Day 16–20: Emotional & Relatable
    "Main apni favourite hoon.",                            // Jab We Met — self-love line :contentReference[oaicite:16]{index=16}
    "Kaustubh… naam toh suna hoga.",                           // Dil To Pagal Hai — smooth intro :contentReference[oaicite:17]{index=17}
    "Rishtey mein toh hum tumhare baap lagte hai, naam hai Shahenshah!", // Shahenshah — swagger classic :contentReference[oaicite:18]{index=18}
    "Zindagi mein teen cheezon ke peechey kabhi nahin bhagna chahiye — bus, train aur chhokri.", // Ishq — fun principle :contentReference[oaicite:19]{index=19}
    "Picture abhi baaki hai mere dost.",                    // Om Shanti Om — optimistic finish :contentReference[oaicite:20]{index=20}

    // Day 21–25: Everyday & Memorable
    "Ye Hath Humko De De Thakur!",                         // Sholay — dramatic plea :contentReference[oaicite:21]{index=21}
    "All izz well!",                                        // 3 Idiots — casual mantra :contentReference[oaicite:22]{index=22}
    "Koi pyaar kare toh tumse kare, tum jaise ho waise kare.",           // Bobby — romantic advice :contentReference[oaicite:23]{index=23}
    "I can talk English, I can walk English… because English is a very phunny language.", // Namak Halal — classic cool line :contentReference[oaicite:24]{index=24}
    "Hindustan zindabad tha, zindabad hai, aur zindabad rahega!", // Gadar: Ek Prem Katha — patriotic classic :contentReference[oaicite:25]{index=25}

    // Day 26–31: Fun & Classic Closeouts
    "Ye adaalat jazbaato se nahi chalte, milord!",          // Court drama classic (used in many films) :contentReference[oaicite:26]{index=26}
    "Utha le re baba…",                                     // Hera Pheri — comedy favorite :contentReference[oaicite:27]{index=27}
    "Jab tak baithne ko na kaha jaye…",                     // Police banter line (classic style) :contentReference[oaicite:28]{index=28}
    "Basanti, tumhara naam kya hai?",                      // Sholay — memorable intro :contentReference[oaicite:29]{index=29}
    "Tum log mujhe dhund rahe ho aur main yaha intezaar kar raha hun.", // Dramatic classic (Bollywood style) :contentReference[oaicite:30]{index=30}
    "How’s the josh? High, sir!",                           // URI: The Surgical Strike — energetic catchphrase :contentReference[oaicite:31]{index=31}
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
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(horizontal = 4.dp)
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
@Composable
fun TopSection(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        Color(0xFF1976D2), // Medium blue
                        Color(0xFF00AAFF),  // Light blue at the bottom
                    )
                )
            )
    ) {
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Welcome Back!",
                        modifier = modifier.padding(start = 16.dp),
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "College",
                        modifier = modifier.padding(start = 16.dp),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.height(0.dp))
                    Text(
                        text = "Companion",
                        modifier = modifier.padding(start = 16.dp),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Column(modifier = Modifier.padding(bottom = 24.dp, end = 32.dp)) {
                    val context = LocalContext.current
                    Image(
                        painter = painterResource(R.drawable.ghibli_kd_stripped_shirt_profile),
                        contentDescription = "College companion",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(56.dp)
                            .clickable {
                                val mySiteLink = "https://kaustubhdeshpande.tech/"
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mySiteLink))
                                context.startActivity(intent)
                            }
                            .border(
                                2.dp,
                                color = MaterialTheme.colorScheme.onPrimary.copy(0.5f),
                                shape = CircleShape
                            ),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .height(120.dp)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.onPrimary.copy(0.4f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp,
                            topEnd = 16.dp,
                            topStart = 16.dp
                        )
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary.copy(0.2f)
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
private fun DashboardComposablePreview() {
    Internship1ProjectTheme {
        TopSection()
    }
}


