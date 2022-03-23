package com.mehmetalioyur.recipeapp.ui.meallist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mehmetalioyur.recipeapp.databinding.RowMealBinding
import com.mehmetalioyur.recipeapp.model.meal.Meal
import javax.inject.Inject

class MealListRecyclerAdapter @Inject constructor(

    private val glide: RequestManager
) :
    RecyclerView.Adapter<MealListRecyclerAdapter.MealListViewHolder>() {

    inner class MealListViewHolder(private val itemBinding: RowMealBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(meal: Meal) {
            itemBinding.rvMealNameTv.text = meal.strMeal
            glide.load(meal.strMealThumb).into(itemBinding.rvMealImage)

            itemView.setOnClickListener {
                onItemClickListener?.let { item ->
                    meal.idMeal?.let { id->
                        item(id) }
                }
            }

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealListViewHolder {
        val view = RowMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealListViewHolder, position: Int) {

        val meal = meals[position]
        holder.bind(meal)

    }

    override fun getItemCount(): Int {
        return meals.size
    }

    private var onItemClickListener : ((Int)-> Unit)? = null

    fun setOnItemClickListener (listener : (Int)-> Unit){

        onItemClickListener = listener
    }


    private val diffUtil = object  : DiffUtil.ItemCallback<Meal>(){
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem //true
        }
        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem //false
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)
    var meals : List<Meal>
        get() {
            return recyclerListDiffer.currentList
        }
        set(value) {
            return recyclerListDiffer.submitList(value)
        }



}