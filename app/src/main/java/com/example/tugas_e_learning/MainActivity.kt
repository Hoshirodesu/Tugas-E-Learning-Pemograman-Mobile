package com.example.tugas_e_learning

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var editTextMain: EditText
    private lateinit var textPhoneLabel: TextView
    private lateinit var textDateLabel: TextView
    private lateinit var textTimeLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        editTextMain = findViewById(R.id.editText_main)
        textPhoneLabel = findViewById(R.id.text_phoneLabel)
        textDateLabel = findViewById(R.id.text_dateLabel)
        textTimeLabel = findViewById(R.id.text_timeLabel)

        editTextMain.inputType = InputType.TYPE_CLASS_PHONE
        editTextMain.keyListener = DigitsKeyListener.getInstance("0123456789+")
    }


    fun showText(view: View) {
        val inputNumber = editTextMain.text.toString().trim()

        if (inputNumber.isEmpty() || !Patterns.PHONE.matcher(inputNumber).matches()) {
            Toast.makeText(this, "Masukkan nomor telepon yang valid", Toast.LENGTH_SHORT).show()
            return
        }

        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Nomor")
            .setMessage("Apakah Anda yakin ingin menyimpan nomor ini?\n$inputNumber")
            .setPositiveButton("Ya") { dialog, _ ->
                findViewById<TextView>(R.id.text_phoneLabel).text = inputNumber
                dialog.dismiss()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    fun showDatePickerDialog(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { _, y, m, d ->
            val dateText = "${d}/${m + 1}/$y"
            textDateLabel.text = dateText
            Toast.makeText(this, "Tanggal: $dateText", Toast.LENGTH_SHORT).show()
        }, year, month, day).show()
    }
    @SuppressLint("DefaultLocale")
    fun showTimePickerDialog(view: View) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        TimePickerDialog(this, { _, h, m ->
            val timeText = String.format("%02d:%02d", h, m)
            textTimeLabel.text = timeText
            Toast.makeText(this, "Waktu: $timeText", Toast.LENGTH_SHORT).show()
        }, hour, minute, true).show()
    }

    fun resetButton(view: View) {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi")
            .setMessage("Apakah Anda yakin ingin mereset data?")
            .setPositiveButton("Ya") { dialog, _ ->
                editTextMain.setText("")
                textPhoneLabel.text = "-"
                textDateLabel.text = "DD/MM/YYYY"
                textTimeLabel.text = "HH:MM"
                dialog.dismiss()
                Toast.makeText(this, "data telah direset", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .show()

    }
}