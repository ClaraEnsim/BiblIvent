package com.example.biblivent.model

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.biblivent.model.MaisonEdition

object MaisonEditionRepository {
    private val db = FirebaseFirestore.getInstance()

    suspend fun fetchMaisonsEdition(
        selectedTypes: List<String>,
        selectedStyles: List<String>,
        selectedPublics: List<String>
    ): List<MaisonEdition> {
        val snapshot = db.collection("maisons_edition").get().await()

        val cleanedSelectedTypes = selectedTypes.map { it.trim().lowercase() }
        val cleanedSelectedStyles = selectedStyles.map { it.trim().lowercase() }
        val cleanedSelectedPublics = selectedPublics.map { it.trim().lowercase() }

        return snapshot.documents.mapNotNull { doc ->
            val nom = doc.getString("nom") ?: return@mapNotNull null
            val description = doc.getString("description") ?: return@mapNotNull null
            val type = doc.get("type") as? List<String> ?: return@mapNotNull null
            val style = doc.get("style") as? List<String> ?: return@mapNotNull null
            val public = doc.get("public") as? List<String> ?: return@mapNotNull null

            println("Doc ${doc.id}: type=$type, style=$style, public=$public")

            val cleanedType = type.map { it.trim().lowercase() }
            val cleanedStyle = style.map { it.trim().lowercase() }
            val cleanedPublic = public.map { it.trim().lowercase() }

            val matchesType = cleanedSelectedTypes.isEmpty() || cleanedSelectedTypes.any { it in cleanedType }
            val matchesStyle = cleanedSelectedStyles.isEmpty() || cleanedSelectedStyles.any { it in cleanedStyle }
            val matchesPublic = cleanedSelectedPublics.isEmpty() || cleanedSelectedPublics.any { it in cleanedPublic }

            if (matchesType || matchesStyle || matchesPublic) {
                MaisonEdition(nom, description, type, style, public)
            } else null
        }
    }
}
