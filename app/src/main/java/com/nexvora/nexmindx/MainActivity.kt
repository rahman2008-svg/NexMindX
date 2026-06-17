package com.nexvora.nexmindx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    Greeting("NexMind X AI is Ready 🚀")
                }
            }
        }
    }
}

@Composable
fun Greeting(text: String) {
    Text(text = text)
}
