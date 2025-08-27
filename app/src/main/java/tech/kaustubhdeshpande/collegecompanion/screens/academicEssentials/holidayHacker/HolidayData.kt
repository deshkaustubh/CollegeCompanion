package tech.kaustubhdeshpande.collegecompanion.screens.academicEssentials.holidayHacker

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
val maharashtraHolidays2025 = listOf(
    Holiday(
        name = "Republic Day",
        date = LocalDate.of(2025, 1, 26),
        emoji = "🇮🇳",
        type = HolidayType.PUBLIC,
        notes = "National holiday"
    ),
    Holiday(
        name = "Chhatrapati Shivaji Maharaj Jayanti",
        date = LocalDate.of(2025, 2, 19),
        emoji = "🏇",
        type = HolidayType.PUBLIC,
        notes = "State public holiday"
    ),
    Holiday(
        name = "Maha Shivaratri",
        date = LocalDate.of(2025, 2, 26),
        emoji = "🕉️",
        type = HolidayType.PUBLIC
    ),
    Holiday(
        name = "Holi",
        date = LocalDate.of(2025, 3, 14),
        emoji = "🌈",
        type = HolidayType.PUBLIC,
        notes = "Holi Dahan"
    ),
    Holiday(
        name = "Gudi Padwa",
        date = LocalDate.of(2025, 3, 30),
        emoji = "🚩",
        type = HolidayType.PUBLIC,
        notes = "Maharashtrian New Year"
    ),
    Holiday(
        name = "Eid al-Fitr",
        date = LocalDate.of(2025, 3, 31),
        emoji = "🕌",
        type = HolidayType.PUBLIC,
        notes = "Date may adjust with moon sighting"
    ),
    Holiday(
        name = "Ram Navami",
        date = LocalDate.of(2025, 4, 6),
        emoji = "🌞",
        type = HolidayType.PUBLIC
    ),
    Holiday(
        name = "Mahavir Jayanti",
        date = LocalDate.of(2025, 4, 10),
        emoji = "🕊️",
        type = HolidayType.PUBLIC
    ),
    Holiday(
        name = "Dr. Ambedkar Jayanti",
        date = LocalDate.of(2025, 4, 14),
        emoji = "📚",
        type = HolidayType.PUBLIC,
        notes = "Birth anniversary of Dr. B.R. Ambedkar"
    ),
    Holiday(
        name = "Good Friday",
        date = LocalDate.of(2025, 4, 18),
        emoji = "✝️",
        type = HolidayType.PUBLIC
    ),
    Holiday(
        name = "Maharashtra Day",
        date = LocalDate.of(2025, 5, 1),
        emoji = "🌴",
        type = HolidayType.PUBLIC,
        notes = "Formation day of Maharashtra"
    ),
    Holiday(
        name = "Buddha Purnima",
        date = LocalDate.of(2025, 5, 12),
        emoji = "☸️",
        type = HolidayType.PUBLIC
    ),
    Holiday(
        name = "Bakri Eid (Eid al-Adha)",
        date = LocalDate.of(2025, 6, 7),
        emoji = "🐐",
        type = HolidayType.PUBLIC,
        notes = "Date may adjust with moon sighting"
    ),
    Holiday(
        name = "Muharram",
        date = LocalDate.of(2025, 7, 6),
        emoji = "☪️",
        type = HolidayType.PUBLIC,
        notes = "Date may adjust with moon sighting"
    ),
    Holiday(
        name = "Independence Day",
        date = LocalDate.of(2025, 8, 15),
        emoji = "🇮🇳",
        type = HolidayType.PUBLIC,
        notes = "National holiday"
    ),
    Holiday(
        name = "Raksha Bandhan",
        date = LocalDate.of(2025, 8, 9),
        emoji = "🪢",
        type = HolidayType.OPTIONAL,
        notes = "Optional in Maharashtra"
    ),
    Holiday(
        name = "Ganesh Chaturthi",
        date = LocalDate.of(2025, 8, 27),
        emoji = "🐘",
        type = HolidayType.PUBLIC,
        notes = "Major festival in Maharashtra"
    ),
    Holiday(
        name = "Id-E-Milad",
        date = LocalDate.of(2025, 9, 5),
        emoji = "🕌",
        type = HolidayType.PUBLIC,
        notes = "Date may adjust with moon sighting"
    ),
    Holiday(
        name = "Gandhi Jayanti",
        date = LocalDate.of(2025, 10, 2),
        emoji = "👓",
        type = HolidayType.PUBLIC
    ),
    Holiday(
        name = "Dussehra (Vijaya Dashami)",
        date = LocalDate.of(2025, 10, 2),
        emoji = "🪔",
        type = HolidayType.PUBLIC
    ),
    Holiday(
        name = "Diwali (Laxmi Pujan)",
        date = LocalDate.of(2025, 10, 21),
        emoji = "🪔",
        type = HolidayType.PUBLIC,
        notes = "Main Diwali day"
    ),
    Holiday(
        name = "Diwali (Balipratipada)",
        date = LocalDate.of(2025, 10, 22),
        emoji = "🪔",
        type = HolidayType.PUBLIC
    ),
    Holiday(
        name = "Guru Nanak Jayanti",
        date = LocalDate.of(2025, 11, 5),
        emoji = "🛕",
        type = HolidayType.PUBLIC
    ),
    Holiday(
        name = "Christmas",
        date = LocalDate.of(2025, 12, 25),
        emoji = "🎄",
        type = HolidayType.PUBLIC
    )
)