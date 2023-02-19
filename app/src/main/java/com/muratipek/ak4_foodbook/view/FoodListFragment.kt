package com.muratipek.ak4_foodbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.muratipek.ak4_foodbook.adapter.FoodRecyclerAdapter
import com.muratipek.ak4_foodbook.databinding.FragmentFoodListBinding
import com.muratipek.ak4_foodbook.viewmodel.FoodListViewModel

class FoodListFragment : Fragment() {
    private lateinit var binding : FragmentFoodListBinding

    private lateinit var viewModel : FoodListViewModel
    private val recyclerFoodAdapter = FoodRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFoodListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Extension
        /*
        var myString = "Murat"
        myString.myString(myString)
         */

        viewModel = ViewModelProvider(this).get(FoodListViewModel::class.java)
        viewModel.refreshData()

        binding.foodListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.foodListRecyclerView.adapter = recyclerFoodAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.foodLoading.visibility = View.VISIBLE
            binding.foodErrorMessage.visibility = View.GONE
            binding.foodListRecyclerView.visibility = View.GONE
            viewModel.refreshFromInternet()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }
    fun observeLiveData(){

        viewModel.foods.observe(viewLifecycleOwner, Observer { foods ->
            foods?.let {
                binding.foodListRecyclerView.visibility = View.VISIBLE
                recyclerFoodAdapter.updateFoodList(it)
            }
        })
        viewModel.foodErrorMessage.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it){
                    binding.foodErrorMessage.visibility = View.VISIBLE
                    binding.foodListRecyclerView.visibility = View.GONE
                }else{
                    binding.foodErrorMessage.visibility = View.GONE
                }
            }
        })
        viewModel.foodLoading.observe(viewLifecycleOwner, Observer { loading ->
        loading?.let {
            if(it){
                binding.foodListRecyclerView.visibility = View.GONE
                binding.foodErrorMessage.visibility = View.GONE
                binding.foodLoading.visibility = View.VISIBLE
            }else{
                binding.foodLoading.visibility = View.GONE
            }
        }

        })
    }
}