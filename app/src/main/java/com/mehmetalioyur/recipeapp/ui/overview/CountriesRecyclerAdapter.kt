package com.mehmetalioyur.recipeapp.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mehmetalioyur.recipeapp.databinding.RowCountryBinding
import com.mehmetalioyur.recipeapp.model.country.CountryModel
import javax.inject.Inject

class CountriesRecyclerAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<CountriesRecyclerAdapter.CountriesViewHolder>() {

    inner class CountriesViewHolder(private val itemBinding: RowCountryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(country : CountryModel) {
            itemBinding.rvCountryNameTv.text = country.name
            glide.load(country.flag).into(itemBinding.rvCountryImage)

            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(country.nationality)
                }
            }

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view = RowCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)

    }

    override fun getItemCount(): Int {
        return countries.size
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }


    private val diffUtil = object : DiffUtil.ItemCallback<CountryModel>() {
        override fun areItemsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
            return oldItem == newItem //true
        }
        override fun areContentsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
            return oldItem == newItem //false
        }
    }
    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var countries: List<CountryModel>
        get() {
            return recyclerListDiffer.currentList
        }
        set(value) {
            return recyclerListDiffer.submitList(value)
        }
}