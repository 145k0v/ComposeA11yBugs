package com.example.composea11ybugs.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composea11ybugs.R

@Composable
fun Header(text: String, onBack: (() -> Unit)?) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colors.primary.copy(alpha = 0.1f))
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .heightIn(min = 48.dp)
    ) {
        if (onBack != null) {
            Image(
                painter = painterResource(id = R.drawable.baseline_chevron_left_24),
                contentDescription = "Go back",
                modifier = Modifier.clickable(onClick = onBack).padding(vertical = 8.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
        Text(text = text)
    }
}