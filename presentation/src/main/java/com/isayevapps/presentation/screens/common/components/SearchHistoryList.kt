package com.isayevapps.presentation.screens.common.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.isayevapps.presentation.R
import com.isayevapps.presentation.theme.AnimeQuotesTheme

@Composable
fun SearchHistoryList(
    modifier: Modifier = Modifier,
    historyList: List<String>,
    onClick: (String) -> Unit = {},
    onDelete: (String) -> Unit = {}
) {
    LazyColumn(modifier = modifier) {
        items(historyList.size) { item ->
            SearchHistoryItem(
                historyItem = historyList[item],
                onClick = onClick,
                onDelete = onDelete,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SearchHistoryItem(
    modifier: Modifier = Modifier,
    historyItem: String,
    onClick: (String) -> Unit = {},
    onDelete: (String) -> Unit = {}
) {
    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onClick(historyItem) },
                    onLongPress = { onDelete(historyItem) }
                )
            },
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.history),
                modifier = Modifier.size(16.dp),
                contentDescription = "Delete"
            )
            Text(text = historyItem, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SearchHistoryItemPreview() {
    AnimeQuotesTheme {
        SearchHistoryItem(
            historyItem = "Naruto",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SearchHistoryListPreview() {
    AnimeQuotesTheme {
        SearchHistoryList(
            historyList = listOf("Naruto", "Sasuke", "Sakura"),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

