package com.qierpan.employeedirectory.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.qierpan.employeedirectory.data.EmployeesList
import com.qierpan.employeedirectory.service.GetEmployeeDirectoryService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EmployeeDirectoryViewModel(
    application: Application
): AndroidViewModel(application) {
    private val _uistate = MutableStateFlow(EmployeeListUiState(employeesList = EmployeesList(emptyList())))
    val uiState: StateFlow<EmployeeListUiState> = _uistate.asStateFlow()

    fun fetchEmployeeApiService() {
        viewModelScope.launch {
            _uistate.value = _uistate.value.copy(isLoading = true)
            GetEmployeeDirectoryService.getEmployeeListService(
                object :
                    GetEmployeeDirectoryService.EmployeeDirectoryService {
                    override fun onResponse(employeesList: EmployeesList) {
                        if (isValidResponse(employeesList)) {
                            _uistate.value = _uistate.value.copy(
                                isLoading = false,
                                employeesList = employeesList
                            )
                        } else {
                            _uistate.value = _uistate.value.copy(
                                isLoading = false,
                                error = "Response contains missing fields"
                            )
                        }
                    }

                    override fun onFailure(error: Error) {
                        _uistate.value = _uistate.value.copy(
                            isLoading = false,
                            error = error.message ?: "Unknown error"
                        )
                    }

                }
            )
        }
    }

    fun isValidResponse(employeesList: EmployeesList): Boolean {
        return employeesList.employees.all { employee ->
            employee.uuid?.isNotEmpty() ?: false &&
                    employee.full_name?.isNotEmpty() ?: false &&
                    employee.phone_number?.isNotEmpty() ?: false &&
                    employee.email_address?.isNotEmpty() ?: false &&
                    employee.biography?.isNotEmpty() ?: false &&
                    employee.photo_url_small?.isNotEmpty() ?: false &&
                    employee.photo_url_large?.isNotEmpty() ?: false &&
                    employee.team?.isNotEmpty() ?: false &&
                    employee.employee_type?.isNotEmpty() ?: false
        }
    }


}