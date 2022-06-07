package com.example.a20220606_sujanlama_nycschools.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class School(
    @Json(name = "dbn")
    val id: String,
    @Json(name = "school_name")
    val schoolName: String,
    @Json(name = "school_email")
    val schoolEmail: String?,
    @Json(name = "phone_number")
    val phoneNumber: String,
    @Json(name = "overview_paragraph")
    val overview: String
) : Parcelable