package com.example.biblivent.ui.theme.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StepBar(currentStep: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in 1..4) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(
                        color = if (i <= currentStep) Color(0xFF1E88E5) else Color.Gray,
                        shape = CircleShape
                    )
            )
        }
    }
}
