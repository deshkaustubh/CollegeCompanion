package tech.kaustubhdeshpande.collegecompanion

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import tech.kaustubhdeshpande.collegecompanion.ui.theme.Internship1ProjectTheme
import com.microsoft.clarity.Clarity
import com.microsoft.clarity.ClarityConfig
import com.microsoft.clarity.models.LogLevel

class MainActivity : ComponentActivity() {

    private lateinit var analytics: FirebaseAnalytics

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // firebase analytics
        analytics = Firebase.analytics

        // microsoft clarity
        val config = ClarityConfig(
            projectId = "szgslc6nt3",
            logLevel = LogLevel.None // Note: Use "LogLevel.Verbose" value while testing to debug initialization issues.
        )
        Clarity.initialize(applicationContext, config)

        // Play-compliant edge-to-edge: transparent bars, readable icons
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            )
        )

        setContent {
            Internship1ProjectTheme {
                AllScreensGraph()
            }
        }
    }
}