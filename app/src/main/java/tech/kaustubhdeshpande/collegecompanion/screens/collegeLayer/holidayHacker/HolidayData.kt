package tech.kaustubhdeshpande.collegecompanion.screens.collegeLayer.holidayHacker

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

enum class HolidayType {
    PUBLIC, RESTRICTED, OPTIONAL, OBSERVANCE
}

data class Holiday(
    val name: String,
    val date: LocalDate,
    val emoji: String = "",
    val type: HolidayType = HolidayType.PUBLIC,
    val notes: String = ""
)

@RequiresApi(Build.VERSION_CODES.O)
val maharashtraHolidays2026 = listOf(
    // National / State Public Holidays
    Holiday(
        name = "Republic Day",
        date = LocalDate.of(2026, 1, 26),
        emoji = "🇮🇳",
        type = HolidayType.PUBLIC,
        notes = "National holiday observed across India"
    ),
    Holiday(
        name = "Maha Shivaratri",
        date = LocalDate.of(2026, 2, 15),
        emoji = "🕉️",
        type = HolidayType.PUBLIC,
        notes = "Hindu festival honoring Lord Shiva"
    ),
    Holiday(
        name = "Chhatrapati Shivaji Maharaj Jayanti",
        date = LocalDate.of(2026, 2, 19),
        emoji = "🏇",
        type = HolidayType.PUBLIC,
        notes = "Birthday of Chhatrapati Shivaji Maharaj (State holiday)"
    ),
    Holiday(
        name = "Holi",
        date = LocalDate.of(2026, 3, 3),
        emoji = "🌈",
        type = HolidayType.PUBLIC,
        notes = "Festival of colours"
    ),
    Holiday(
        name = "Gudi Padwa",
        date = LocalDate.of(2026, 3, 19),
        emoji = "🚩",
        type = HolidayType.PUBLIC,
        notes = "Maharashtrian New Year"
    ),
    Holiday(
        name = "Eid al-Fitr",
        date = LocalDate.of(2026, 3, 21),
        emoji = "🕌",
        type = HolidayType.PUBLIC,
        notes = "Ramzan Eid — date based on moon sighting"
    ),
    Holiday(
        name = "Ram Navami",
        date = LocalDate.of(2026, 3, 26),
        emoji = "🌞",
        type = HolidayType.PUBLIC
    ),
    Holiday(
        name = "Mahavir Jayanti",
        date = LocalDate.of(2026, 3, 31),
        emoji = "🕊️",
        type = HolidayType.PUBLIC
    ),
    Holiday(
        name = "Good Friday",
        date = LocalDate.of(2026, 4, 3),
        emoji = "✝️",
        type = HolidayType.PUBLIC,
        notes = "Christian observance"
    ),
    Holiday(
        name = "Dr. Babasaheb Ambedkar Jayanti",
        date = LocalDate.of(2026, 4, 14),
        emoji = "📚",
        type = HolidayType.PUBLIC,
        notes = "Birth anniversary of Dr. B.R. Ambedkar"
    ),
    Holiday(
        name = "Maharashtra Day",
        date = LocalDate.of(2026, 5, 1),
        emoji = "🌴",
        type = HolidayType.PUBLIC,
        notes = "State formation day"
    ),
    Holiday(
        name = "Buddha Purnima",
        date = LocalDate.of(2026, 5, 1),
        emoji = "☸️",
        type = HolidayType.PUBLIC,
        notes = "Birth of Gautama Buddha"
    ),
    Holiday(
        name = "Bakri Eid (Eid al-Adha)",
        date = LocalDate.of(2026, 5, 28),
        emoji = "🐐",
        type = HolidayType.PUBLIC,
        notes = "Festival of sacrifice (moon sighting based)"
    ),
    Holiday(
        name = "Muharram",
        date = LocalDate.of(2026, 6, 26),
        emoji = "☪️",
        type = HolidayType.PUBLIC,
        notes = "Islamic New Year observance"
    ),
    Holiday(
        name = "Independence Day",
        date = LocalDate.of(2026, 8, 15),
        emoji = "🇮🇳",
        type = HolidayType.PUBLIC,
        notes = "National holiday"
    ),
    Holiday(
        name = "Parsi New Year (Shahenshahi)",
        date = LocalDate.of(2026, 8, 15),
        emoji = "🕊️",
        type = HolidayType.OBSERVANCE,
        notes = "Cultural observance (Parsi community)"
    ),
    Holiday(
        name = "Eid-e-Milad",
        date = LocalDate.of(2026, 8, 26),
        emoji = "🕌",
        type = HolidayType.PUBLIC,
        notes = "Birth of Prophet Muhammad (approx.)"
    ),
    Holiday(
        name = "Ganesh Chaturthi",
        date = LocalDate.of(2026, 9, 14),
        emoji = "🐘",
        type = HolidayType.PUBLIC,
        notes = "Major festival in Maharashtra"
    ),
    Holiday(
        name = "Gandhi Jayanti",
        date = LocalDate.of(2026, 10, 2),
        emoji = "👓",
        type = HolidayType.PUBLIC,
        notes = "Birth anniversary of Mahatma Gandhi"
    ),
    Holiday(
        name = "Dussehra (Vijaya Dashami)",
        date = LocalDate.of(2026, 10, 20),
        emoji = "🪔",
        type = HolidayType.PUBLIC
    ),
    Holiday(
        name = "Diwali (Laxmi Pujan)",
        date = LocalDate.of(2026, 11, 8),
        emoji = "🪔",
        type = HolidayType.PUBLIC
    ),
    Holiday(
        name = "Diwali (Balipratipada)",
        date = LocalDate.of(2026, 11, 10),
        emoji = "🪔",
        type = HolidayType.PUBLIC
    ),
    Holiday(
        name = "Bhaubeej",
        date = LocalDate.of(2026, 11, 11),
        emoji = "👫",
        type = HolidayType.PUBLIC,
        notes = "Special extra state holiday (Diwali festival)"
    ),
    Holiday(
        name = "Guru Nanak Jayanti",
        date = LocalDate.of(2026, 11, 24),
        emoji = "🛕",
        type = HolidayType.PUBLIC
    ),
    Holiday(
        name = "Christmas",
        date = LocalDate.of(2026, 12, 25),
        emoji = "🎄",
        type = HolidayType.PUBLIC
    )
)