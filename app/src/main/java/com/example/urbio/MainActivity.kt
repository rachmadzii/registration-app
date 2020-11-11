package com.example.urbio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var Nama = ""
    private var NIM = ""
    private var Jurusan = ""
    private var JenisKelamin = ""
    private var Alamat = ""
    private var Tanggal = ""
    private var Bulan = ""
    private var Tahun = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setTitle("Formulir Pendaftaran")
        setContentView(R.layout.activity_main)

        btnKirim.setOnClickListener((this))
        inNama.addTextChangedListener(addData)
        inNIM.addTextChangedListener(addData)
        inJurusan.addTextChangedListener(addData)
        inAlamat.addTextChangedListener(addData)
        inTanggal.addTextChangedListener(addTanggal)
        inBulan.addTextChangedListener(addTanggal)
        inTahun.addTextChangedListener(addTanggal)
    }

    private val addData: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(d1: Editable?) {
            NIM = inNIM.text.toString().trim()
            Nama = inNama.text.toString().trim()
            Jurusan = inJurusan.text.toString().trim()
            Alamat = inAlamat.text.toString().trim()
            radioGender.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener() {
                    radioGroup, i ->
                val radio: RadioButton = findViewById(i)
                JenisKelamin = radio.text.toString()
            })

            enableButtonKirim()
        }
        override fun beforeTextChanged(d1: CharSequence?, d2: Int, d3: Int, d4: Int) {}
        override fun onTextChanged(d1: CharSequence?, d2: Int, d3: Int, d4: Int) {}
    }

    private val addTanggal: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(d1: Editable?) {
            Tanggal = inTanggal.text.toString().trim()
            Bulan = inBulan.text.toString().trim()
            Tahun = inTahun.text.toString().trim()

            if (Tanggal.isNotEmpty() && (Tanggal.toInt() < 0 || Tanggal.toInt() > 31)) {
                txtAlertTanggal.text = "[!] Tanggal harus antara 1 sampai 31"
                txtAlertTanggal.visibility = View.VISIBLE
            } else if (Bulan.isNotEmpty() && (Bulan.toInt() < 0 || Bulan.toInt() > 12)) {
                txtAlertTanggal.text = "[!] Bulan harus antara 1 sampai 12"
                txtAlertTanggal.visibility = View.VISIBLE
            } else if (Tahun.isNotEmpty() && (Tahun.toInt() < 1500 || Tahun.toInt() > 2020)) {
                txtAlertTanggal.text = "[!] Tahun harus antara 1500 sampai 2020"
                txtAlertTanggal.visibility = View.VISIBLE
            } else {
                txtAlertTanggal.visibility = View.INVISIBLE
                enableButtonKirim()
            }
        }
        override fun beforeTextChanged(d1: CharSequence?, d2: Int, d3: Int, d4: Int) {}
        override fun onTextChanged(d1: CharSequence?, d2: Int, d3: Int, d4: Int) {}
    }

    private fun enableButtonKirim() {
        btnKirim.isEnabled =
            Nama.isNotEmpty() && NIM.isNotEmpty() && Jurusan.isNotEmpty() &&
                    JenisKelamin.isNotEmpty() && Alamat.isNotEmpty() && Tanggal.isNotEmpty() &&
                    Bulan.isNotEmpty() && Tahun.isNotEmpty()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnKirim -> {
                val intent = Intent(this, DataActivity::class.java)

                intent.putExtra("NAMA", Nama)
                intent.putExtra("NIM", NIM)
                intent.putExtra("JURUSAN", Jurusan)
                intent.putExtra("JENIS_KELAMIN", JenisKelamin)
                intent.putExtra("ALAMAT", Alamat)
                intent.putExtra("TANGGAL", Tanggal)
                intent.putExtra("BULAN", Bulan.toInt())
                intent.putExtra("TAHUN", Tahun)

                startActivity(intent)
            }
        }
    }
}

