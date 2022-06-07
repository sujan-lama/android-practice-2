package com.example.a20220606_sujanlama_nycschools.utils

import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible(){
    this.visibility  = View.INVISIBLE
}