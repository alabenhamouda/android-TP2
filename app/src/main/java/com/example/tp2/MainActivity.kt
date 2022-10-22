package com.example.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.models.GenderEnum
import com.example.tp2.models.Student

class MainActivity : AppCompatActivity() {
    val spinner: Spinner by lazy { findViewById(R.id.spinner) }
    val studentsRecyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.studentsView) }
    val txtSearchCriteria: EditText by lazy { findViewById(R.id.searchCriteria) }
    lateinit var currentAdapter: StudentAdapter
    lateinit var adapters: Array<StudentAdapter>

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

                switchToAdapter(position)
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }

        adapters = arrayOf(
            StudentAdapter(arrayListOf(
                Student("mostfa", "saad", GenderEnum.Male),
                Student("kaabi", "taoufik", GenderEnum.Male),
            )),
            StudentAdapter(arrayListOf(
                Student("mra", "mra", GenderEnum.Female)
            ))
        )

        studentsRecyclerView.layoutManager = LinearLayoutManager(this)
        switchToAdapter(0)

        txtSearchCriteria.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                currentAdapter.filter.filter(s)
            }
        })
    }

    fun switchToAdapter(idx: Int) {
        if (idx < 0 || idx >= adapters.size) {
            return
        }

        currentAdapter = adapters[idx]
        studentsRecyclerView.adapter = currentAdapter
        currentAdapter.filter.filter(txtSearchCriteria.text.toString())
    }
}