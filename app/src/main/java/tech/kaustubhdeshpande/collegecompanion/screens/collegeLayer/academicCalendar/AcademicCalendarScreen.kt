package tech.kaustubhdeshpande.collegecompanion.screens.collegeLayer.academicCalendar

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import android.os.SystemClock
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.createBitmap
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.analytics.FirebaseAnalytics
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.buffer
import okio.sink
import java.io.File
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcademicCalendarScreen(
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
            putString(FirebaseAnalytics.Param.SCREEN_NAME, "Academic Calendar")
        })
    }

    DisposableEffect(Unit) {
        onDispose {
            val exitTime = SystemClock.elapsedRealtime()
            val durationMs = exitTime - enterTime
            val durationSec = durationMs / 1000
            firebaseAnalytics.logEvent("screen_time_spent", android.os.Bundle().apply {
                putString("screen_name", "Academic Calendar")
                putLong("duration_sec", durationSec)
            })
        }
    }
    // --- End Analytics Tracking ---

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Academic Calendar",
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
                            contentDescription = "Menu",
                            tint = Color.Black,
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        var pdfFile by remember { mutableStateOf<File?>(null) }
        var isLoading by remember { mutableStateOf(true) }
        val url = "https://drive.google.com/uc?export=download&id=190X7eOx6bs-TQkNV3LZ7ktityHx7STB5"
        val context = LocalContext.current

        LaunchedEffect(url) {
            isLoading = true
            downloadPdfToCache(context, url) { file ->
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
                    CircularProgressIndicator()
                }

                pdfFile != null -> {
                    MultiPagePdfViewer(pdfFile!!)
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
                                text = "Failed to load Academic Calendar",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.Red
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Please check your internet connection and try again, Internet connection is required to load it for the first time.",
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


@Composable
private fun MultiPagePdfViewer(
    pdfFile: File,
    modifier: Modifier = Modifier
) {
    val pageCount by remember(pdfFile) { mutableIntStateOf(getPdfPageCount(pdfFile)) }
    val pagerState = rememberPagerState(pageCount = { pageCount })

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize()
    ) { page ->
        var bitmap by remember(page) { mutableStateOf<Bitmap?>(null) }
        var loading by remember { mutableStateOf(true) }

        LaunchedEffect(page) {
            loading = true
            bitmap = renderPdfPage(pdfFile, page)
            loading = false
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            bitmap?.let {
                ZoomableImage(it)
            } ?: if (loading) {
                CircularProgressIndicator()
            } else {
                LinearProgressIndicator()
            }
        }
    }
}

@Composable
private fun ZoomableImage(bitmap: Bitmap) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(1f, 5f)
                    offsetX += pan.x
                    offsetY += pan.y
                }
            }
    ) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                )
                .fillMaxSize()
        )
    }
}
private fun getPdfPageCount(file: File): Int {
    return try {
        val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        val renderer = PdfRenderer(fileDescriptor)
        val count = renderer.pageCount
        renderer.close()
        fileDescriptor.close()
        count
    } catch (e: Exception) {
        0 // If file is not a valid PDF or something goes wrong
    }
}

private fun renderPdfPage(file: File, pageIndex: Int): Bitmap? {
    return try {
        val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        val renderer = PdfRenderer(fileDescriptor)
        val page = renderer.openPage(pageIndex)
        val width = page.width
        val height = page.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        page.close()
        renderer.close()
        fileDescriptor.close()
        bitmap
    } catch (e: Exception) {
        null
    }
}

private fun downloadPdfToCache(context: Context, url: String, onComplete: (File?) -> Unit) {
    val file = File(context.cacheDir, "academic_calendar.pdf")

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
                sink.writeAll(response.body!!.source())
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