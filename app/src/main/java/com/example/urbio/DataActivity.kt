package com.example.urbio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_data.*

class DataActivity : AppCompatActivity() {
    private var Nama = ""
    private var NIM = ""
    private var Jurusan = ""
    private var JenisKelamin = ""
    private var Alamat = ""
    private var TanggalLahir = ""
    private var daftarBulan = arrayOf(
        "Januari",
        "Februari",
        "Maret",
        "April",
        "Mei",
        "Juni",
        "Juli",
        "Agustus",
        "September",
        "Oktober",
        "November",
        "Desember"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        this.setTitle("Data Pendaftaran")

        val intent = intent
        val tanggal = intent.getStringExtra("TANGGAL")
        val bulan = intent.getIntExtra("BULAN", 0)
        val tahun = intent.getStringExtra("TAHUN")

        nama.text = intent.getStringExtra("NAMA")
        nim.text = intent.getStringExtra("NIM")
        jurusan.text = intent.getStringExtra("JURUSAN")
        jenisKelamin.text = intent.getStringExtra("JENIS_KELAMIN")
        alamat.text = intent.getStringExtra("ALAMAT")
        tanggallahir.text = "$tanggal ${daftarBulan.get(bulan - 1)} $tahun"
    }
}