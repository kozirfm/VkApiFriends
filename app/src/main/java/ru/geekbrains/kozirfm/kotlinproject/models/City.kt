package ru.geekbrains.kozirfm.kotlinproject.models

import com.google.gson.annotations.Expose

data class City(
    @Expose
    val id: Int,
    @Expose
    val title: String)