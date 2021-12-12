package com.aditya.calender.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aditya.calender.remote.Resource
import com.aditya.calender.remote.responses.createResponse.CreateResponseModel
import com.aditya.calender.remote.responses.getResponse.ResponseData
import com.aditya.calender.remote.responses.getResponse.Task
import com.aditya.calender.repositories.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(val appRepo: AppRepo) : ViewModel() {

    fun getData() : LiveData<Resource<ResponseData>>{
        return liveData(IO) {
            val data = appRepo.getResponseFromApi()
            emit(data)
        }
    }

    fun storeNewData(createResponseModel: CreateResponseModel){
        appRepo.storeTaskToAPI(createResponseModel)
    }

    fun deleteTaskFromAPI(task_id : Int){
        appRepo.deleteTaskFromAPI(task_id)
    }

}
