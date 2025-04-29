package com.example.travelling.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelling.State.ResultDataClass
import com.example.travelling.Supabase.Constant.supabase
import com.example.travelling.Tables.Categories
import com.example.travelling.Tables.Travelling
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ItemView : ViewModel(){
    private val _resultDataClass = MutableStateFlow<ResultDataClass>(ResultDataClass.Loading)
    val resultDataClass: StateFlow<ResultDataClass> = _resultDataClass.asStateFlow()

    private val _travelling = MutableLiveData<List<Travelling>>()
    val travelling: LiveData<List<Travelling>> get() = _travelling

    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>> get() = _categories

    private var filterTravelling: List<Travelling> = listOf()

    init {
        loadTravelling()
        loadCategoriesTravell()

    }

    private fun loadTravelling(){
        _resultDataClass.value = ResultDataClass.Loading
        viewModelScope.launch {
            try{
                filterTravelling = supabase.postgrest.from("Travelling").select().decodeList<Travelling>()
                _travelling.value = filterTravelling
                _resultDataClass.value = ResultDataClass.Success("Все выполнилось")
            }
            catch (ex: Exception){
                _resultDataClass.value = ResultDataClass.Error(ex.message + "Ошибка")
            }
        }
    }

    private fun loadCategoriesTravell(){
        viewModelScope.launch {
            try{
                _categories.value = supabase.postgrest.from("Categories").select().decodeList<Categories>()
            }
            catch (ex: Exception){
                _resultDataClass.value = ResultDataClass.Error(ex.message + "Ошибка12421")
            }
        }
    }

    fun filterListTravells(strFilt: String, categoryId: String?) {
        val filteredTravellingAll = filterTravelling.filter { travelling ->
            travelling.name.contains(strFilt)
        }
        val filteredTravelling = filterTravelling.filter { travelling ->
            travelling.name.contains(strFilt)
        }
        val filteredTravellingCategories = filterTravelling.filter { travelling ->
            travelling.categoryId == categoryId
        }
        if (categoryId != "" && strFilt != "") {
            _travelling.value = filteredTravellingAll
        } else if (categoryId != "" && strFilt == "") {
            _travelling.value = filteredTravellingCategories
        } else if (categoryId == "" && strFilt != "") {
            _travelling.value = filterTravelling
        }
    }}