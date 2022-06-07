package com.example.a20220606_sujanlama_nycschools.api

import com.example.a20220606_sujanlama_nycschools.constant.API
import com.example.a20220606_sujanlama_nycschools.model.School
import com.example.a20220606_sujanlama_nycschools.model.SchoolSAT
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(API.SCHOOL_LIST)
    suspend fun getSchoolsList(): Response<List<School>>
    @GET(API.SCHOOL_SAT_LIST)
    suspend fun getSchoolsSAT(): Response<List<SchoolSAT>>

}