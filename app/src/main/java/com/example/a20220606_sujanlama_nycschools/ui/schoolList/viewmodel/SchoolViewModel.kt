package com.example.a20220606_sujanlama_nycschools.ui.schoolList.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20220606_sujanlama_nycschools.model.School
import com.example.a20220606_sujanlama_nycschools.repository.Repository
import com.example.a20220606_sujanlama_nycschools.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    private val _schools = MutableLiveData<Resource<List<School>>>()

    val schoolsList: LiveData<Resource<List<School>>>
        get() = _schools

    init {
        getSchools()
    }

    fun getSchools() {
        _schools.value = Resource.loading()

        viewModelScope.launch {
            try {
                val resource = repository.getSchoolsList()
                if (resource.isSuccessful) {
                    _schools.value = Resource.success(resource.body() ?: listOf())
                } else {
                    _schools.value = Resource.error(resource.message() ?: "Something went wrong")
                }
            } catch (e: Exception) {
                // handle no internet connection
                if (e is UnknownHostException) {
                    _schools.value = Resource.error("No internet connection")
                } else {
                    _schools.value = Resource.error(e.localizedMessage ?: "Something went wrong")
                }
            }
        }
    }
}