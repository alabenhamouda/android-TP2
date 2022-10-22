package com.example.tp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.models.GenderEnum
import com.example.tp2.models.Student

class StudentAdapter (private val students: Array<Student>) : RecyclerView.Adapter<StudentAdapter.ViewHolder>()
{

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
        val student = students[position]
        holder.bind(student)
    }

    override fun getItemCount(): Int = students.size
}