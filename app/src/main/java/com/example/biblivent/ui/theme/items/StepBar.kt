package com.example.biblivent.ui.theme.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StepBar(
    currentStep: Int,
    onStepClick: (Int) -> Unit
) {
    val steps = listOf(
        "Dépôt livre\n ",
        "Réalisation\ncouvertures",
        "Renseignement\ndes détails",
        "Éditeurs\nintéressés"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        steps.forEachIndexed { index, label ->
            val isCompleted = index + 1 <= currentStep

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable(enabled = isCompleted) { onStepClick(index + 1) }
                    .padding(horizontal = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            color = if (isCompleted) MaterialTheme.colorScheme.primary else Color.Gray,
                            shape = CircleShape
                        )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = label,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    lineHeight = 14.sp
                )
            }
        }
    }
}
