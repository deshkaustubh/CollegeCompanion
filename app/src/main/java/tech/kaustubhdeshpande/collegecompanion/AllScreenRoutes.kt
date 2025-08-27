package tech.kaustubhdeshpande.collegecompanion

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import tech.kaustubhdeshpande.collegecompanion.screens.collegeLayer.attendanceCalculator.AttendanceScreen
import tech.kaustubhdeshpande.collegecompanion.screens.collegeLayer.detaieldacademiccalendar.SemesterPlan
import tech.kaustubhdeshpande.collegecompanion.screens.collegeLayer.holidayHacker.LongLeavePlanner
import tech.kaustubhdeshpande.collegecompanion.screens.collegeLayer.labsheet.FillMyCycleScreen
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
    Pyq,
    Club75,
    SemesterPlan,
    LongLeavePlanner
}

enum class FocusAndExtrasScreens {
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
        composable(
            AcademicEssentialsScreens.AcademicEssentialsDashboard.name,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it * 2 },
                    animationSpec = tween(250, easing = FastOutLinearInEasing)
                ) + scaleIn(
                    initialScale = 0.7f,
                    animationSpec = tween(250, easing = FastOutLinearInEasing)
                ) + fadeIn(animationSpec = tween(150))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it * 2 },
                    animationSpec = tween(250, easing = LinearOutSlowInEasing)
                ) + scaleOut(
                    targetScale = 0.7f,
                    animationSpec = tween(250, easing = LinearOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(150))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it * 2 },
                    animationSpec = tween(250, easing = FastOutLinearInEasing)
                ) + scaleIn(
                    initialScale = 0.7f,
                    animationSpec = tween(250, easing = FastOutLinearInEasing)
                ) + fadeIn(animationSpec = tween(150))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it * 2 },
                    animationSpec = tween(250, easing = LinearOutSlowInEasing)
                ) + scaleOut(
                    targetScale = 0.7f,
                    animationSpec = tween(250, easing = LinearOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(150))
            }
        ) {
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
                            "Fill My Cycle" -> CollegeLayerScreens.LabSheet.name
                            "Past Papers" -> CollegeLayerScreens.Pyq.name
                            "75% Club" -> CollegeLayerScreens.Club75.name
                            "Semester Plan" -> CollegeLayerScreens.SemesterPlan.name
                            "Long Leave Planner" -> CollegeLayerScreens.LongLeavePlanner.name
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
                navigateBack = { navController.popBackStack() },
                onCgpaCalculatorClick = { navController.navigate(AcademicEssentialsScreens.CGPACalculator.name) },
                onSgpaCalculatorClick = { navController.navigate(AcademicEssentialsScreens.SGPACalculator.name) }
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
            FillMyCycleScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(CollegeLayerScreens.AcademicCalendar.name) {
            AcademicCalendarScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(CollegeLayerScreens.Club75.name) {
            AttendanceScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(CollegeLayerScreens.SemesterPlan.name) {
            SemesterPlan(
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(CollegeLayerScreens.LongLeavePlanner.name) {
            LongLeavePlanner(
                navigateBack = { navController.popBackStack() }
            )
        }

        // Focus and Extras

        composable(FocusAndExtrasScreens.Pomodoro.name) {
            PomodoroScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

    }
}
