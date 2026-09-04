package com.friday.assistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Message(
    val text: String,
    val fromOwner: Boolean
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FridayApp()
        }
    }
}

@Composable
fun FridayApp() {

    var input by remember {
        mutableStateOf("")
    }

    val messages = remember {
        mutableStateListOf(
            Message(
                "FRIDAY online. How may I assist you, Owner?",
                false
            )
        )
    }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFF00E5FF)
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF05080C))
                .padding(16.dp)
        ) {

            Text(
                text = "FRIDAY",
                color = Color(0xFF00E5FF),
                fontSize = 32.sp
            )

            Text(
                text = "ONLINE • V1.1",
                color = Color.Gray,
                fontSize = 12.sp
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {

                items(messages) { message ->

                    Text(
                        text = message.text,
                        color = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Row {

                OutlinedTextField(
                    value = input,
                    onValueChange = {
                        input = it
                    },
                    modifier = Modifier.weight(1f),
                    placeholder = {
                        Text("Talk to FRIDAY...")
                    }
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Button(
                    onClick = {

                        if (input.isNotBlank()) {

                            messages.add(
                                Message(
                                    input.trim(),
                                    true
                                )
                            )

                            messages.add(
                                Message(
                                    "Message received, Owner.",
                                    false
                                )
                            )

                            input = ""
                        }
                    }
                ) {
                    Text("SEND")
                }
            }
        }
    }
}
