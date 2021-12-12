package com.aditya.calender.remote.responses.getResponse


import com.google.gson.annotations.SerializedName

data class GetTaskResponse(
    @SerializedName("user_id")
    var userId: Int
)