package com.qierpan.employeedirectory

import android.app.Application
import com.qierpan.employeedirectory.data.Employee
import com.qierpan.employeedirectory.data.EmployeesList
import com.qierpan.employeedirectory.ui.EmployeeDirectoryViewModel
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class EmployeeDirectoryViewModelTest {

    private lateinit var viewModel: EmployeeDirectoryViewModel
    private lateinit var mockApplication: Application

    @Before
    fun setUp() {
        // Create a mock Application context
        mockApplication = Mockito.mock(Application::class.java)
        // Initialize ViewModel with the mocked Application
        viewModel = EmployeeDirectoryViewModel(mockApplication)
    }

    @Test
    fun `isValidResponse returns true when all fields are valid`() {
        val employeesList = EmployeesList(
            listOf(
                Employee(
                    "1", "John Doe", "1234567890", "john.doe@example.com",
                    "Biography", "urlSmall", "urlLarge", "Engineering", "Full-Time"
                )
            )
        )

        val result = viewModel.isValidResponse(employeesList)
        assertTrue(result)
    }

    @Test
    fun `isValidResponse returns false when any field is null or empty`() {
        val employeesList = EmployeesList(
            listOf(
                Employee(
                    null, "John Doe", "1234567890", "john.doe@example.com",
                    "Biography", "urlSmall", "urlLarge", "Engineering", "Full-Time"
                ),
                Employee("2", "", "", "", "", "", "", "", "")
            )
        )

        val result = viewModel.isValidResponse(employeesList)
        assertFalse(result)
    }
}

