package com.mehmetalioyur.recipeapp.ui.details.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mehmetalioyur.recipeapp.databinding.RowIngredientBinding
import javax.inject.Inject

class IngredientsRecyclerAdapter@Inject constructor(

    private val glide: RequestManager
) :
    RecyclerView.Adapter<IngredientsRecyclerAdapter.IngredientsViewHolder>() {

    inner class IngredientsViewHolder(private val itemBinding: RowIngredientBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(ingredient : String, measure : String) {
            itemBinding.ingredientName.text = ingredient
            itemBinding.measureName.text = measure
            glide.load("https://www.themealdb.com/images/ingredients/${ingredient}-Small.png").into(itemBinding.ingredientImage)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val view = RowIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientsViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
            val ingredient = ingredients[position]
            val measure = measures[position]

        holder.bind(ingredient,measure)


    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    private val diffUtilForIngredients = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem //true
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem //false
        }

    }

    private val recyclerListDifferForIngredients = AsyncListDiffer(this, diffUtilForIngredients)

    var ingredients: List<String>
        get() {
            return recyclerListDifferForIngredients.currentList
        }
        set(value) {

            return recyclerListDifferForIngredients.submitList(value)
        }

    private val diffUtilForMeasures = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem //true
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem //false
        }

    }

    private val recyclerListDifferForMeasures = AsyncListDiffer(this, diffUtilForMeasures)

    var measures: List<String>
        get() {
            return recyclerListDifferForMeasures.currentList
        }
        set(value) {

            return recyclerListDifferForMeasures.submitList(value)
        }

}

