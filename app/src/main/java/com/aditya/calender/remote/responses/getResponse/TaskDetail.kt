package com.aditya.calender.remote.responses.getResponse


import com.google.gson.annotations.SerializedName

data class TaskDetail(
    @SerializedName("date")
    var date: String? = "Adiya",
    @SerializedName("description")
    var description: String? = "Desc",
    @SerializedName("title")
    var title: String? = "title"
)