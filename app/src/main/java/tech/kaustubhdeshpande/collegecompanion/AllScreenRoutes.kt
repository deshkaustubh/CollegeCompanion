package tech.kaustubhdeshpande.collegecompanion

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.kaustubhdeshpande.collegecompanion.screens.academicEssentials.cgpaCalculator.CGPAScreen
import tech.kaustubhdeshpande.collegecompanion.screens.academicEssentials.gpa101.GPA101Screen
import tech.kaustubhdeshpande.collegecompanion.screens.academicEssentials.sgpacalculator.SGPAScreen
import tech.kaustubhdeshpande.collegecompanion.screens.academicEssentials.simpleCalculator.SimpleCalculatorScreen
import tech.kaustubhdeshpande.collegecompanion.screens.collegeLayer.academicCalendar.AcademicCalendarScreen
import tech.kaustubhdeshpande.collegecompanion.screens.collegeLayer.labsheet.LabSheetScreen
import tech.kaustubhdeshpande.collegecompanion.screens.collegeLayer.pyq.PYQScreen
import tech.kaustubhdeshpande.collegecompanion.screens.dashboard.DashboardScreen
import tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.excuseGenerator.ExcuseGeneratorScreen
import tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.holidaysScreen.HolidaysScreen
import tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.letterGenerator.LetterLabHomeScreen
import tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.mailGenerator.MailGeneratorScreen
import tech.kaustubhdeshpande.collegecompanion.screens.focusAndExtras.pomodoro.PomodoroScreen

enum class AcademicEssentialsScreens {
    AcademicEssentialsDashboard,
    SimpleCalculator,
    GPA101,
    CGPACalculator,
    SGPACalculator,
}

enum class LettersAndCommunicationScreens {
    LetterLab,
    MailGenerator,
    ExcuseGenerator,
    Holidays
}

enum class CollegeLayerScreens {
    AcademicCalendar,
    LabSheet,
    Pyq
}

enum class FocusAndExtrasScreens{
    Pomodoro
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AllScreensGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AcademicEssentialsScreens.AcademicEssentialsDashboard.name
    ) {
        composable(AcademicEssentialsScreens.AcademicEssentialsDashboard.name) {
            DashboardScreen(
                onCardClick = { optionCardText ->
                    navController.navigate(
                        when (optionCardText) {
                            // Academic Essentials cards
                            "CGPA Calculator" -> AcademicEssentialsScreens.CGPACalculator.name
                            "SGPA Calculator" -> AcademicEssentialsScreens.SGPACalculator.name
                            "GPA 1-0-1" -> AcademicEssentialsScreens.GPA101.name
                            "Simple Calculator" -> AcademicEssentialsScreens.SimpleCalculator.name
                            //Letters and Communications Cards
                            "Letter Lab" -> LettersAndCommunicationScreens.LetterLab.name
                            "Mail Generator" -> LettersAndCommunicationScreens.MailGenerator.name
                            "Excuse Generator" -> LettersAndCommunicationScreens.ExcuseGenerator.name
                            "Holidays Tracker" -> LettersAndCommunicationScreens.Holidays.name
                            //College Layer cards
                            "Academic Calendar" -> CollegeLayerScreens.AcademicCalendar.name
                            "Lab Evaluation" -> CollegeLayerScreens.LabSheet.name
                            "Past Papers" -> CollegeLayerScreens.Pyq.name
                            //Focus And Extras cards
                            "Pomodoro Timer" -> FocusAndExtrasScreens.Pomodoro.name
                            else -> AcademicEssentialsScreens.AcademicEssentialsDashboard.name
                        }
                    )
                }
            )
        }

        // Academic Essentials Screens

        composable(AcademicEssentialsScreens.SimpleCalculator.name) {
            SimpleCalculatorScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(AcademicEssentialsScreens.GPA101.name) {
            GPA101Screen(
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(AcademicEssentialsScreens.CGPACalculator.name) {
            CGPAScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(AcademicEssentialsScreens.SGPACalculator.name) {
            SGPAScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        // Letters and Communications Screen

        composable(LettersAndCommunicationScreens.LetterLab.name) {
            LetterLabHomeScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(LettersAndCommunicationScreens.Holidays.name) {
            HolidaysScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(LettersAndCommunicationScreens.MailGenerator.name) {
            MailGeneratorScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(LettersAndCommunicationScreens.ExcuseGenerator.name) {
            ExcuseGeneratorScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        // College Layer Screens

        composable(CollegeLayerScreens.Pyq.name) {
            PYQScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(CollegeLayerScreens.LabSheet.name) {
            LabSheetScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(CollegeLayerScreens.AcademicCalendar.name) {
            AcademicCalendarScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        // Focus and Extras

        composable(FocusAndExtrasScreens.Pomodoro.name) {
            PomodoroScreen(
                navigateBack = {navController.popBackStack()}
            )
        }

    }
}

