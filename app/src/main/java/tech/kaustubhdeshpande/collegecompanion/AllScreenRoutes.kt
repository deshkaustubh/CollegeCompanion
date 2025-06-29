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
import tech.kaustubhdeshpande.collegecompanion.screens.dashboard.DashboardScreen

enum class AcademicEssentialsScreens {
    AcademicEssentialsDashboard,
    SimpleCalculator,
    GPA101,
    CGPACalculator,
    SGPACalculator,
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
                            "CGPA Calculator" -> AcademicEssentialsScreens.CGPACalculator.name
                            "SGPA Calculator" -> AcademicEssentialsScreens.SGPACalculator.name
                            "GPA 1-0-1" -> AcademicEssentialsScreens.GPA101.name
                            "Simple Calculator" -> AcademicEssentialsScreens.SimpleCalculator.name
                            else -> AcademicEssentialsScreens.AcademicEssentialsDashboard.name
                        }
                    )
                }
            )
        }

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
    }
}

