package com.aditya.calender.remote.responses


import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("task")
    val taskModel: TaskModel,
    @SerializedName("user_id")
    val userId: Int
)