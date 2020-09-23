package ru.geekbrains.kozirfm.kotlinproject.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FriendsList (
    @Expose
    val count: Int,
    @Expose
    @SerializedName("items")
    val friends: ArrayList<FriendModel>,
)