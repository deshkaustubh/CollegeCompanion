package tech.kaustubhdeshpande.collegecompanion.screens.collegeLayer.holidayHacker

import android.content.Intent
import android.os.Build
import android.os.SystemClock
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LongLeavePlanner(
    navigateBack: () -> Unit,
    viewModel: HolidayViewModel = viewModel()
) {
    val breakSuggestions by viewModel.breakSuggestions.collectAsState()
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val now = LocalDate.now()
    val monthsRaw = remember { Month.entries }
    val allMonths by remember(now) {
        derivedStateOf {
            val idx = monthsRaw.indexOf(now.month)
            monthsRaw.drop(idx) + monthsRaw.take(idx)
        }
    }
    var selectedMonthIdx by remember { mutableStateOf(0) }
    var lastBackPressTime by rememberSaveable { mutableStateOf(0L) }
    val debounceInterval = 500L // milliseconds

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .statusBarsPadding(),
                title = {
                    Text(
                        text = "Long Leave Planner",
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
                        onClick = {
                            val now = SystemClock.elapsedRealtime()
                            if (now - lastBackPressTime > debounceInterval) {
                                lastBackPressTime = now
                                navigateBack()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Your Next Epic Break Awaits! 🌴",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Month selector
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    allMonths.forEachIndexed { idx, month ->
                        val isSelected = idx == selectedMonthIdx
                        Box(
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isSelected) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
                                )
                                .clickable { selectedMonthIdx = idx }
                                .padding(horizontal = 18.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = month.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                                    .replaceFirstChar { it.uppercase() },
                                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                val selectedMonth = allMonths[selectedMonthIdx]
                val breaks = breakSuggestions[selectedMonth].orEmpty()

                Text(
                    text = selectedMonth.getDisplayName(TextStyle.FULL, Locale.getDefault())
                        .uppercase(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )

                if (breaks.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        items(breaks, key = { "${it.start}-${it.end}" }) { breakInfo ->
                            DaysRow(breakInfo = breakInfo)
                            Spacer(modifier = Modifier.height(12.dp))
                            BreakCard(
                                breakInfo = breakInfo,
                                onShare = {
                                    val shareText = buildShareText(breakInfo)
                                    val sendIntent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(Intent.EXTRA_TEXT, shareText)
                                        type = "text/plain"
                                    }
                                    val shareIntent = Intent.createChooser(sendIntent, null)
                                    context.startActivity(shareIntent)
                                },
                                onCopy = {
                                    clipboardManager.setText(
                                        AnnotatedString(
                                            buildShareText(
                                                breakInfo
                                            )
                                        )
                                    )
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar("Break details copied!")
                                    }
                                }
                            )
                        }
                    }
                } else {
                    Text(
                        text = "No long breaks found for ${
                            selectedMonth.getDisplayName(
                                TextStyle.FULL,
                                Locale.getDefault()
                            )
                        }. Try another month!",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp)
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DaysRow(
    breakInfo: BreakSuggestion,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val days = (0 until breakInfo.length).map { breakInfo.start.plusDays(it.toLong()) }
        days.forEach { day ->
            val isHoliday = breakInfo.holidays.any { it.date == day }
            val isLeave = breakInfo.leaves.contains(day)
            val isWeekend = breakInfo.weekends.contains(day)
            val color = when {
                isLeave -> MaterialTheme.colorScheme.tertiaryContainer
                isHoliday -> MaterialTheme.colorScheme.secondaryContainer
                isWeekend -> MaterialTheme.colorScheme.primaryContainer
                else -> MaterialTheme.colorScheme.surfaceVariant
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(color),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = day.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                            .take(3),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = day.dayOfMonth.toString(),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }
}

data class BreakSuggestion(
    val start: LocalDate,
    val end: LocalDate,
    val length: Int,
    val holidays: List<Holiday>,
    val weekends: List<LocalDate>,
    val leaves: List<LocalDate>
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BreakCard(
    breakInfo: BreakSuggestion,
    onShare: () -> Unit,
    onCopy: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "${breakInfo.holidays.joinToString { it.emoji }} ${breakInfo.length}-Day Break!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            if (breakInfo.leaves.isNotEmpty()) {
                val leaveDays = breakInfo.leaves.joinToString {
                    it.dayOfWeek.getDisplayName(
                        TextStyle.SHORT,
                        Locale.getDefault()
                    )
                }
                Text(
                    text = "Take leave on $leaveDays",
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "${breakInfo.start} ➔ ${breakInfo.end}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 2.dp)
            )
            Text(
                text = "Holidays in this break:",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.padding(top = 8.dp)
            )
            breakInfo.holidays.forEach {
                Text(
                    "• ${it.name}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onShare,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Share")
                }
                Button(
                    onClick = onCopy,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Copy")
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun findAllPossibleBreaksByMonth(
    holidays: List<Holiday>,
    minBreakLength: Int = 3,
    maxLeave: Int = 2
): Map<Month, List<BreakSuggestion>> {
    if (holidays.isEmpty()) return emptyMap()
    val year = holidays.first().date.year
    val holidayDates = holidays.map { it.date }.toSet()
    val monthMap = Month.entries.associateWith { mutableListOf<BreakSuggestion>() }
    val allDays = generateSequence(LocalDate.of(year, 1, 1)) { it.plusDays(1) }
        .takeWhile { it.year == year }
        .toList()

    for (month in Month.entries) {
        val daysInMonth = allDays.filter { it.month == month }
        for (i in daysInMonth.indices) {
            for (len in minBreakLength..daysInMonth.size - i) {
                val window = daysInMonth.subList(i, i + len)
                val holidaysInWindow = window.filter { it in holidayDates }
                    .mapNotNull { d -> holidays.find { it.date == d } }
                val weekendsInWindow =
                    window.filter { it.dayOfWeek == DayOfWeek.SATURDAY || it.dayOfWeek == DayOfWeek.SUNDAY }
                val leaveDays =
                    window.filter { it !in holidayDates && it.dayOfWeek != DayOfWeek.SATURDAY && it.dayOfWeek != DayOfWeek.SUNDAY }
                if (leaveDays.size in 0..maxLeave && holidaysInWindow.isNotEmpty()) {
                    monthMap[month]?.add(
                        BreakSuggestion(
                            start = window.first(),
                            end = window.last(),
                            length = window.size,
                            holidays = holidaysInWindow,
                            weekends = weekendsInWindow,
                            leaves = leaveDays
                        )
                    )
                }
            }
        }
        // Remove overlapping shorter breaks
        monthMap[month]?.sortBy { it.start }
        monthMap[month]?.let { breaks ->
            val filtered = mutableListOf<BreakSuggestion>()
            var lastCovered = LocalDate.MIN
            for (b in breaks.sortedWith(compareBy({ -it.length }, { it.start }))) {
                if (b.start > lastCovered) {
                    filtered.add(b)
                    lastCovered = b.end
                }
            }
            monthMap[month]?.clear()
            monthMap[month]?.addAll(filtered.sortedBy { it.start })
        }
    }
    return monthMap
}

@RequiresApi(Build.VERSION_CODES.O)
fun buildShareText(breakInfo: BreakSuggestion): String {
    val holidaysList = breakInfo.holidays.joinToString { it.name }
    val leaveDays =
        if (breakInfo.leaves.isNotEmpty())
            "Take leave on " + breakInfo.leaves.joinToString {
                it.dayOfWeek.getDisplayName(
                    TextStyle.FULL,
                    Locale.getDefault()
                )
            }
        else ""
    return "🎉 ${breakInfo.length}-Day Break!\n" +
            (if (leaveDays.isNotBlank()) "$leaveDays\n" else "") +
            "${breakInfo.start} ➔ ${breakInfo.end}\n" +
            "Holidays in this break: $holidaysList\n" +
            "Found using College Companion!"
}