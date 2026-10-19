package com.example.kampusku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// ===============================
// WARNA APLIKASI
// ===============================

private val BrandPurple = Color(0xFF6750A4)
private val BrandLavender = Color(0xFFEADDFF)
private val PageBackground = Color(0xFFF7F5FC)
private val Ink = Color(0xFF211A31)
private val MutedInk = Color(0xFF625D70)

private val SoftGreen = Color(0xFFE0F2E8)
private val SoftBlue = Color(0xFFE3EDFF)
private val SoftOrange = Color(0xFFFFEAD5)
private val SoftPink = Color(0xFFFCE4EC)

// Warna tambahan mata kuliah
private val TealAccent = Color(0xFF00796B)
private val TealContainer = Color(0xFFE0F2F1)

private val MagentaAccent = Color(0xFFAD1457)
private val MagentaContainer = Color(0xFFFCE4EC)

// ===============================
// MAIN ACTIVITY
// ===============================

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KampusKuTheme {
                AdaptiveDashboard()
            }
        }
    }
}

// ===============================
// THEME
// ===============================

@Composable
private fun KampusKuTheme(
    content: @Composable () -> Unit
) {
    val colors = lightColorScheme(
        primary = BrandPurple,
        onPrimary = Color.White,
        primaryContainer = BrandLavender,
        onPrimaryContainer = Ink,
        secondary = Color(0xFF625B71),
        background = PageBackground,
        surface = Color.White,
        onSurface = Ink,
        onSurfaceVariant = MutedInk,
        tertiary = Color(0xFF39745B)
    )

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}

// ===============================
// ADAPTIVE DASHBOARD
// ===============================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AdaptiveDashboard() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "KampusKu",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Dashboard Perkuliahan",
                            style = MaterialTheme.typography.labelMedium,
                            color = MutedInk
                        )
                    }
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.School,
                        contentDescription = "Logo KampusKu",
                        tint = BrandPurple,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .size(28.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Ink
                )
            )
        },
        containerColor = PageBackground
    ) { innerPadding ->

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val currentWidth = maxWidth

            val widthClass = when {
                currentWidth < 600.dp ->
                    "COMPACT · PONSEL"

                currentWidth < 840.dp ->
                    "MEDIUM · LAYAR SEDANG"

                currentWidth < 1200.dp ->
                    "EXPANDED · TABLET/LAPTOP"

                else ->
                    "LARGE · LAYAR BESAR"
            }

            val showSideRail = currentWidth >= 840.dp

            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                if (showSideRail) {
                    DashboardSideRail()
                }

                DashboardContent(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    widthClassLabel = widthClass
                )
            }
        }
    }
}

// ===============================
// NAVIGASI SAMPING
// ===============================

@Composable
private fun DashboardSideRail() {
    Column(
        modifier = Modifier
            .width(88.dp)
            .fillMaxHeight()
            .background(Color.White)
            .padding(
                top = 24.dp,
                start = 8.dp,
                end = 8.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        SideRailItem(
            Icons.Filled.Dashboard,
            "Beranda",
            selected = true
        )

        SideRailItem(
            Icons.Filled.CalendarMonth,
            "Jadwal",
            selected = false
        )

        SideRailItem(
            Icons.Filled.MenuBook,
            "Materi",
            selected = false
        )

        SideRailItem(
            Icons.Filled.BarChart,
            "Nilai",
            selected = false
        )
    }
}

@Composable
private fun SideRailItem(
    icon: ImageVector,
    label: String,
    selected: Boolean
) {
    val tileColor =
        if (selected) BrandLavender else Color.Transparent

    val iconColor =
        if (selected) BrandPurple else MutedInk

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            color = tileColor,
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.size(
                width = 56.dp,
                height = 40.dp
            )
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = iconColor
                )
            }
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = iconColor
        )
    }
}

// ===============================
// DATA DASHBOARD
// ===============================

private data class MetricData(
    val label: String,
    val value: String,
    val detail: String,
    val icon: ImageVector,
    val accent: Color,
    val container: Color
)

private data class CourseData(
    val title: String,
    val subtitle: String,
    val note: String,
    val progress: Float,
    val icon: ImageVector,
    val accent: Color,
    val container: Color
)

// ===============================
// KONTEN DASHBOARD
// ===============================

@Composable
private fun DashboardContent(
    modifier: Modifier,
    widthClassLabel: String
) {
    val metrics = listOf(
        MetricData(
            "Mata Kuliah",
            "6",
            "Semester berjalan",
            Icons.Filled.MenuBook,
            BrandPurple,
            BrandLavender
        ),
        MetricData(
            "Tugas Selesai",
            "12 / 15",
            "80% tugas terkumpul",
            Icons.Filled.AssignmentTurnedIn,
            Color(0xFF24734A),
            SoftGreen
        ),
        MetricData(
            "Jadwal Hari Ini",
            "3",
            "Kelas terjadwal",
            Icons.Filled.Schedule,
            Color(0xFF245D9B),
            SoftBlue
        )
    )

    // TOTAL 6 MATA KULIAH
    val courses = listOf(
        CourseData(
            title = "Pemrograman Mobile",
            subtitle = "Jetpack Compose dan Kotlin",
            note = "Pertemuan 5 · Layout Adaptif",
            progress = 0.72f,
            icon = Icons.Filled.School,
            accent = BrandPurple,
            container = BrandLavender
        ),
        CourseData(
            title = "Basis Data",
            subtitle = "Normalisasi dan perancangan ERD",
            note = "Tugas dikumpulkan Jumat",
            progress = 0.56f,
            icon = Icons.Filled.Science,
            accent = Color(0xFF24734A),
            container = SoftGreen
        ),
        CourseData(
            title = "Struktur Data",
            subtitle = "Array, list, dan pencarian",
            note = "Latihan mandiri",
            progress = 0.84f,
            icon = Icons.Filled.Code,
            accent = Color(0xFF9A5700),
            container = SoftOrange
        ),
        CourseData(
            title = "Analisis Data",
            subtitle = "Statistik deskriptif",
            note = "Materi minggu ini",
            progress = 0.41f,
            icon = Icons.Filled.BarChart,
            accent = Color(0xFF9A405D),
            container = SoftPink
        ),

        // MATA KULIAH BARU 1
        CourseData(
            title = "Jaringan Komputer",
            subtitle = "Protokol jaringan dan topologi",
            note = "Praktikum minggu depan",
            progress = 0.65f,
            icon = Icons.Filled.Science,
            accent = TealAccent,
            container = TealContainer
        ),

        // MATA KULIAH BARU 2
        CourseData(
            title = "Kecerdasan Buatan",
            subtitle = "Machine learning dan neural network",
            note = "Pelajari materi terbaru",
            progress = 0.35f,
            icon = Icons.Filled.Code,
            accent = MagentaAccent,
            container = MagentaContainer
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Adaptive(
            minSize = 220.dp
        ),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 18.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            WindowClassBanner(widthClassLabel)
        }

        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            WelcomeCard()
        }

        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            SectionHeader(
                title = "Ringkasan Akademik",
                subtitle = "Ringkasan kegiatan perkuliahanmu."
            )
        }

        items(metrics) { metric ->
            MetricCard(metric)
        }

        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            SectionHeader(
                title = "Mata Kuliah",
                subtitle = "6 mata kuliah semester berjalan."
            )
        }

        items(courses) { course ->
            CourseCard(course)
        }

        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            Text(
                text = "Data contoh untuk praktikum · Belum menggunakan database atau API",
                style = MaterialTheme.typography.bodySmall,
                color = MutedInk,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }
    }
}

// ===============================
// BANNER MODE LAYOUT
// ===============================

@Composable
private fun WindowClassBanner(label: String) {
    Surface(
        color = BrandLavender,
        contentColor = Ink,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 14.dp,
                    vertical = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null,
                tint = BrandPurple
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = "Mode layout: $label",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

// ===============================
// KARTU SELAMAT DATANG
// ===============================

@Composable
private fun WelcomeCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = BrandPurple
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Selamat datang, Fauzan!",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "Atur kegiatan kuliahmu dengan lebih terarah. Tampilan ini menyesuaikan ruang layar yang tersedia.",
                    color = Color(0xFFF0E9FF),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Surface(
                shape = CircleShape,
                color = Color(0xFF8A76C5),
                modifier = Modifier.size(58.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.School,
                        contentDescription = "Ilustrasi pendidikan",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}

// ===============================
// JUDUL BAGIAN
// ===============================

@Composable
private fun SectionHeader(
    title: String,
    subtitle: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 4.dp,
                bottom = 1.dp
            )
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Ink
        )

        Spacer(Modifier.height(3.dp))

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MutedInk
        )
    }
}

// ===============================
// KARTU RINGKASAN
// ===============================

@Composable
private fun MetricCard(data: MetricData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = data.container,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.size(42.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = data.icon,
                            contentDescription = null,
                            tint = data.accent
                        )
                    }
                }

                Spacer(Modifier.width(10.dp))

                Text(
                    text = data.label,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MutedInk
                )
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = data.value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Ink
            )

            Spacer(Modifier.height(2.dp))

            Text(
                text = data.detail,
                style = MaterialTheme.typography.bodySmall,
                color = MutedInk
            )
        }
    }
}

// ===============================
// KARTU MATA KULIAH
// ===============================

@Composable
private fun CourseCard(course: CourseData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Surface(
                    color = course.container,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.size(48.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = course.icon,
                            contentDescription = null,
                            tint = course.accent,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = course.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Ink,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = course.subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MutedInk,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Progres belajar",
                    style = MaterialTheme.typography.labelMedium,
                    color = MutedInk
                )

                Spacer(Modifier.weight(1f))

                Text(
                    text = "${(course.progress * 100).toInt()}%",
                    style = MaterialTheme.typography.labelMedium,
                    color = course.accent,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(6.dp))

            LinearProgressIndicator(
                progress = { course.progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(7.dp),
                color = course.accent,
                trackColor = course.container
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = course.note,
                style = MaterialTheme.typography.labelSmall,
                color = MutedInk
            )
        }
    }
}

// ===============================
// PREVIEW PONSEL
// ===============================

@Preview(
    name = "Ponsel · Compact",
    showBackground = true,
    widthDp = 393,
    heightDp = 850
)
@Composable
private fun PhonePreview() {
    KampusKuTheme {
        AdaptiveDashboard()
    }
}

// ===============================
// PREVIEW TABLET
// ===============================

@Preview(
    name = "Tablet · Expanded",
    showBackground = true,
    widthDp = 1000,
    heightDp = 800
)
@Composable
private fun TabletPreview() {
    KampusKuTheme {
        AdaptiveDashboard()
    }
}