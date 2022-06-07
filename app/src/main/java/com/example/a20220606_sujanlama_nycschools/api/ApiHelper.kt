package com.example.a20220606_sujanlama_nycschools.api

import com.example.a20220606_sujanlama_nycschools.model.School
import com.example.a20220606_sujanlama_nycschools.model.SchoolSAT
import retrofit2.Response

// show that only one instance of api service is used
interface ApiHelper {
    suspend fun getSchoolsList(): Response<List<School>>
    suspend fun getSchoolsSAT(): Response<List<SchoolSAT>>

}