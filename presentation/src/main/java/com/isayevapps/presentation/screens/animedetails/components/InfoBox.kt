package com.isayevapps.presentation.screens.animedetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isayevapps.presentation.theme.InfoBoxColor
import com.isayevapps.presentation.theme.Stroke

@Composable
fun InfoBox(ico: Int, type: String = "", text: String = "", modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(90.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(InfoBoxColor)
            .border(width = 2.dp, color = Stroke, shape = RoundedCornerShape(14.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                painter = painterResource(ico),
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .padding(4.dp)
            )
            Text(text = type, color = Color.Gray, fontSize = 14.sp)
            Text(text = text, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Preview
@Composable
private fun InfoBoxPreview() {

    InfoBox(ico = com.isayevapps.presentation.R.drawable.genre_ico, type = "Type", text = "TV Special")

}