package tech.kaustubhdeshpande.collegecompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import tech.kaustubhdeshpande.collegecompanion.ui.theme.Internship1ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Internship1ProjectTheme {
                Text("Shree Ganesh")
            }
        }
    }
}

