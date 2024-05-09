package com.qierpan.employeedirectory.service

import com.qierpan.employeedirectory.data.Constants.EMPLOYEE_LIST_ENDPOINT
import com.qierpan.employeedirectory.data.EmployeesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetEmployeeDirectoryService {
    companion object {
        fun getEmployeeListService(listener: EmployeeDirectoryService) {
            val employeeApiService =
                RetrofitManager.employeeDirectoryService.create(EmployeeApiService::class.java)
            val call: Call<EmployeesList> = employeeApiService.getEmployees(EMPLOYEE_LIST_ENDPOINT)
            call.enqueue(object : Callback<EmployeesList> {
                override fun onResponse(
                    call: Call<EmployeesList>,
                    response: Response<EmployeesList>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        listener.onResponse(response.body()!!)
                    } else {
                        onFailure(call, Throwable())
                    }
                }

                override fun onFailure(call: Call<EmployeesList>, t: Throwable) {
                    listener.onFailure(Error(t.message))
                }

            })


        }
    }

    interface EmployeeDirectoryService {
        fun onResponse(employeesList: EmployeesList)

        fun onFailure(error: Error)
    }
}
