package com.muratipek.ak4_foodbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.muratipek.ak4_foodbook.databinding.FoodRecyclerRowBinding
import com.muratipek.ak4_foodbook.model.Food
import com.muratipek.ak4_foodbook.util.didPlaceholder
import com.muratipek.ak4_foodbook.util.downloadImage
import com.muratipek.ak4_foodbook.view.FoodListFragmentDirections

class FoodRecyclerAdapter(val foodList: ArrayList<Food>): RecyclerView.Adapter<FoodRecyclerAdapter.FoodHolder>() {
    class FoodHolder(val binding : FoodRecyclerRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val binding = FoodRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodHolder(binding)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.binding.foodName.text = foodList[position].name
        holder.binding.foodCalori.text = foodList[position].calori
        //image will be added
        holder.itemView.setOnClickListener {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.imageView.downloadImage(foodList.get(position).image, didPlaceholder(holder.itemView.context))
    }
    fun updateFoodList(newFoodList: List<Food>){
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }
}