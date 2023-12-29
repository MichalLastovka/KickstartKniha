package eu.example.kickstartkniha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import eu.example.kickstartkniha.ui.theme.KickstartKnihaTheme
import kotlin.concurrent.timer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var seconds by  mutableStateOf(0.00)
        val stopWatchTimer = timer(period = 100) {seconds += 0.1}
        setContent {
            KickstartKnihaTheme {
                // A surface container using the 'background' color from the theme
                RestaurantScreen()
            }
        }
    }
}


@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun DefaultPreview() {
    KickstartKnihaTheme {
        RestaurantScreen()
    }
}