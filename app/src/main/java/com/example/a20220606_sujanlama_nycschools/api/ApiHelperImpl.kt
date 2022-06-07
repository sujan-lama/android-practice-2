package com.example.a20220606_sujanlama_nycschools.api

import com.example.a20220606_sujanlama_nycschools.model.School
import com.example.a20220606_sujanlama_nycschools.model.SchoolSAT
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getSchoolsList(): Response<List<School>> {
        return apiService.getSchoolsList()
    }

    override suspend fun getSchoolsSAT(): Response<List<SchoolSAT>> {
        return apiService.getSchoolsSAT()
    }
}