package com.example.biblivent.ui.theme.screens

import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.biblivent.ui.theme.items.Header
import com.example.biblivent.ui.theme.items.StepBar

@Composable
fun DepotScreen(onBack: () -> Unit, onValidate: () -> Unit) {
    val context = LocalContext.current
    var selectedFileName by remember { mutableStateOf<String?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri: Uri? ->
            uri?.let {
                context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                    val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (cursor.moveToFirst() && nameIndex != -1) {
                        selectedFileName = cursor.getString(nameIndex)
                    }
                }
            }
        }
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Header()

        IconButton(onClick = onBack, modifier = Modifier.padding(start = 8.dp)) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Retour", tint = Color.Blue)
        }

        Spacer(modifier = Modifier.height(12.dp))

        StepBar(currentStep = 1)

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = selectedFileName ?: "Dépôt fichier PDF",
                fontSize = 20.sp,
                color = Color(0xFF1E88E5),
                modifier = Modifier.clickable {
                    launcher.launch(arrayOf("application/pdf"))
                }
            )
        }

        Button(
            onClick = onValidate,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text("Valider")
        }
    }
}