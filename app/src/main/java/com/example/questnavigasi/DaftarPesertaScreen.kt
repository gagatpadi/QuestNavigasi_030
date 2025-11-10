package com.example.questnavigasi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController

// Definisikan warna di sini atau import dari MainActivity
val LightPurpleBackground = Color(0xFFF3E5F5)
val DarkPurpleText = Color(0xFF6A1B9A)
val LightPurpleCard = Color(0xFFFFFFFF) // Kartunya terlihat putih

// Data dummy untuk contoh
val dummyPesertaList = listOf(
    Peserta(1, "Asroni Sukiman", "Laki-Laki", "Cerai", "Sleman"),
    Peserta(2, "Aprilia Kurnianti", "Perempuan", "Lajang", "Bantul"),
    Peserta(3, "Haris Setyawan", "Laki-Laki", "Kawin", "Jogja")
)

@Composable
fun DaftarPesertaScreen(
    navController: NavController, // Tambahkan NavController
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LightPurpleBackground)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Judul
        Text(
            text = "List Daftar Peserta",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = DarkPurpleText,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        // 2. Daftar Peserta
        LazyColumn(
            modifier = Modifier.weight(1f) // Agar daftar mengisi ruang
        ) {
            items(dummyPesertaList) { peserta ->
                PesertaCard(peserta = peserta)
            }
        }

        // 3. Tombol Bawah
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(
                onClick = {
                    // Kembali ke layar Welcome
                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Beranda", color = DarkPurpleText)
            }

            Spacer(modifier = Modifier.width(16.dp))

            OutlinedButton(
                onClick = { /* TODO: Aksi ke Formulir */ },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Formulir", color = DarkPurpleText)
            }
        }
    }
}

@Composable
fun PesertaCard(peserta: Peserta) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = LightPurpleCard)
    ) {
        // ConstraintLayout bagus untuk menata 2x2 seperti ini
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val (labelNama, valueNama, labelKelamin, valueKelamin,
                labelStatus, valueStatus, labelAlamat, valueAlamat) = createRefs()

            val barrier = createBottomBarrier(valueNama, valueKelamin) // Penanda batas bawah

            // Baris 1 - Kiri: Nama
            Text( "NAMA LENGKAP", fontSize = 12.sp, color = Color.Gray,
                modifier = Modifier.constrainAs(labelNama) { top.linkTo(parent.top); start.linkTo(parent.start) }
            )
            Text( peserta.nama, fontSize = 16.sp, color = DarkPurpleText, fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(valueNama) { top.linkTo(labelNama.bottom, 4.dp); start.linkTo(labelNama.start) }
            )

            // Baris 1 - Kanan: Jenis Kelamin
            Text( "JENIS KELAMIN", fontSize = 12.sp, color = Color.Gray,
                modifier = Modifier.constrainAs(labelKelamin) { top.linkTo(labelNama.top); start.linkTo(parent.start, 180.dp) } // Sesuaikan jarak
            )
            Text( peserta.jenisKelamin, fontSize = 16.sp, color = DarkPurpleText, fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(valueKelamin) { top.linkTo(labelKelamin.bottom, 4.dp); start.linkTo(labelKelamin.start) }
            )

            // Baris 2 - Kiri: Status
            Text( "STATUS PERKAWINAN", fontSize = 12.sp, color = Color.Gray,
                modifier = Modifier.constrainAs(labelStatus) { top.linkTo(barrier, 16.dp); start.linkTo(labelNama.start) }
            )
            Text( peserta.statusPerkawinan, fontSize = 16.sp, color = DarkPurpleText, fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(valueStatus) { top.linkTo(labelStatus.bottom, 4.dp); start.linkTo(labelStatus.start) }
            )

            // Baris 2 - Kanan: Alamat
            Text( "ALAMAT", fontSize = 12.sp, color = Color.Gray,
                modifier = Modifier.constrainAs(labelAlamat) { top.linkTo(labelStatus.top); start.linkTo(labelKelamin.start) }
            )
            Text( peserta.alamat, fontSize = 16.sp, color = DarkPurpleText, fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(valueAlamat) { top.linkTo(labelAlamat.bottom, 4.dp); start.linkTo(labelAlamat.start) }
            )
        }
    }
}