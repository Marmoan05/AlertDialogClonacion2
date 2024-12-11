package com.example.alertdialogclonacion2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TarjetaGrandePlaylist(playlistName: String, imageRes: Int, imageSize: Dp) {
    Column(
        modifier = Modifier
            .width(imageSize)
            .padding(end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Imagen de playlist",
            modifier = Modifier
                .size(imageSize)
                .clip(RoundedCornerShape(10))
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = playlistName,
            style = TextStyle(color = Color.White, fontSize = 14.sp),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}