package com.example.biblivent.ui.theme.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.biblivent.R

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(vertical = 32.dp, horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo de l'application",
            modifier = Modifier.size(140.dp)
        )

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = "Bibl'Invent",
            color = Color.White,
            fontSize = 34.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}
