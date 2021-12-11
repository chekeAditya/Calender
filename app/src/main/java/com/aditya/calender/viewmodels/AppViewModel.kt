package com.aditya.calender.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aditya.calender.remote.Resource
import com.aditya.calender.remote.responses.ResponseModel
import com.aditya.calender.remote.responses.TaskModel
import com.aditya.calender.repositories.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(val appRepo: AppRepo) : ViewModel() {

    fun getTaskFromApi(): LiveData<TaskModel> {
        return liveData(Dispatchers.IO) {
            val data = appRepo.getResponseFromAPI()
        }
    }

    fun createTask(responseModel: ResponseModel) {
        appRepo.createTask(responseModel)
    }

}
