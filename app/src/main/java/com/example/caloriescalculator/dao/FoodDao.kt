package com.example.caloriescalculator.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.caloriescalculator.data.Food


@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFood(food: Food)

    @Update
    suspend fun updateFood(food: Food)

    @Delete
    suspend fun deleteFood(food: Food)

    @Query("DELETE FROM food_data")
    suspend fun deleteAllData()

    @Query("SELECT * FROM food_data ORDER BY id ASC")
    fun readAllData(): LiveData<List<Food>>
}