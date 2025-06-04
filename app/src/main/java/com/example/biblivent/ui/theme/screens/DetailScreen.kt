package com.example.biblivent.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.runtime.remember
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.biblivent.ui.theme.items.Header
import com.example.biblivent.ui.theme.items.StepBar

@Composable
fun DetailScreen(
    onBack: () -> Unit,
    onValidate: () -> Unit,
    onNavigateToDepot: () -> Unit,
    onNavigateToCover: () -> Unit,
    onNavigateToDetails: () -> Unit,
    onNavigateToEditors: () -> Unit
) {
    val title = remember { mutableStateOf(TextFieldValue()) }
    val subtitle = remember { mutableStateOf(TextFieldValue()) }
    val author = remember { mutableStateOf(TextFieldValue()) }
    val themes = remember { mutableStateOf(TextFieldValue()) }
    val summary = remember { mutableStateOf(TextFieldValue()) }

    val selectedTypes = remember { mutableStateListOf<String>() }
    val selectedStyles = remember { mutableStateListOf<String>() }
    val selectedPublics = remember { mutableStateListOf<String>() }

    val isFormValid by remember {
        derivedStateOf {
            title.value.text.isNotBlank() &&
                    author.value.text.isNotBlank() &&
                    themes.value.text.isNotBlank() &&
                    summary.value.text.isNotBlank() &&
                    selectedTypes.isNotEmpty() &&
                    selectedStyles.isNotEmpty() &&
                    selectedPublics.isNotEmpty()
        }
    }

    fun toggleSelection(option: String, list: MutableList<String>) {
        if (list.contains(option)) list.remove(option) else list.add(option)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 88.dp) // espace pour le bouton
            .verticalScroll(rememberScrollState())
    ) {

        Header()

        IconButton(onClick = onBack) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Retour", tint = MaterialTheme.colorScheme.primary)
        }

        StepBar(currentStep = 3, onStepClick = { step ->
            when (step) {
                1 -> onNavigateToDepot()
                2 -> onNavigateToCover()
                3 -> onNavigateToDetails()
                4 -> onNavigateToEditors()
            }
        })

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title.value,
            onValueChange = { title.value = it },
            label = { Text("Titre *") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = subtitle.value,
            onValueChange = { subtitle.value = it },
            label = { Text("Sous-titre (facultatif)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = author.value,
            onValueChange = { author.value = it },
            label = { Text("Auteur *") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Type *", fontSize = 18.sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Roman", "BD", "Poésie").forEach {
                SelectableButton(it, selectedTypes.contains(it)) {
                    toggleSelection(it, selectedTypes)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text("Style *", fontSize = 18.sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Fantastique", "Historique", "Thriller").forEach {
                SelectableButton(it, selectedStyles.contains(it)) {
                    toggleSelection(it, selectedStyles)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text("Public *", fontSize = 18.sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Enfants", "Ados", "Adultes").forEach {
                SelectableButton(it, selectedPublics.contains(it)) {
                    toggleSelection(it, selectedPublics)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = themes.value,
            onValueChange = { themes.value = it },
            label = { Text("Thèmes abordés *") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = summary.value,
            onValueChange = { summary.value = it },
            label = { Text("Résumé *") },
            modifier = Modifier.fillMaxWidth().height(120.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onValidate,
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Valider", fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Composable
fun SelectableButton(text: String, selected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (selected) MaterialTheme.colorScheme.primary else Color.LightGray
    val textColor = if (selected) MaterialTheme.colorScheme.onPrimary else Color.Black

    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = textColor)
    }
}
