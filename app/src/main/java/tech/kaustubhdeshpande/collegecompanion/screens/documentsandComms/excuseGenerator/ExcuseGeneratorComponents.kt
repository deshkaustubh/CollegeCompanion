package tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.excuseGenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun ExcuseCard(excuse: String, onCopy: () -> Unit, onShuffle: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = excuse,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Medium
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = onCopy) {
                    Icon(Icons.Default.ContentCopy, null)
                    Spacer(Modifier.width(6.dp))
                    Text("Copy")
                }
                TextButton(onClick = onShuffle) {
                    Icon(Icons.Default.Casino, null)
                    Spacer(Modifier.width(6.dp))
                    Text("Another")
                }
            }
        }
    }
}


@Composable
fun ToneSelector(selected: ExcuseTone, onSelect: (ExcuseTone) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        ExcuseTone.entries.forEach { tone ->
            val toneColor = when (tone) {
                ExcuseTone.Honest -> MaterialTheme.colorScheme.primary
                ExcuseTone.Respectful -> MaterialTheme.colorScheme.tertiary
                ExcuseTone.Light -> MaterialTheme.colorScheme.secondary
            }

            FilterChip(
                selected = tone == selected,
                onClick = { onSelect(tone) },
                label = {
                    Text(
                        text = tone.name,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = if (tone == selected) FontWeight.ExtraBold else FontWeight.Normal,
                        color = if (tone == selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                    )
                },
                leadingIcon = {
                    when (tone) {
                        ExcuseTone.Honest -> Icon(
                            Icons.Default.Face,
                            contentDescription = null,
                            tint = toneColor
                        )

                        ExcuseTone.Respectful -> Icon(
                            Icons.Default.ThumbUp,
                            contentDescription = null,
                            tint = toneColor
                        )

                        ExcuseTone.Light -> Icon(
                            Icons.Default.Mood,
                            contentDescription = null,
                            tint = toneColor
                        )
                    }
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = if (tone == selected) toneColor else MaterialTheme.colorScheme.surface,
                    selectedContainerColor = toneColor,
                    labelColor = if (tone == selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}


@Composable
fun TopicSelector(selected: ExcuseTopic, onSelect: (ExcuseTopic) -> Unit) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(ExcuseTopic.entries) { topic ->
            val isSelected = topic == selected
            FilterChip(
                selected = isSelected,
                onClick = { onSelect(topic) },
                label = {
                    Text(
                        text = topic.name,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    labelColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }
}

object ClipboardManagerCompat {
    fun copyText(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("Excuse", text))
    }
}
