package com.example.biblivent.ui.theme.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.biblivent.ui.theme.items.Header
import java.io.InputStream
import android.graphics.BitmapFactory
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.biblivent.ui.theme.items.StepBar

@Composable
fun CoverScreen(
    onBack: () -> Unit,
    onValidate: () -> Unit,
    onNavigateToDepot: () -> Unit,
    onNavigateToCover: () -> Unit,
    onNavigateToDetails: () -> Unit,
    onNavigateToEditors: () -> Unit
) {
    val context = LocalContext.current
    var imageUri1 by remember { mutableStateOf<Uri?>(null) }
    var imageUri2 by remember { mutableStateOf<Uri?>(null) }

    val launcher1 = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        if (uri != null) imageUri1 = uri
    }

    val launcher2 = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        if (uri != null) imageUri2 = uri
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 88.dp) // espace pour le bouton
                .verticalScroll(rememberScrollState())
        ) {
            Header()

            IconButton(onClick = onBack, modifier = Modifier.padding(start = 10.dp)) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour", tint = MaterialTheme.colorScheme.primary)
            }

            Spacer(modifier = Modifier.height(12.dp))

            StepBar(currentStep = 2, onStepClick = { step ->
                when (step) {
                    1 -> onNavigateToDepot()
                    2 -> onNavigateToCover()
                    3 -> onNavigateToDetails()
                    4 -> onNavigateToEditors()
                }
            })

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageBox(
                    uri = imageUri1,
                    onClick = { launcher1.launch(arrayOf("image/png")) }
                )

                ImageBox(
                    uri = imageUri2,
                    onClick = { launcher2.launch(arrayOf("image/png")) }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }

        // Bouton fixé en bas
        Button(
            onClick = onValidate,
            modifier = Modifier
                .align(Alignment.BottomCenter)
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

@Composable
fun ImageBox(uri: Uri?, onClick: () -> Unit) {
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }

    LaunchedEffect(uri) {
        uri?.let {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            inputStream?.use {
                bitmap = BitmapFactory.decodeStream(it)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f / 3f)
            .border(2.dp, Color.Gray)
            .clickable(onClick = onClick)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        if (bitmap != null) {
            // Image limitée à l'intérieur de la box
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            ) {
                Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = "Image sélectionnée",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        } else {
            Text(
                text = "Déposer une image PNG",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp
            )
        }
    }
}

