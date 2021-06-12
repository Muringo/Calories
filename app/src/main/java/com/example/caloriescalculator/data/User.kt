package com.example.caloriescalculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val age: Int,
    val height: String,
    val weight: Float,
    val gender: String,
    val dailyCalorieIntake: String





    )
