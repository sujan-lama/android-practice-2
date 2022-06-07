package com.example.a20220606_sujanlama_nycschools.repository

import com.example.a20220606_sujanlama_nycschools.model.School
import com.example.a20220606_sujanlama_nycschools.model.SchoolSAT
import retrofit2.Response

interface Repository {
    suspend fun getSchoolsList(): Response<List<School>>

    suspend fun getSchoolsSAT(): Response<List<SchoolSAT>>
}