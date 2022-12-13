package com.ozaltun.randomuser.data.remote

import com.ozaltun.randomuser.util.Constants

class ApiUtils {
    companion object {
         fun getApiService(): ApiService {
            return RetrofitClient.getClient(Constants.BASE_URL).create(ApiService::class.java)
        }
    }
}

