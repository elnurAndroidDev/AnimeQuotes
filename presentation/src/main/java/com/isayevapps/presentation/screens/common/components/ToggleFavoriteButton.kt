package com.isayevapps.presentation.screens.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isayevapps.presentation.R
import com.isayevapps.presentation.theme.Stroke

@Composable
fun ToggleFavoriteButton(modifier: Modifier = Modifier, isFavorite: Boolean = false, onClick: () -> Unit = {}) {
    val icon = if (isFavorite) R.drawable.filled_heart else R.drawable.outlined_heart
    val text = if (isFavorite) "Remove from favorites" else "Add to favorites"
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(2.dp, Stroke),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Icon(
            painter = painterResource(icon),
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(end = 8.dp),
            contentDescription = null
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun ToggleFavoriteButtonPreview() {
    ToggleFavoriteButton()
}