package com.example.travelling.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelling.State.ResultDataClass
import com.example.travelling.State.TravellingDataClass
import com.example.travelling.Supabase.Constant
import com.example.travelling.Supabase.Constant.supabase
import com.example.travelling.Tables.Categories
import com.example.travelling.Tables.Travelling

import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InterfaceViewModel (id: String) : ViewModel() {
    val _id = id
    private val _resultDataClassSelectTravelling = MutableStateFlow<ResultDataClass>(ResultDataClass.Loading)
    val resultDataClassSelectTravelling: StateFlow<ResultDataClass> = _resultDataClassSelectTravelling.asStateFlow()

    lateinit var travelling: Travelling

    private val _travellingDataClass = mutableStateOf(TravellingDataClass())
    val travellingDataClass: TravellingDataClass get() = _travellingDataClass.value

    private val _userId = mutableStateOf<String?>(null)
    val userId:String? get() = _userId.value

    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>> get() = _categories

    init {
        selectTravelling()
        loadCategoriesTravelling()
    }

    fun updateTravelling(newTravelling: TravellingDataClass){
        _travellingDataClass.value = newTravelling
    }
    fun selectTravelling(){
        _resultDataClassSelectTravelling.value = ResultDataClass.Loading
        viewModelScope.launch {
            try{
                travelling = supabase.postgrest.from("Travelling").select(){
                    filter {
                        eq("id", _id)
                    }
                }.decodeSingle<Travelling>()
                _travellingDataClass.value = TravellingDataClass(
                    name = travelling.name,
                    description = travelling.description,
                    categoryId = travelling.categoryId,
                    image = travelling.image,
                    Country = travelling.Country,
                    Sozdatel = travelling.Sozdatel
                )
                _userId.value = Constant.supabase.auth.currentUserOrNull()?.id
                _resultDataClassSelectTravelling.value = ResultDataClass.Success("Хорошо")
            } catch(ex: Exception){
                _resultDataClassSelectTravelling.value = ResultDataClass.Error("Ошибка")
            }
        }
    }

    fun DeleteTravelling(){
        viewModelScope.launch {
            try{
                supabase.postgrest.from("Travelling").delete(){
                    filter {
                        eq("id", _id)
                    }
                }
            }
            catch(ex: Exception){
            }
        }
    }

    fun UpdateTravelling(){
        viewModelScope.launch {
            try{
                supabase.postgrest.from("Travelling").update(
                    {
                        set("name", _travellingDataClass.value.name)
                        set("description", _travellingDataClass.value.description)
                        set("categoryId", _travellingDataClass.value.categoryId)
                        set("Country", _travellingDataClass.value.Country)
                    }
                ){
                    filter {
                        eq("id", _id)
                    }
                }
            }
            catch (ex: Exception){
            }
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