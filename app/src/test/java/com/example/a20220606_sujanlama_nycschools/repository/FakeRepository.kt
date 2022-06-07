package com.example.a20220606_sujanlama_nycschools.repository

import com.example.a20220606_sujanlama_nycschools.model.School
import com.example.a20220606_sujanlama_nycschools.model.SchoolSAT
import retrofit2.Response

class FakeRepository : Repository {
    private val fakeSchoolList = listOf(
        School("123", "test", "a@a.com", "", ""),
        School("124", "test", "a@a.com", "", "")
    )

    private val fakeSchoolSATList = listOf(
        SchoolSAT("123", "11", "12", "13", "14", "15")
    )

    override suspend fun getSchoolsList(): Response<List<School>> {
        return Response.success(fakeSchoolList)
    }

    override suspend fun getSchoolsSAT(): Response<List<SchoolSAT>> {
        return Response.success(fakeSchoolSATList)

    }
}