package com.pry.kspiguana

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class viewmodel @Inject constructor(): ViewModel() {

    private var list: MutableList<String> = arrayListOf("juan", "sergio","pedro")
    private var _listPersons = MutableLiveData<List<String>>()
    val listPersons: LiveData<List<String>> = _listPersons


    fun getListPersons() {
        viewModelScope.launch {
            _listPersons.value = list
        }
    }
}