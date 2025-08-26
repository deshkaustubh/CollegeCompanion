package tech.kaustubhdeshpande.collegecompanion.screens.collegeLayer.detaieldacademiccalendar

import android.content.Context
import android.os.SystemClock
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.analytics.FirebaseAnalytics
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.buffer
import okio.sink
import java.io.File
import java.io.IOException
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedAcademicCalendarScreen(
    navigateBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color(0xFF0a3579)
    val navigationBarColor = Color(0xFFe5f2fb)

    SideEffect {
        systemUiController.setStatusBarColor(color = statusBarColor)
        systemUiController.setNavigationBarColor(color = navigationBarColor)
    }

    // --- Firebase Analytics Tracking ---
    val context = LocalContext.current
    val firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    val enterTime = SystemClock.elapsedRealtime()

    LaunchedEffect(Unit) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, android.os.Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, "Detailed Academic Calendar")
        })
    }

    DisposableEffect(Unit) {
        onDispose {
            val exitTime = SystemClock.elapsedRealtime()
            val durationMs = exitTime - enterTime
            val durationSec = durationMs / 1000
            firebaseAnalytics.logEvent("screen_time_spent", android.os.Bundle().apply {
                putString("screen_name", "Detailed Academic Calendar")
                putLong("duration_sec", durationSec)
            })
        }
    }
    // --- End Analytics Tracking ---

    // Download progress state
    var downloadedBytes by remember { mutableStateOf(0L) }
    var totalBytes by remember { mutableStateOf(0L) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Detailed Academic Calendar",
                        style = MaterialTheme.typography.headlineLarge,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navigateBack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black,
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        var pdfFile by remember { mutableStateOf<File?>(null) }
        var isLoading by remember { mutableStateOf(true) }
        val url = "https://drive.google.com/uc?export=download&id=12f92H9moJRjPNxGarj_n7IUro63h_Rdg"
        val context = LocalContext.current

        LaunchedEffect(url) {
            isLoading = true
            downloadPdfToCacheWithProgress(context, url, "detailed_academic_calendar.pdf",
                onProgress = { downloaded, total ->
                    downloadedBytes = downloaded
                    totalBytes = total
                }
            ) { file ->
                pdfFile = file
                isLoading = false
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when {
                isLoading -> {
                    DownloadProgressBar(downloadedBytes, totalBytes)
                }
                pdfFile != null -> {
                    PdfRendererPager(pdfFile!!)
                }
                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Filled.ErrorOutline,
                                contentDescription = "Error",
                                tint = Color.Red,
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Failed to load Detailed Academic Calendar",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.Red
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Please check your internet connection and try again. Internet connection is required to load it for the first time.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

// Helper function for downloading with a custom filename and progress
private fun downloadPdfToCacheWithProgress(
    context: Context,
    url: String,
    fileName: String,
    onProgress: (Long, Long) -> Unit,
    onComplete: (File?) -> Unit
) {
    val file = File(context.cacheDir, fileName)

    if (file.exists() && file.length() > 0) {
        onComplete(file)
        return
    }

    val client = OkHttpClient()
    val request = Request.Builder().url(url).build()

    client.newCall(request).enqueue(object : okhttp3.Callback {
        override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
            if (!response.isSuccessful || response.body == null) {
                onComplete(null)
                return
            }
            try {
                val sink = file.sink().buffer()
                val totalBytes = response.body!!.contentLength()
                val source = response.body!!.source()
                var downloadedBytes = 0L
                val buffer = ByteArray(8 * 1024)
                var read: Int
                while (source.read(buffer).also { read = it } != -1) {
                    sink.write(buffer, 0, read)
                    downloadedBytes += read
                    onProgress(downloadedBytes, totalBytes)
                }
                sink.close()
                onComplete(file)
            } catch (e: IOException) {
                onComplete(null)
            }
        }

        override fun onFailure(call: okhttp3.Call, e: IOException) {
            onComplete(null)
        }
    })
}

@Composable
fun DownloadProgressBar(
    downloaded: Long,
    total: Long
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LinearProgressIndicator(
            progress = if (total > 0) downloaded.toFloat() / total else 0f,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(6.dp)
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = if (total > 0) {
                "Downloaded ${formatFileSize(downloaded)} of ${formatFileSize(total)}"
            } else {
                "Downloaded ${formatFileSize(downloaded)}"
            },
            style = MaterialTheme.typography.bodySmall
        )
    }
}

fun formatFileSize(bytes: Long): String {
    val kb = 1024
    val mb = kb * 1024
    val gb = mb * 1024
    return when {
        bytes >= gb -> String.format("%.2f GB", bytes / gb.toFloat())
        bytes >= mb -> String.format("%.2f MB", bytes / mb.toFloat())
        bytes >= kb -> String.format("%.1f KB", bytes / kb.toFloat())
        else -> "$bytes bytes"
    }
}

@Composable
fun PdfRendererPager(
    file: File,
    modifier: Modifier = Modifier
) {
    val pageCount = remember(file) { getPageCountFromPdf(file) }
    var currentPage by remember { mutableStateOf(0) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(currentPage, file) {
        isLoading = true
        bitmap = renderPdfPage(file, currentPage)
        isLoading = false
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            bitmap?.let { bmp ->
                ZoomableBitmapImage(bmp, Modifier.fillMaxSize())
            }
        }

        // Simple navigation controls
        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                enabled = currentPage > 0,
                onClick = { currentPage-- }
            ) { Text("Prev") }
            Spacer(Modifier.width(16.dp))
            Text("Page ${currentPage + 1} / $pageCount")
            Spacer(Modifier.width(16.dp))
            Button(
                enabled = currentPage < pageCount - 1,
                onClick = { currentPage++ }
            ) { Text("Next") }
        }
    }
}

fun getPageCountFromPdf(file: File): Int {
    val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
    return try {
        val renderer = PdfRenderer(fileDescriptor)
        val count = renderer.pageCount
        renderer.close()
        count
    } catch (e: Exception) {
        1
    } finally {
        fileDescriptor.close()
    }
}

fun renderPdfPage(file: File, pageIndex: Int): Bitmap? {
    val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
    return try {
        val renderer = PdfRenderer(fileDescriptor)
        val page = renderer.openPage(pageIndex)
        val bitmap = Bitmap.createBitmap(
            page.width, page.height, Bitmap.Config.ARGB_8888
        )
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        page.close()
        renderer.close()
        bitmap
    } catch (e: Exception) {
        null
    } finally {
        fileDescriptor.close()
    }
}

@Composable
fun ZoomableBitmapImage(bitmap: Bitmap, modifier: Modifier = Modifier) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = null,
        modifier = modifier
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(1f, 5f)
                    offsetX += pan.x
                    offsetY += pan.y
                }
            }
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                translationX = offsetX,
                translationY = offsetY
            )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DetailedAcademicCalendarScreen_Preview() {
    MaterialTheme {
        Surface(modifier = Modifier) {
            Column {
                TopAppBar(
                    title = { Text("Detailed Academic Calendar") },
                    navigationIcon = {},
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF0a3579),
                        titleContentColor = Color.White
                    )
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    DownloadProgressBar(1_048_576, 4_194_304)
                }
            }
        }
    }
}