package com.example.caloriescalculator.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caloriescalculator.adapter.ListAdapter
import com.example.caloriescalculator.R
import com.example.caloriescalculator.viewmodel.FoodViewModel
import kotlinx.android.synthetic.main.fragment_food_list.view.*


class FoodListFragment : Fragment() {

    private lateinit var mFoodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_food_list, container, false)

        //Initialize RecyclerView
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Initialize FoodViewModel
        mFoodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
        mFoodViewModel.readAllData.observe(viewLifecycleOwner, Observer { food ->
            adapter.setData(food)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_foodListFragment_to_addFoodFragment)
        }
        return view
    }
}