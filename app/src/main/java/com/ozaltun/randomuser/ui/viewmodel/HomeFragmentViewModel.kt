package com.ozaltun.randomuser.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozaltun.randomuser.data.model.UserModel
import com.ozaltun.randomuser.data.model.UserResponse
import com.ozaltun.randomuser.data.remote.ApiService
import com.ozaltun.randomuser.data.remote.ApiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentViewModel : ViewModel() {
    private lateinit var service: ApiService
    private val _userList = MutableLiveData<List<UserModel>>()
    val userList: LiveData<List<UserModel>>
        get() = _userList

    fun getUserList() = viewModelScope.launch(Dispatchers.IO) {
        service = ApiUtils.getApiService()
        //first choice
        /* service.getAllUsers().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                 val list = response.body()?.results
                 list?.let {
                     _userList.postValue(it)
                 }
             }
             override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                 Log.e("RETROFIT FAIL", t.message.toString())
             }
         })
         */
        //second choice
        try {
            val data = service.getAllUsers()
            data?.let {
                _userList.postValue(it.body()?.results)
            }

        } catch (e: Exception) {
            Log.e("RETROFIT ERROR", e.message.toString())
        }
    }
}