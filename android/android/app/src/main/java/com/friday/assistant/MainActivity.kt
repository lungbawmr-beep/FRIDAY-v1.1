package com.friday.assistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class FridayMessage(
    val text: String,
    val owner: Boolean
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
    var input by remember { mutableStateOf("") }

    val messages = remember {
        mutableStateListOf(
            FridayMessage(
                "FRIDAY online. How may I assist you, Owner?",
                false
            )
        )
    }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFF00E5FF),
            background = Color(0xFF05080C),
            surface = Color(0xFF0B1218)
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

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(messages) { message ->
                    MessageBubble(message)
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    modifier = Modifier.weight(1f),
                    placeholder = {
                        Text("Talk to FRIDAY...")
                    },
                    singleLine = true
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (input.isNotBlank()) {
                            val question = input.trim()

                            messages.add(
                                FridayMessage(question, true)
                            )

                            messages.add(
                                FridayMessage(
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

@Composable
fun MessageBubble(message: FridayMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
            if (message.owner)
                Arrangement.End
            else
                Arrangement.Start
    ) {
        Surface(
            color =
                if (message.owner)
                    Color(0xFF17232B)
                else
                    Color(0xFF0D2A34),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(
                text = message.text,
                color = Color.White,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}
