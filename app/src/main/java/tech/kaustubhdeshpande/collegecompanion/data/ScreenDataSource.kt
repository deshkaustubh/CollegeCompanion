package tech.kaustubhdeshpande.collegecompanion.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.FactCheck
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Timer
import androidx.compose.ui.graphics.vector.ImageVector

data class ScreenItem(
    val title: String,
    val icon: ImageVector
)


object ScreenDataSource {

    val academicEssentials = listOf(
        ScreenItem("CGPA Calculator", Icons.Default.School),
        ScreenItem("SGPA Calculator", Icons.Default.School),
        ScreenItem("GPA 1-0-1", Icons.Default.Info),
        ScreenItem("Simple Calculator", Icons.Default.Calculate)
    )

    val documentsAndComms = listOf(
        ScreenItem("Letter Lab", Icons.Default.Description),
        ScreenItem("Mail Generator", Icons.AutoMirrored.Filled.Send),
        ScreenItem("Excuse Generator", Icons.AutoMirrored.Filled.Chat),
        ScreenItem("National Holidays", Icons.Default.Event)
    )

    val collegeLayer = listOf(
        ScreenItem("Academic Calendar", Icons.Default.CalendarMonth),
        ScreenItem("Lab Evaluation", Icons.AutoMirrored.Filled.FactCheck),
        ScreenItem("Past Papers", Icons.AutoMirrored.Filled.MenuBook),
    )

    val focusAndExtras = listOf(
        ScreenItem("Pomodoro Timer", Icons.Default.Timer),
    )

    // Optional: All in one list for search/autocomplete use
    val allScreens = academicEssentials + documentsAndComms + collegeLayer + focusAndExtras
}
