```
Nama: Mochammad Raffi Fadhillah
NPM: 23552011066
Kelas: TIF RP 23 CID B
```
# Implementasi 4 input control (Date time, Alert, Toast, Input Phone Number
### Project ini dibuat untuk memenuhi tugas E-Learning Pemograman Mobile
## Fitur dan penjelasan:
## 1. Date & Time picker
![datetime](https://github.com/user-attachments/assets/afdac4a8-8dd9-4c11-8c28-28b5c71a9269) | ![datetime2](https://github.com/user-attachments/assets/ea89fb59-7355-4d8c-9b8a-4ceef14b0c21) | ![datetime3](https://github.com/user-attachments/assets/42bacdf7-3979-4376-9e49-d5097b159f86)
|:-------------------------------:|:-------------------------------:|:-------------------------------:|

### Fitur:
- Memudahkan pengguna memilih tanggal dan waktu lewat dialog standar Android.
- Setelah memilih, tanggal dan waktu akan ditampilkan di layar dan toast
- Hasil input tanggal dam waktu akan ditampilkan di TextView dengan ID ```@+id/text_dateLabel``` dan ```@+id/text_timeLabel```
- Hasil implementasi:
```
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
```

## 2. Alert Dialog
![alert](https://github.com/user-attachments/assets/759ab20c-a340-4052-bb10-9baf43c96a97) | ![alert2](https://github.com/user-attachments/assets/876fdb79-ff43-4613-a29b-65c3fa54c6a4)
|:-------------------------------:|:-------------------------------:|

### Fitur:
- Menampilkan dialog konfirmasi sebelum menyimpan data nomor telepon dan mereset data
- Pilihan "Ya" untuk menyetujui, dan "Tidak" untuk membatalkan
- Hasil implementasi:
```
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
```

## 3. Toast
![toast](https://github.com/user-attachments/assets/59e92ea4-119d-466a-b163-2348f2dca549) | ![toast2](https://github.com/user-attachments/assets/3707e826-491f-420b-a902-bb08af5d82d6) | ![toast3](https://github.com/user-attachments/assets/aaf82370-2da1-47c3-8220-ce2a5dd71e87) | ![toast5](https://github.com/user-attachments/assets/c67e4740-6e59-47f8-a7aa-3d8672843a20)
|:-------------------------------:|:-------------------------------:|:-------------------------------:|:-------------------------------:|

### Fitur:
- Memudahkan pengguna memilih tanggal dan waktu lewat dialog standar android
- Saat pengguna meng-submit nomor telepon, tanggal, waktu, dan reset maka akan muncul sebuah pesan toast
- Hasil Implementasi:
```
//Nomor telepon
Toast.makeText(this, "Masukkan nomor telepon yang valid", Toast.LENGTH_SHORT).show()

//Tanggal
Toast.makeText(this, "Tanggal: $dateText", Toast.LENGTH_SHORT).show()

//Waktu
Toast.makeText(this, "Waktu: $timeText", Toast.LENGTH_SHORT).show()

//Reset
Toast.makeText(this, "tanggal dan waktu telah direset", Toast.LENGTH_SHORT).show()
}
```

## 4. Input Phone Number
<img src="https://github.com/user-attachments/assets/d2ec3a9d-365d-4774-ab3f-df98f992c3a5" alt="phoneNumber" width="480" height="200">
<img src="https://github.com/user-attachments/assets/997b368d-0708-42c1-aede-63eabc00c768" alt="phoneNumber2" width="480" height="200">

### Fitur:
- Memastikan pengguna hanya dapat memasukkan karakter yang valid untuk nomor telepon
- Menggunakan EditText dengan ```inputType="phone"``` dan ```DigitsKeyListener``` untuk menginput nomor telepon
- Hasil implementasi:
```
//Activity_main.xml
<EditText
        android:id="@+id/editText_main"
        android:layout_width="390dp"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_marginLeft="8dp"
        android:layout_toStartOf="@id/editText_main"
        android:hint="Input number"
        android:inputType="phone"
        android:minHeight="48dp" />

//MainActivity.kt
editTextMain = findViewById(R.id.editText_main)
editTextMain.inputType = InputType.TYPE_CLASS_PHONE
editTextMain.keyListener = DigitsKeyListener.getInstance("0123456789+")
```
