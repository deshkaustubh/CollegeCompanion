package tech.kaustubhdeshpande.collegecompanion.screens.collegeLayer.academicCalendar

package com.example.holidaylist

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.time.LocalDate


data class Holiday(
    val name: String,
    val month: String, // "JAN", "FEB" etc.
    val date: String,  // "01", "15" etc.
    val type: String,  // "Fixed Holiday" / "Variable Holiday"
    val day: String,   // "Monday" etc.
    val fullDate: LocalDate
)


class HolidayViewModel : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val holidayList = listOf(
        Holiday("New Year's Day", "JAN", "01", "FIXED", "WEDNESDAY", LocalDate.of(2025, 1, 1)),
        Holiday("Makar Sankranti", "JAN", "14", "FIXED", "TUESDAY", LocalDate.of(2025, 1, 14)),
        Holiday("Republic Day", "JAN", "26", "FIXED", "SUNDAY", LocalDate.of(2025, 1, 26)),

        Holiday(
            "Chhatrapati Shivaji Maharaj Jayanti",
            "FEB",
            "19",
            "FIXED",
            "WEDNESDAY",
            LocalDate.of(2025, 2, 19)
        ),
        Holiday("Maha Shivaratri", "FEB", "26", "VARIABLE", "WEDNESDAY", LocalDate.of(2025, 2, 26)),

        Holiday("Holi", "MAR", "14", "VARIABLE", "FRIDAY", LocalDate.of(2025, 3, 14)),
        Holiday("Gudi Padwa", "MAR", "30", "VARIABLE", "SUNDAY", LocalDate.of(2025, 3, 30)),
        Holiday("Eid-ul-Fitr", "MAR", "31", "VARIABLE", "MONDAY", LocalDate.of(2025, 3, 31)),

        Holiday("Ram Navami", "APR", "06", "VARIABLE", "SUNDAY", LocalDate.of(2025, 4, 6)),
        Holiday("Mahavir Jayanti", "APR", "10", "FIXED", "THURSDAY", LocalDate.of(2025, 4, 10)),
        Holiday("Dr Ambedkar Jayanti", "APR", "14", "FIXED", "MONDAY", LocalDate.of(2025, 4, 14)),
        Holiday("Good Friday", "APR", "18", "FIXED", "FRIDAY", LocalDate.of(2025, 4, 18)),

        Holiday("Maharashtra Day", "MAY", "01", "FIXED", "THURSDAY", LocalDate.of(2025, 5, 1)),
        Holiday("Buddha Purnima", "MAY", "12", "VARIABLE", "MONDAY", LocalDate.of(2025, 5, 12)),

        Holiday(
            "Bakrid / Eid al Adha",
            "JUN",
            "07",
            "VARIABLE",
            "SATURDAY",
            LocalDate.of(2025, 6, 7)
        ),

        Holiday("Muharram", "JUL", "06", "VARIABLE", "SUNDAY", LocalDate.of(2025, 7, 6)),

        Holiday("Independence Day", "AUG", "15", "FIXED", "FRIDAY", LocalDate.of(2025, 8, 15)),
        Holiday("Parsi New Year", "AUG", "16", "VARIABLE", "SATURDAY", LocalDate.of(2025, 8, 16)),
        Holiday(
            "Ganesh Chaturthi",
            "AUG",
            "27",
            "VARIABLE",
            "WEDNESDAY",
            LocalDate.of(2025, 8, 27)
        ),

        Holiday("Eid e Milad", "SEP", "05", "VARIABLE", "FRIDAY", LocalDate.of(2025, 9, 5)),

        Holiday("Gandhi Jayanti", "OCT", "02", "FIXED", "THURSDAY", LocalDate.of(2025, 10, 2)),
        Holiday("Diwali", "OCT", "21", "VARIABLE", "TUESDAY", LocalDate.of(2025, 10, 21)),
        Holiday("Deepavali Holiday", "OCT", "22", "FIXED", "WEDNESDAY", LocalDate.of(2025, 10, 22)),

        Holiday(
            "Guru Nanak Jayanti",
            "NOV",
            "05",
            "VARIABLE",
            "WEDNESDAY",
            LocalDate.of(2025, 11, 5)
        ),

        Holiday("Christmas Day", "DEC", "25", "FIXED", "THURSDAY", LocalDate.of(2025, 12, 25))
    )


    @RequiresApi(Build.VERSION_CODES.O)
    val today = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    val upcoming: List<Holiday> = holidayList.filter { it.fullDate >= today }

    @RequiresApi(Build.VERSION_CODES.O)
    val past: List<Holiday> = holidayList.filter { it.fullDate < today }
}