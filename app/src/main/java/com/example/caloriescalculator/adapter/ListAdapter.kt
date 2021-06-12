package com.example.caloriescalculator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.caloriescalculator.R
import com.example.caloriescalculator.data.Food
import com.example.caloriescalculator.fragments.FoodListFragmentDirections
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var foodList = emptyList<Food>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = foodList[position]
        holder.itemView.id_tv.text = currentItem.id.toString()
        holder.itemView.day_tv.text = currentItem.day
        holder.itemView.food_tv.text = currentItem.foodItems
        holder.itemView.serving_tv.text = currentItem.numberOfServings
        holder.itemView.calories_tv.text = currentItem.calories.toString()

        holder.itemView.rowLayout.setOnClickListener {
            val action = FoodListFragmentDirections.actionFoodListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(food: List<Food>){
        this.foodList = food
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}