package com.example.alertdialogclonacion2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaPerfil() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        // Encabezado con foto de perfil, nombre, seguidores y seguidos
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Foto de perfil
            Image(
                painter = painterResource(id = R.drawable.pfp),
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Información del usuario
            Column {
                Text(
                    text = "Marmoan05",
                    style = TextStyle(color = Color.White, fontSize = 24.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "5 seguidores · 6 siguiendo",
                    style = TextStyle(color = Color.Gray, fontSize = 14.sp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Título
        Text(
            text = "Artistas escuchados recientemente",
            style = TextStyle(color = Color.White, fontSize = 18.sp),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Lista de artistas
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            listaArtistasRecientes.forEach { artist ->
                TarjetaArtista(
                    artistName = artist.first,
                    followers = artist.second,
                    imageRes = artist.third
                )
            }
        }
    }
}