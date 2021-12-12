package com.aditya.calender.remote.responses.createResponse


import com.google.gson.annotations.SerializedName

data class CreateResponseModel(
    @SerializedName("task")
    val createTaskDetails: CreateTaskDetails,
    @SerializedName("user_id")
    val userId: Int
)