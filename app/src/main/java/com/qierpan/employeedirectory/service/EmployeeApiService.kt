package com.qierpan.employeedirectory.service

import com.qierpan.employeedirectory.data.EmployeesList
import retrofit2.Call
import retrofit2.http.GET

interface EmployeeApiService {
    @GET("employees.json")
    fun getEmployees(): Call<EmployeesList>
}