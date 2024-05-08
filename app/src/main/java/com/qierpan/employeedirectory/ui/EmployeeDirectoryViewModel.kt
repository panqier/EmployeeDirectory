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
import java.lang.Error

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
                        _uistate.value = _uistate.value.copy(
                            isLoading = false,
                            employeesList = employeesList
                        )
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

}