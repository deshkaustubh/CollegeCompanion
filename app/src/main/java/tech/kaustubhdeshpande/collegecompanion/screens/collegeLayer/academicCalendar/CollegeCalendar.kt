package tech.kaustubhdeshpande.collegecompanion.screens.collegeLayer.academicCalendar

data class CollegeCalendar(
    val id: String,
    val title: String,
    val university: String,
    val pdfUrl: String,
    val description: String
)

val collegeCalendars = listOf(

    CollegeCalendar(
        id = "mu_2024",
        title = "Academic Calendar 2024–25",
        university = "Mumbai University",
        description = "Official MU calendar for Engineering and Allied Courses.",
        pdfUrl = "https://drive.google.com/file/d/1-4OywI7wUdUy_TzmlhMA_8sEJ_VoDRZE/view?usp=drive_link"
    ),
    CollegeCalendar(
        id = "sppu_2024",
        title = "Academic Calendar 2024–25",
        university = "Savitribai Phule Pune University (SPPU)",
        description = "Tentative calendar for affiliated colleges and programs.",
        pdfUrl = "https://drive.google.com/file/d/1PpSxbPYR1rzG3vcPSn_yloI88_Mvqj29/view?usp=drive_link"
    ),
    CollegeCalendar(
        id = "tcet_2024",
        title = "Academic Calendar 2024–25",
        university = "TCET (Thakur College of Engineering and Technology)",
        description = "Full-term schedule including odd/even semester timelines.",
        pdfUrl = "https://drive.google.com/file/d/190X7eOx6bs-TQkNV3LZ7ktityHx7STB5/view?usp=drive_link"
    )
)
