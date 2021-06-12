package com.example.caloriescalculator.repository

import androidx.lifecycle.LiveData
import com.example.caloriescalculator.dao.FoodDao
import com.example.caloriescalculator.data.Food

class FoodRepository(private val foodDao: FoodDao) {

    val readAllData: LiveData<List<Food>> = foodDao.readAllData()

    suspend fun addFood(food: Food){
        foodDao.addFood(food)
    }

    suspend fun updateFood(food: Food){
        foodDao.updateFood(food)
    }

    suspend fun deleteFood(food: Food){
        foodDao.deleteFood(food)
    }

    suspend fun deleteAllData(){
        foodDao.deleteAllData()
    }
}