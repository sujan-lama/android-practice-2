package com.example.a20220606_sujanlama_nycschools.ui.schoolDetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20220606_sujanlama_nycschools.model.SchoolSAT
import com.example.a20220606_sujanlama_nycschools.repository.Repository
import com.example.a20220606_sujanlama_nycschools.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class SchoolDetailViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    private val _schoolSat = MutableLiveData<Resource<SchoolSAT>>()

    val schoolSAT: LiveData<Resource<SchoolSAT>>
        get() = _schoolSat


    fun getSchoolSAT(schoolId: String) {
        _schoolSat.value = Resource.loading()

        viewModelScope.launch {
            try {
                val resource = repository.getSchoolsSAT()
                if (resource.isSuccessful) {
                    val detail = resource.body()?.firstOrNull { it.id == schoolId }
                    _schoolSat.value =
                        if (detail != null) Resource.success(detail)
                        else Resource.error("No SAT scores found")
                } else {
                    _schoolSat.value = Resource.error(resource.message() ?: "Something went wrong")
                }
            } catch (e: Exception) {
                // handle no internet connection
                if (e is UnknownHostException) {
                    _schoolSat.value = Resource.error("No internet connection")
                } else {
                    _schoolSat.value = Resource.error(e.localizedMessage ?: "Something went wrong")

                }
            }
        }
    }
}