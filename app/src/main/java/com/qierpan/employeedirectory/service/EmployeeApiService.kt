package com.qierpan.employeedirectory.service

import com.qierpan.employeedirectory.data.EmployeesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface EmployeeApiService {
    @GET
    fun getEmployees(@Url endpoint: String): Call<EmployeesList>
}
