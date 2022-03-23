package com.mehmetalioyur.recipeapp.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mehmetalioyur.recipeapp.databinding.RowCategoryBinding
import com.mehmetalioyur.recipeapp.model.categories.Category
import javax.inject.Inject

class OverviewRecyclerAdapter @Inject constructor(

    private val glide: RequestManager
) :
    RecyclerView.Adapter<OverviewRecyclerAdapter.OverviewViewHolder>() {

    inner class OverviewViewHolder(private val itemBinding: RowCategoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(category: Category) {
            itemBinding.rvCategoryNameTextView.text = category.strCategory
            glide.load(category.strCategoryThumb).into(itemBinding.rvCategoryImage)

            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(category.strCategory)
                }
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewViewHolder {
        val view = RowCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OverviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: OverviewViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)

    }

    override fun getItemCount(): Int {
        return categories.size
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }


    private val diffUtil = object : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem //true
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem //false
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var categories: List<Category>
        get() {
            return recyclerListDiffer.currentList
        }
        set(value) {
            return recyclerListDiffer.submitList(value)
        }
}