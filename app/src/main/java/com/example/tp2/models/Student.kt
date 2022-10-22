package com.example.tp2.models

class Student(var name: String, var firstname: String, val gender: GenderEnum) {
    fun getFullName() = "$firstname $name"
}