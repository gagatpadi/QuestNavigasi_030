package com.example.questnavigasi // Pastikan package name ini sesuai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.questnavigasi.ui.theme.QuestNavigasiTheme // Pastikan ini di-import

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuestNavigasiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Kita panggil layar WelcomeScreen di sini
                    WelcomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Definisikan warna di sini agar mudah
val LightPurple = Color(0xFFF3E5F5)
val DarkPurple = Color(0xFF6A1B9A)

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LightPurple) // Warna background
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround // Memberi jarak antar elemen
    ) {
        // 1. Teks Selamat Datang
        Text(
            text = "Selamat Datang",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkPurple, // Sesuaikan warnanya
            modifier = Modifier.padding(top = 32.dp)
        )

        // 2. Logo
        // PENTING: Anda harus menambahkan gambar logo Anda ke res/drawable
        // Ganti 'R.drawable.card_lst_logo' dengan nama file logo Anda

        Image(
            painter = painterResource(id = R.drawable.gambarlogo),
            contentDescription = "Logo CARD-LST",
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .aspectRatio(1f)
        )



        // 3. Nama dan NIM/Nomor
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Pascal Pahlevi Pasha",
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = "20250140001",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        // 4. Tombol Masuk
        Button(
            onClick = { /* TODO: Nanti kita tambahkan navigasi di sini */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .height(50.dp),
            shape = RoundedCornerShape(16.dp), // Membuat tombol rounded
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkPurple // Warna tombol
            )
        ) {
            Text(
                text = "Masuk",
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    QuestNavigasiTheme {
        WelcomeScreen()
    }
}