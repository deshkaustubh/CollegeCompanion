package tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.holidaysScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

data class Holiday(
    val name: String,
    val month: String, // "JAN", "FEB" etc.
    val date: String,  // "01", "15" etc.
    val type: String,  // "FIXED" / "VARIABLE"
    val day: String,   // "Monday" etc.
    val fullDate: LocalDate
)

class HolidayViewModel : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private fun dayOfWeek(date: LocalDate): String =
        date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH).uppercase()

    @RequiresApi(Build.VERSION_CODES.O)
    private val holidayList = listOf(
        Holiday(
            "Republic Day", "JAN", "26", "FIXED",
            dayOfWeek(LocalDate.of(2026, 1, 26)),
            LocalDate.of(2026, 1, 26)
        ),
        Holiday(
            "Maha Shivaratri", "FEB", "15", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 2, 15)),
            LocalDate.of(2026, 2, 15)
        ),
        Holiday(
            "Chhatrapati Shivaji Maharaj Jayanti", "FEB", "19", "FIXED",
            dayOfWeek(LocalDate.of(2026, 2, 19)),
            LocalDate.of(2026, 2, 19)
        ),
        Holiday(
            "Holi (Second Day)", "MAR", "03", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 3, 3)),
            LocalDate.of(2026, 3, 3)
        ),
        Holiday(
            "Gudi Padwa", "MAR", "19", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 3, 19)),
            LocalDate.of(2026, 3, 19)
        ),
        Holiday(
            "Eid al-Fitr (Ramzan Eid)", "MAR", "21", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 3, 21)),
            LocalDate.of(2026, 3, 21)
        ),
        Holiday(
            "Ram Navami", "MAR", "26", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 3, 26)),
            LocalDate.of(2026, 3, 26)
        ),
        Holiday(
            "Mahavir Jayanti", "MAR", "31", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 3, 31)),
            LocalDate.of(2026, 3, 31)
        ),
        Holiday(
            "Good Friday", "APR", "03", "FIXED",
            dayOfWeek(LocalDate.of(2026, 4, 3)),
            LocalDate.of(2026, 4, 3)
        ),
        Holiday(
            "Dr. Babasaheb Ambedkar Jayanti", "APR", "14", "FIXED",
            dayOfWeek(LocalDate.of(2026, 4, 14)),
            LocalDate.of(2026, 4, 14)
        ),
        Holiday(
            "Bank Holiday – Annual Accounts Closing", "APR", "01", "BANK_ONLY",
            dayOfWeek(LocalDate.of(2026, 4, 1)),
            LocalDate.of(2026, 4, 1)
        ),
        Holiday(
            "Maharashtra Day", "MAY", "01", "FIXED",
            dayOfWeek(LocalDate.of(2026, 5, 1)),
            LocalDate.of(2026, 5, 1)
        ),
        Holiday(
            "Buddha Purnima", "MAY", "01", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 5, 1)),
            LocalDate.of(2026, 5, 1)
        ),
        Holiday(
            "Bakri Eid (Eid al-Adha)", "MAY", "28", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 5, 28)),
            LocalDate.of(2026, 5, 28)
        ),
        Holiday(
            "Muharram", "JUN", "26", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 6, 26)),
            LocalDate.of(2026, 6, 26)
        ),
        Holiday(
            "Independence Day", "AUG", "15", "FIXED",
            dayOfWeek(LocalDate.of(2026, 8, 15)),
            LocalDate.of(2026, 8, 15)
        ),
        Holiday(
            "Parsi New Year (Shahenshahi)", "AUG", "15", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 8, 15)),
            LocalDate.of(2026, 8, 15)
        ),
        Holiday(
            "Eid-e-Milad", "AUG", "26", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 8, 26)),
            LocalDate.of(2026, 8, 26)
        ),
        Holiday(
            "Ganesh Chaturthi", "SEP", "14", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 9, 14)),
            LocalDate.of(2026, 9, 14)
        ),
        Holiday(
            "Gandhi Jayanti", "OCT", "02", "FIXED",
            dayOfWeek(LocalDate.of(2026, 10, 2)),
            LocalDate.of(2026, 10, 2)
        ),
        Holiday(
            "Dussehra (Vijaya Dashami)", "OCT", "20", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 10, 20)),
            LocalDate.of(2026, 10, 20)
        ),
        Holiday(
            "Diwali (Laxmi Pujan)", "NOV", "08", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 11, 8)),
            LocalDate.of(2026, 11, 8)
        ),
        Holiday(
            "Diwali (Balipratipada)", "NOV", "10", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 11, 10)),
            LocalDate.of(2026, 11, 10)
        ),
        Holiday(
            "Bhaubeej (Special Holiday)", "NOV", "11", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 11, 11)),
            LocalDate.of(2026, 11, 11)
        ),
        Holiday(
            "Guru Nanak Jayanti", "NOV", "24", "VARIABLE",
            dayOfWeek(LocalDate.of(2026, 11, 24)),
            LocalDate.of(2026, 11, 24)
        ),
        Holiday(
            "Christmas Day", "DEC", "25", "FIXED",
            dayOfWeek(LocalDate.of(2026, 12, 25)),
            LocalDate.of(2026, 12, 25)
        )
    )

    @RequiresApi(Build.VERSION_CODES.O)
    val today = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    val upcoming: List<Holiday> = holidayList.filter { it.fullDate >= today }

    @RequiresApi(Build.VERSION_CODES.O)
    val past: List<Holiday> = holidayList.filter { it.fullDate < today }
}