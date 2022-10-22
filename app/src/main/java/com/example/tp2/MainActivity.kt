package com.example.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.models.GenderEnum
import com.example.tp2.models.Student

class MainActivity : AppCompatActivity() {
    val spinner: Spinner by lazy { findViewById(R.id.spinner) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var matieres = listOf<String>("Cours","TP");
        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,matieres)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val toast: Toast = Toast.makeText(
                    applicationContext,
                    adapterView?.getItemAtPosition(position).toString(),
                    Toast.LENGTH_SHORT)
                toast.show()
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }

        val students = arrayOf<Student>(
            Student("degla", "fsfs", GenderEnum.Male),
            Student("kaabi", "taoufik", GenderEnum.Male),
            Student("mra", "mra", GenderEnum.Female)
        )

        val studentsRecyclerView = findViewById<RecyclerView>(R.id.studentsView)
        studentsRecyclerView.adapter = StudentAdapter(students)
        studentsRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}