package com.aditya.calender.remote.interfaces

import com.aditya.calender.remote.responses.ResponseModel
import com.aditya.calender.remote.responses.TaskModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface APIClient {

    //http://13.232.92.136:8084/api/storeCalendarTask
    @POST("storeCalendarTask")
    suspend fun storeTask(
        @Header("Authorization") token: String,
        @Body responseModel: ResponseModel
    ): ResponseModel


    //http://13.232.92.136:8084/api/getCalendarTaskList
    @GET("getCalendarTaskList")
    suspend fun getTask(
        @Header("Authorization") token: String
    ): ResponseModel

}