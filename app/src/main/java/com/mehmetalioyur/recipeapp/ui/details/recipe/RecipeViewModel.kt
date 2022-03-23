package com.mehmetalioyur.recipeapp.ui.details.recipe

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehmetalioyur.recipeapp.model.meal.MealModel
import com.mehmetalioyur.recipeapp.repository.RecipeRepository
import com.mehmetalioyur.recipeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository,
    sharedPreferences: SharedPreferences
) : ViewModel() {

    private val mealId = sharedPreferences.getInt("mealId", 57982)

    private val _mealDetails = MutableLiveData<Resource<MealModel>>()
    val mealDetails: LiveData<Resource<MealModel>>
        get() = _mealDetails

    private val _isSavedBefore = MutableLiveData<Boolean>()
    val isSavedBefore: LiveData<Boolean>
        get() = _isSavedBefore

    init {
        isMealExist()
        getMealModel()
    }

    private fun getMealModel() {
        _mealDetails.value = Resource.Loading()
        viewModelScope.launch {
            _mealDetails.value = repository.lookUpWithId(mealId)
        }
    }

    fun insertRecipe() = viewModelScope.launch {
        _mealDetails.value?.let { resourceMealModel ->
            resourceMealModel.data?.let {
                it.meals[0]
            }?.let { meal ->
                repository.insertRecipe(meal)
            }

        }

    }
    private fun isMealExist() = viewModelScope.launch {
        _isSavedBefore.value = repository.isRowExists(mealId)
    }


}


