package com.qierpan.employeedirectory.ui

import com.qierpan.employeedirectory.data.EmployeesList


data class EmployeeListUiState(
    val isLoading: Boolean = true,
    val error: String = "",
    val employeesList: EmployeesList
)
