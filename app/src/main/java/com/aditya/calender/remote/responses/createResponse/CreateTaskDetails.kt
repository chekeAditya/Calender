package com.aditya.calender.remote.responses.createResponse


import com.google.gson.annotations.SerializedName

data class CreateTaskDetails(
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String
)