package tech.kaustubhdeshpande.collegecompanion.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.FactCheck
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.EventBusy
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.Percent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class ScreenItem(
    val title: String,
    val icon: ImageVector,
    val backgroundColor: Color
)

object ScreenDataSource {

    val academicEssentials = listOf(
        ScreenItem("Simple Calculator", Icons.Default.Calculate, Color(0xFF9C27B0)), // Purple
        ScreenItem("GPA 1-0-1", Icons.Default.Info, Color(0xFFFF9800)),         // Orange
        ScreenItem("CGPA Calculator", Icons.Default.School, Color(0xFF4CAF50)), // Green
        ScreenItem("SGPA Calculator", Icons.Default.School, Color(0xFF2196F3)) // Blue
    )

    val documentsAndComms = listOf(
        ScreenItem("Excuse Generator", Icons.AutoMirrored.Filled.Chat, Color(0xFF00BCD4)), // Cyan
        ScreenItem("Holidays Tracker", Icons.Default.Event, Color(0xFFFF5722)), // Deep Orange
        ScreenItem("Letter Lab", Icons.Default.Description, Color(0xFF3F51B5)), // Indigo
        ScreenItem("Mail Generator", Icons.AutoMirrored.Filled.Send, Color(0xFFE91E63)) // Pink
    )

    val collegeLayer = listOf(
        ScreenItem("Fill My Cycle", Icons.AutoMirrored.Filled.FactCheck, Color(0xFFCDDC39)), // Lime
        ScreenItem("75% Club", Icons.Outlined.Percent, Color(0xFF607D8B)), // Blue Grey
        ScreenItem("Academic Calendar", Icons.Default.CalendarMonth, Color(0xFF009688)), // Teal
        ScreenItem("Past Papers", Icons.AutoMirrored.Filled.MenuBook, Color(0xFF795548)), // Brown
        ScreenItem("Semester Plan", Icons.Default.DateRange, Color(0xFF3F51B5)), // Indigo (distinct from above, Letter Lab, so see note)
        ScreenItem("Long Leave Planner", Icons.Default.EventBusy, Color(0xFFFFC107)), // Amber (unique)
    )

    val focusAndExtras = listOf(
        ScreenItem("Pomodoro Timer", Icons.Default.Timer, Color(0xFF673AB7)) // Deep Purple
    )

    // Optional: All in one list for search/autocomplete use
    val allScreens = academicEssentials + documentsAndComms + collegeLayer + focusAndExtras
}
