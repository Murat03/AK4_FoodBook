package com.muratipek.ak4_foodbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.muratipek.ak4_foodbook.R
import com.muratipek.ak4_foodbook.databinding.FoodRecyclerRowBinding
import com.muratipek.ak4_foodbook.model.Food
import com.muratipek.ak4_foodbook.view.FoodListFragmentDirections

class FoodRecyclerAdapter(val foodList: ArrayList<Food>): RecyclerView.Adapter<FoodRecyclerAdapter.FoodHolder>(), FoodClickListener {

    class FoodHolder(var binding: FoodRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FoodRecyclerRowBinding>(
            inflater,
            R.layout.food_recycler_row,
            parent,
            false
        )
        return FoodHolder(binding)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.binding.food = foodList[position]
        holder.itemView.setOnClickListener {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(foodList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        /*
        holder.binding.foodName.text = foodList[position].name
        holder.binding.foodCalori.text = foodList[position].calori
        //image will be added
        holder.itemView.setOnClickListener {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(foodList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.imageView.downloadImage(foodList.get(position).image, didPlaceholder(holder.itemView.context))
         */
    }

    override fun foodClicked(view: View) {
        /*
        val uuid = view.food_uuid.text.toString().toIntOrNull()
        uuid?.let {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(it)
            Navigation.findNavController(view).navigate(action)
        }

         */
    }

    fun updateFoodList(newFoodList: List<Food>) {
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }
}