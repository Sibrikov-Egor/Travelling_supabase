package com.example.travelling.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelling.State.ResultDataClass
import com.example.travelling.Supabase.Constant.supabase
import com.example.travelling.Tables.Categories
import com.example.travelling.Tables.Travells
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ItemView : ViewModel(){
    private val _resultDataClass = MutableStateFlow<ResultDataClass>(ResultDataClass.Loading)
    val resultDataClass: StateFlow<ResultDataClass> = _resultDataClass.asStateFlow()

    private val _travells = MutableLiveData<List<Travells>>()
    val games: LiveData<List<Travells>> get() = _travells

    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>> get() = _categories

    private var filterTravells: List<Travells> = listOf()

    init {
        loadTravell()
        loadCategoriesTravell()
    }

    private fun loadTravell(){
        _resultDataClass.value = ResultDataClass.Loading
        viewModelScope.launch {
            try{
                filterTravells = supabase.postgrest.from("Travells").select().decodeList<Travells>()
                _travells.value = filterTravells
                _resultDataClass.value = ResultDataClass.Success("Все выполнилось отлично")
            }
            catch (ex: Exception){
                _resultDataClass.value = ResultDataClass.Error(ex.message + "Ошибка")
            }
        }
    }

    private fun loadCategoriesTravell(){
        viewModelScope.launch {
            try{
                _categories.value = supabase.postgrest.from("GenresOfTravells").select().decodeList<Categories>()
            }
            catch (ex: Exception){
                _resultDataClass.value = ResultDataClass.Error(ex.message + "Ошибка")
            }
        }
    }

    fun filterListTravells(strFilt: String, categoryId: String?){
        val filteredGames = filterTravells.filter { travells ->
            travells.name.contains(strFilt)
        }
        val filtredTravellsCategories = filterTravells.filter{ travells ->
           travells.categoryId == categoryId
        }
        if (categoryId == ""){
            _travells.value = filteredGames
        }
        else
        {
            _travells.value = filtredTravellsCategories
        }
    }
}