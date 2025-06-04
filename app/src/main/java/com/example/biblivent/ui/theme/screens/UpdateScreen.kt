package com.example.biblivent.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.biblivent.R
import com.example.biblivent.ui.theme.items.Header

@Composable
fun UpdateScreen(
    onNavigateToDetail: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.White)
    ) {
        Header()

        Spacer(modifier = Modifier.height(24.dp))

        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)) {
            Button(
                onClick = { onNavigateToDetail() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Text("Ajouter un livre")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        FeaturedBookCard2()

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Récent",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        RecentBooksRow2()

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun FeaturedBookCard2() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.anges_et_demons),
                contentDescription = "Livre en vedette",
                modifier = Modifier
                    .size(160.dp)
                    .padding(end = 18.dp),
                contentScale = ContentScale.Fit
            )

            Column {
                Text(
                    text = "Ange et démon",  // Titre du livre
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = "Suite à un drame terrible,\n" +
                            "Anèthe découvre qu'elle est est l'héritière du trône d'Angess et,\n" +
                            "que le roi actuel a tué toute sa famille.\n" +
                            "Épaulée par deux inconnus, avec un passé sombre,\n" +
                            "ils vont devoir affronter mille et un périples afin de recupérer le trône. \n" +
                            "Et si Anèthe avait d'autres secrets à découvrir ?",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text(
                    text = "Auteur : Clara Tournay",  // Auteur
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun RecentBooksRow2() {
    val recentBooks = listOf(
        "Le cygne noir" to R.drawable.le_cygne_noir2,
        "Par-delà l'autre rive" to R.drawable.autre_rive,
        "Regarde-moi" to R.drawable.regarde_moi,
        "Ne me jugez pas" to R.drawable.jugez_pas
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(recentBooks) { (title, imageRes) ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 8.dp),
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
