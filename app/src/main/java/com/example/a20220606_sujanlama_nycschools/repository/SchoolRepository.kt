package com.example.a20220606_sujanlama_nycschools.repository

import com.example.a20220606_sujanlama_nycschools.api.ApiHelper
import com.example.a20220606_sujanlama_nycschools.model.School
import com.example.a20220606_sujanlama_nycschools.model.SchoolSAT
import retrofit2.Response
import javax.inject.Inject



class SchoolRepository @Inject constructor(private val apiHelper: ApiHelper) : Repository {

    override suspend fun getSchoolsList(): Response<List<School>> = apiHelper.getSchoolsList()

    override suspend fun getSchoolsSAT(): Response<List<SchoolSAT>> = apiHelper.getSchoolsSAT()
}