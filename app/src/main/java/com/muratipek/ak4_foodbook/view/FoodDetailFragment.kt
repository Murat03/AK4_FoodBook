package com.muratipek.ak4_foodbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.muratipek.ak4_foodbook.R
import com.muratipek.ak4_foodbook.databinding.FragmentFoodDetailBinding
import com.muratipek.ak4_foodbook.viewmodel.FoodDetailViewModel

class FoodDetailFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailBinding
    private lateinit var viewModel : FoodDetailViewModel
    private var foodId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFoodDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FoodDetailViewModel::class.java)
        viewModel.getRoomData()

        arguments?.let {
            foodId = FoodDetailFragmentArgs.fromBundle(it).foodId
            println(foodId)
        }
        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.foodLiveData.observe(viewLifecycleOwner, Observer { food ->
            food?.let {
                binding.foodName.text = it.name
                binding.foodCalori.text = it.calori
                binding.foodCarbohydrate.text = it.carbohydrate
                binding.foodProtein.text = it.protein
                binding.foodFat.text = it.fat
            }
        })
    }
}