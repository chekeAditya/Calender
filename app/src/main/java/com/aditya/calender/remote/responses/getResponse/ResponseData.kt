package com.aditya.calender.remote.responses.getResponse


import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("tasks")
    var tasks: List<Task>
)