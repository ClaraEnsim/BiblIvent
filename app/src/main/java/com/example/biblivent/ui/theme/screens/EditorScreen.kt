package com.example.biblivent.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.biblivent.R
import com.example.biblivent.ui.theme.items.Header
import com.example.biblivent.ui.theme.items.StepBar
import com.example.biblivent.model.MaisonEdition
import com.example.biblivent.model.MaisonEditionRepository
import com.example.biblivent.model.viewmodel.BookViewModel

@Composable
fun EditorScreen(
    onBack: () -> Unit,
    onValidate: () -> Unit,
    onNavigateToDepot: () -> Unit,
    onNavigateToCover: () -> Unit,
    onNavigateToDetails: () -> Unit,
    onNavigateToEditors: () -> Unit,
    bookViewModel: BookViewModel
) {
    // Récupération des sélections depuis le ViewModel
    val selectedTypes = bookViewModel.selectedTypes.collectAsState()
    val selectedStyles = bookViewModel.selectedStyles.collectAsState()
    val selectedPublics = bookViewModel.selectedPublics.collectAsState()

    var maisonsEdition by remember { mutableStateOf<List<MaisonEdition>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // 🔁 Récupération des données depuis Firebase selon les sélections dans le ViewModel
    LaunchedEffect(selectedTypes.value, selectedStyles.value, selectedPublics.value) {
        isLoading = true
        maisonsEdition = MaisonEditionRepository.fetchMaisonsEdition(
            selectedTypes.value,
            selectedStyles.value,
            selectedPublics.value
        )
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 88.dp) // espace pour le bouton
            .verticalScroll(rememberScrollState())
    ) {
        Header()

        IconButton(onClick = onBack) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "Retour",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        StepBar(currentStep = 4, onStepClick = { step ->
            when (step) {
                1 -> onNavigateToDepot()
                2 -> onNavigateToCover()
                3 -> onNavigateToDetails()
                4 -> onNavigateToEditors()
            }
        })

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        } else {
            if (maisonsEdition.isNotEmpty()) {
                maisonsEdition.forEach { maison ->
                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                        Text(
                            text = maison.nom,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = maison.description)
                        Text(
                            text = "Genres : ${maison.type.joinToString(", ")}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "Styles : ${maison.style.joinToString(", ")}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "Public : ${maison.public.joinToString(", ")}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Divider(modifier = Modifier.padding(vertical = 8.dp))
                    }
                }
            } else {
                Text(
                    text = "Aucune maison d'édition ne correspond à vos critères.",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge
                )
                Image(
                    painter = painterResource(R.drawable.no_match),
                    contentDescription = "Aucune correspondance",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onValidate,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Déposer le livre",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
