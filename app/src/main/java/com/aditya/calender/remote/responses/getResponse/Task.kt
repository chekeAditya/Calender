package com.aditya.calender.remote.responses.getResponse


import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("user_id")
    var taskId: Int,
    @SerializedName("task")
    var taskdetails: TaskDetail
)