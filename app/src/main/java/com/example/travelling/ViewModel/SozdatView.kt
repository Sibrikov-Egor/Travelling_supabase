package com.example.travelling.ViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelling.State.TravellingDataClass
import com.example.travelling.Supabase.Constant
import com.example.travelling.Supabase.Constant.supabase
import com.example.travelling.Tables.Categories
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class SozdatView : ViewModel() {

    private val _travellingDataClass = mutableStateOf(TravellingDataClass())
    val travellingDataClass: TravellingDataClass get() = _travellingDataClass.value

    private val _userId = mutableStateOf<String?>("")
    val userId:String? get() = _userId.value

    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>> get() = _categories

    @Serializable
    data class newTravelling(
        val name: String,
        val description: String,
        @SerialName("category_id")
        val categoryId: String,
        val Country: String,
        val Sozdatel: String,

        )

    init{
        selectUser()
        loadCategoriesTravelling()
    }

    fun updateTravelling(newTravelling: TravellingDataClass){
        _travellingDataClass.value = newTravelling
    }
    fun selectUser(){
        viewModelScope.launch {
            try{
                _userId.value = Constant.supabase.auth.currentUserOrNull()?.id
            }
            catch (ex: Exception){ }
        }
    }
    fun addTravelling(){
        viewModelScope.launch {
            try {
                supabase.postgrest.from("Travelling").insert(
                    newTravelling(
                        name = _travellingDataClass.value.name,
                        description = _travellingDataClass.value.description,
                        categoryId = _travellingDataClass.value.categoryId,
                        Sozdatel = _userId.value ?: "",
                        Country = _travellingDataClass.value.Country,
                    )
                )
            }
            catch (ex: Exception){
                Log.e("AddTravelling", "Full error: ", ex)
                println("Detailed error: ${ex.stackTraceToString()}")}
        }
    }
    private fun loadCategoriesTravelling(){
        viewModelScope.launch {
            try{
                _categories.value = supabase.postgrest.from("Categories").select().decodeList<Categories>()
            }
            catch (ex: Exception){
            }
        }
    }
}