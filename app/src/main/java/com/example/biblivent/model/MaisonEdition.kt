package com.example.biblivent.model

data class MaisonEdition(
    val nom: String = "",
    val description: String = "",
    val type: List<String> = emptyList(),
    val style: List<String> = emptyList(),
    val public: List<String> = emptyList()
)

