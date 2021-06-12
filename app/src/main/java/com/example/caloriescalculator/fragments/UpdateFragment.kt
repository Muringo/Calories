package com.example.caloriescalculator.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.caloriescalculator.R
import com.example.caloriescalculator.data.Food
import com.example.caloriescalculator.viewmodel.FoodViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mFoodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mFoodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)

        view.update_day.setText(args.currentFood.day)
        view.update_food_items.setText(args.currentFood.foodItems)
        view.update_number_of_serving.setText(args.currentFood.numberOfServings)
        view.update_calories.setText(args.currentFood.calories.toString())

        view.update_btn.setOnClickListener {
            updateItem()
        }

        //Add Menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
        val day = update_day.text.toString()
        val numberofServing = update_number_of_serving.text.toString()
        val foodItem = update_food_items.text.toString()
        val calories = Integer.parseInt(update_calories.text.toString())

        if (inputCheck(day, numberofServing, foodItem, update_calories.text)){
            val updatedFood = Food(args.currentFood.id, day, numberofServing, foodItem, calories)
            mFoodViewModel.updateFood(updatedFood)
            findNavController().navigate(R.id.action_updateFragment_to_foodListFragment)
            Toast.makeText(requireContext(), "Updated succefully", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), "PLease fill out all fields", Toast.LENGTH_LONG).show()

        }
    }

    private fun inputCheck(day: String, numberOfServing: String, foodItem: String, calories: Editable): Boolean{
        return !(TextUtils.isEmpty(day) && numberOfServing.isEmpty() && TextUtils.isEmpty(foodItem) && calories.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete ${args.currentFood.day}")
        builder.setMessage("Are you sure you want to delete this item ${args.currentFood.day}")
        builder.setPositiveButton("Yes"){_, _ ->
            mFoodViewModel.deleteFood(args.currentFood)
            findNavController().navigate(R.id.action_updateFragment_to_foodListFragment)
            Toast.makeText(requireContext(), "Succeffully removed: ${args.currentFood.day}", Toast.LENGTH_SHORT).show()
        }
        builder.setPositiveButton("No"){_, _ -> }
        builder.create().show()

    }


}