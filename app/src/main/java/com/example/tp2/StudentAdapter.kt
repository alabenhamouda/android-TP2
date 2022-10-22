package com.example.tp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.models.GenderEnum
import com.example.tp2.models.Student
import java.util.*
import kotlin.collections.ArrayList

class StudentAdapter (private val students: ArrayList<Student>) :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>(),
    Filterable
{
    private var dataFilterList: ArrayList<Student>

    init {
        dataFilterList = students
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtFullName: TextView
        private val photo: ImageView

        init {
            txtFullName = view.findViewById(R.id.fullName)
            photo = view.findViewById(R.id.photo)
        }

        fun bind(student: Student) {
            txtFullName.text = student.getFullName()

            val imageResource = if (student.gender == GenderEnum.Male) {
                R.drawable.male
            } else {
                R.drawable.female
            }

            photo.setImageResource(imageResource)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentAdapter.ViewHolder, position: Int) {
        val student = dataFilterList[position]
        holder.bind(student)
    }

    override fun getItemCount(): Int = dataFilterList.size

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    dataFilterList = students
                } else {
                    val resultList = ArrayList<Student>()
                    for (student in students) {
                        if (student.getFullName().lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(student)
                        }
                    }
                    dataFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as ArrayList<Student>
                notifyDataSetChanged()
            }

        }
    }
}