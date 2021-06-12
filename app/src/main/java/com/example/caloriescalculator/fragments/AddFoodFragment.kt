package com.example.caloriescalculator.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.caloriescalculator.R
import com.example.caloriescalculator.data.Food
import com.example.caloriescalculator.viewmodel.FoodViewModel
import kotlinx.android.synthetic.main.fragment_add_food.*
import kotlinx.android.synthetic.main.fragment_add_food.view.*
import kotlinx.android.synthetic.main.fragment_update.*

class AddFoodFragment : Fragment() {

    private lateinit var mFoodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_food, container, false)

        mFoodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)

        view.btn_add.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val day = editText_day.text.toString()
        val numberOfServing = et_number_of_serving.text.toString()
        val foodItem = et_food_items.text.toString()
        val calories = Integer.parseInt(et_calories.text.toString())

        if (inputCheck(day, numberOfServing, foodItem, et_calories.text)){
            //Create Object
            val food = Food(0, day, numberOfServing, foodItem, Integer.parseInt(calories.toString()))
            //Add data to database
            mFoodViewModel.addFood(food)
            findNavController().navigate(R.id.action_addFoodFragment_to_foodListFragment)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()

        }
    }

    private fun inputCheck(day: String, numberOfServing: String, foodItem: String, calories: Editable): Boolean{
        return !(TextUtils.isEmpty(day) && TextUtils.isEmpty(numberOfServing) && TextUtils.isEmpty(foodItem) && calories.isEmpty())
    }

}