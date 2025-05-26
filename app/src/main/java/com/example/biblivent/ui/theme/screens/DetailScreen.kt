package com.example.biblivent.ui.theme.screens

import androidx.compose.runtime.Composable
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
    StepBar(currentStep = 3, onStepClick = { step ->
        when (step) {
            1 -> onNavigateToDepot()
            2 -> onNavigateToCover()
            3 -> onNavigateToDetails()
            4 -> onNavigateToEditors()
        }
    })
}