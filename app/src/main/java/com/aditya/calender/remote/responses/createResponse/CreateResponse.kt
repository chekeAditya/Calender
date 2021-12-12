package com.aditya.calender.remote.responses.createResponse


import com.google.gson.annotations.SerializedName

data class CreateResponse(
    @SerializedName("task")
    val task: Task,
    @SerializedName("user_id")
    val userId: Int
)