package com.example.alertdialogclonacion2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PantallaInicio(onProfileClick: () -> Unit) {
    var selectedButton by remember { mutableStateOf("Todos") }
    // Mantener las playlists
    val playlistsRecientes = remember { (listaPlaylistHorizontales + listaPlaylists).shuffled().take(6) }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF121212))) {

        // Fila con la imagen de perfil y los botones
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Imagen de perfil
            Image(
                painter = painterResource(id = R.drawable.pfp),
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .clickable { onProfileClick() }
            )

            // Botones de selección
            Boton(
                text = "Todos",
                isSelected = selectedButton == "Todos",
                onClick = { selectedButton = "Todos" }
            )
            Boton(
                text = "Música",
                isSelected = selectedButton == "Música",
                onClick = { selectedButton = "Música" }
            )
            Boton(
                text = "Pódcasts",
                isSelected = selectedButton == "Pódcasts",
                onClick = { selectedButton = "Pódcasts" }
            )
        }

        // Contenido Principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                // Playlists en Dos Columnas
                listaPlaylists.chunked(2).forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        rowItems.forEach { playlist ->
                            TarjetaPequeñaPlaylist(playlistName = playlist.first, imageRes = playlist.second)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Secciones con Playlists
                SeccionHorizontal(
                    title = "Hecho para Marmoan05",
                    playlists = listOf(
                        "Mix diario 1" to R.drawable.mix1,
                        "Mix diario 2" to R.drawable.mix2,
                        "Mix diario 3" to R.drawable.mix3,
                        "Radar de novedades" to R.drawable.radardenovedades
                    ),
                    imageSize = 165.dp
                )
                // Sección con 6 Playlists Combinadas
                SeccionHorizontal(
                    title = "Recientes",
                    playlists = playlistsRecientes,
                    imageSize = 135.dp
                )

                SeccionHorizontal(
                    title = "Mix Diario",
                    playlists = listOf(
                        "Mix diario 1" to R.drawable.mix1,
                        "Mix diario 2" to R.drawable.mix2,
                        "Mix diario 3" to R.drawable.mix3
                    ),
                    imageSize = 165.dp
                )

                SeccionHorizontal(
                    title = "Emisoras Recomendadas",
                    playlists = listOf(
                        "Radio1" to R.drawable.radio1,
                        "Radio2" to R.drawable.radio2,
                        "Radio3" to R.drawable.radio3
                    ),
                    imageSize = 165.dp
                )

            }
        }
    }
}