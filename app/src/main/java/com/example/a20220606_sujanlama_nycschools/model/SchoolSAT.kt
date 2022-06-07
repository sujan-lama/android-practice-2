package com.example.a20220606_sujanlama_nycschools.model

import com.squareup.moshi.Json

data class SchoolSAT(
    @Json(name = "dbn")
    val id: String,
    val num_of_sat_test_takers: String,
    val sat_critical_reading_avg_score: String,
    val sat_math_avg_score: String,
    val sat_writing_avg_score: String,
    val school_name: String
)