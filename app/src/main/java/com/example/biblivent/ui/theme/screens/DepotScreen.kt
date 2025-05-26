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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.biblivent.ui.theme.items.Header
import com.example.biblivent.ui.theme.items.StepBar

@Composable
fun DepotScreen(
    onBack: () -> Unit,
    onValidate: () -> Unit,
    onNavigateToDepot: () -> Unit,
    onNavigateToCover: () -> Unit,
    onNavigateToDetails: () -> Unit,
    onNavigateToEditors: () -> Unit
) {
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

        IconButton(onClick = onBack, modifier = Modifier.padding(start = 10.dp)) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Retour", tint = MaterialTheme.colorScheme.primary)
        }

        Spacer(modifier = Modifier.height(12.dp))

        StepBar(currentStep = 1, onStepClick = { step ->
            when (step) {
                1 -> onNavigateToDepot()
                2 -> onNavigateToCover()
                3 -> onNavigateToDetails()
                4 -> onNavigateToEditors()
            }
        })

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = selectedFileName ?: "Dépôt fichier PDF",
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.primary,
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
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Valider",
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
