package com.ozaltun.randomuser.data.remote

import com.ozaltun.randomuser.data.model.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/api/?results=50")
    suspend fun getAllUsers(): Response<UserResponse>
}





