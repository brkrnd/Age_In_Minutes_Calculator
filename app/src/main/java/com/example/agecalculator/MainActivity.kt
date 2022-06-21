package com.example.agecalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedText : TextView? = null
    private var ageInMinutesTextView : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val selectDateButton : Button = findViewById(R.id.selectDateButton)
        selectedText = findViewById(R.id.selectedText)
        ageInMinutesTextView = findViewById(R.id.ageInMinutesTextView)
        selectDateButton.setOnClickListener{
            dateButtonClicked()
        }
    }
    @SuppressLint("SimpleDateFormat")
    private fun dateButtonClicked(){

        val myCalendar = Calendar.getInstance()
        val yearSelected = myCalendar.get(Calendar.YEAR)
        val monthSelected = myCalendar.get(Calendar.MONTH)
        val daySelected = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd =
            DatePickerDialog(this, {
                    _, year, month, dayOfMonth ->
                    Toast.makeText(this,
                        "You selected a date", Toast.LENGTH_LONG).show()
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    selectedText?.text = selectedDate
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ITALY)

                    val theDate = sdf.parse(selectedDate)

                    val selectedDateInMinutes = theDate!!.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    val currentDateInMinutes = currentDate!!.time / 60000

                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                    ageInMinutesTextView?.text = differenceInMinutes.toString()
                },
                yearSelected,
                monthSelected,
                daySelected
            )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 84600000
        dpd.show()
    }
}