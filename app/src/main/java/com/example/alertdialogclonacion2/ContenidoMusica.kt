package com.example.alertdialogclonacion2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContenidoMusica() {
    val items = listOf(
        R.drawable.categoria1 to "Categoría 1",
        R.drawable.categoria2 to "Categoría 2",
        R.drawable.categoria3 to "Categoría 3",
        R.drawable.categoria4 to "Categoría 4",
        R.drawable.categoria5 to "Categoría 5",
        R.drawable.categoria6 to "Categoría 6" ,
        R.drawable.categoria7 to "Categoría 7",
        R.drawable.categoria8 to "Categoría 8",
        R.drawable.categoria9 to "Categoría 9",
        R.drawable.categoria10 to "Categoría 10"
    )

    // Contenedor principal
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 0.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        // Divide los elementos en filas de 2
        items.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(0.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),

                ) {
                // Muestra cada tarjeta en la fila
                rowItems.forEach { (imageRes) ->
                    TarjetaCategoria(imageRes = imageRes, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}