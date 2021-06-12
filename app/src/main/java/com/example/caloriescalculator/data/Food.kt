package com.example.caloriescalculator.data

import android.os.Parcelable
import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "food_data")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val day: String,
    val foodItems: String,
    val numberOfServings: String,
    val calories: Int
): Parcelable