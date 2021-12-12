package com.aditya.calender.remote.interfaces

import com.aditya.calender.remote.responses.createResponse.CreateResponseModel
import com.aditya.calender.remote.responses.getResponse.GetTaskResponse
import com.aditya.calender.remote.responses.getResponse.ResponseData
import com.aditya.calender.remote.responses.getResponse.Task
import retrofit2.http.*

interface APIClient {


    //http://13.232.92.136:8084/api/getCalendarTaskList
    @POST("getCalendarTaskList")
    suspend fun getResponseFromAPI(
        @Body getTaskResponse: GetTaskResponse
    ): ResponseData

    //http://13.232.92.136:8084/api/storeCalendarTask
    @POST("storeCalendarTask")
    suspend fun storeResponseToAPI(
        @Header("Authorization") token: String,
        @Body createResponseModel: CreateResponseModel
    ): CreateResponseModel

    @Multipart
    @POST("deleteCalendarTask")
    suspend fun deleteTaskFromAPI(
        @Part("user_id") user_id: Int,
        @Part("task_id") task_id: Int,
    ): ResponseData
}