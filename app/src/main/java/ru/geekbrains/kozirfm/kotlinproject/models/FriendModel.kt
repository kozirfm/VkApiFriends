package ru.geekbrains.kozirfm.kotlinproject.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FriendModel(
    @Expose
    @SerializedName("first_name")
    val name: String,
    @Expose
    @SerializedName("last_name")
    val surname: String,
    val city: String?,
    @Expose
    @SerializedName("photo_100")
    val avatar: String?,
    @Expose
    @SerializedName("online")
    val isOnline: Int,
    @Expose
    @SerializedName("city")
    val mCity: City?
)
