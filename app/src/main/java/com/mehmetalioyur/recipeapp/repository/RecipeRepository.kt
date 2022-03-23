package com.mehmetalioyur.recipeapp.repository

import androidx.lifecycle.LiveData
import com.mehmetalioyur.recipeapp.data.local.RecipesDao
import com.mehmetalioyur.recipeapp.data.remote.RecipeApiService
import com.mehmetalioyur.recipeapp.model.categories.Categories
import com.mehmetalioyur.recipeapp.model.meal.Meal
import com.mehmetalioyur.recipeapp.model.meal.MealModel
import com.mehmetalioyur.recipeapp.util.Resource
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val api: RecipeApiService,
    private val dao : RecipesDao
) {

    suspend fun insertRecipe(meal: Meal) {
        dao.insertRecipe(meal)
    }

    suspend fun deleteRecipe(meal: Meal) {
        dao.deleteRecipe(meal)
    }

    suspend fun isRowExists(id: Int?): Boolean {
        return dao.isRowExist(idMeal = id)
    }

    fun getRecipes(): LiveData<List<Meal>> {
        return dao.observeRecipes()
    }

    suspend fun searchMealWithName(searchWithName: String): Resource<MealModel> {
        return try {
            val response = api.searchMealWithName(searchWithName)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("No Data!" )
            } else {
                Resource.Error("No Data")
            }
        } catch (e: Exception) {
            Resource.Error("No Data")
        }
    }

    suspend fun lookUpWithId(searchMealId: Int): Resource<MealModel> {
        return try {
            val response = api.lookUpWithId(searchMealId)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("No Data!")
            } else {
                Resource.Error("No Data")
            }
        } catch (e: Exception) {
            Resource.Error("No Data")
        }
    }

    suspend fun randomMeal(): Resource<MealModel> {
        return try {
            val response = api.randomMeal()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("No Data!")
            } else {
                Resource.Error("No Data")
            }
        } catch (e: Exception) {
            Resource.Error("No Data")

        }
    }

    suspend fun categories(): Resource<Categories> {
        return try {
            val response = api.categories()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("No Data!")
            } else {
                Resource.Error("No Data")
            }
        } catch (e: Exception) {
            Resource.Error("No Data")
        }
    }


    suspend fun filterByCategory(categoryName: String): Resource<MealModel> {
        return try {
            val response = api.filterByCategory(categoryName)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("No Data!")
            } else {
                Resource.Error("No Data")
            }
        } catch (e: Exception) {
            Resource.Error("No Data")
        }
    }

    suspend fun filterByNation(nationName: String): Resource<MealModel> {
        return try {
            val response = api.filterByNation(nationName)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("No Data!")
            } else {
                Resource.Error("No Data")
            }
        } catch (e: Exception) {
            Resource.Error("No Data")
        }
    }
}