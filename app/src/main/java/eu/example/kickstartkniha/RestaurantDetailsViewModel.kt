package eu.example.kickstartkniha

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RestaurantDetailsViewModel(
    private val stateHandle: SavedStateHandle
): ViewModel() {
    private var restInterface: RestaurantsApiService
    val state = mutableStateOf<Restaurant?>(null)
    init{
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://restaurant-db-8b103-default-rtdb.firebaseio.com/")
            .build()
        restInterface = retrofit.create(
            RestaurantsApiService::class.java
        )
        val id = stateHandle.get<Int>("restaurant_id")?: 0

        viewModelScope.launch {
            val restaurant = getRemoteRestaurant(id)
            state.value = restaurant
        }


    }

    private suspend fun getRemoteRestaurant(id: Int): Restaurant {
        return withContext(Dispatchers.IO) {
            val responseMap = restInterface.getRestaurant(id)
            return@withContext responseMap.values.first()
        }
    }
}