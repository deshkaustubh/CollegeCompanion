package tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.letterGenerator


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

fun copyToClipboard(context: Context, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Generated Letter", text)
    clipboard.setPrimaryClip(clip)
}
