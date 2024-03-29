package com.willowtree.foodapp.ui.food

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.willowtree.foodapp.api.data.Category
import com.willowtree.foodapp.api.data.FoodishResponse
import kotlinx.coroutines.launch

class FoodViewModel(val foodRepository: FoodRepository) : ViewModel() {
    val foodPicUrl = MutableLiveData<String>()

    fun getRandomFoodPic(category: Category? = null) {
        Log.e("JG", "getRandomFoodPic called: ViewModel instance: $this")
        viewModelScope.launch {
            val foodishResponse = foodRepository.getRandomFoodPic(category)
            foodPicUrl.value = foodishResponse.image
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val foodRepository: FoodRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FoodViewModel(foodRepository) as T
        }
    }
}