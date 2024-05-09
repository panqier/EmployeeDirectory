package com.qierpan.employeedirectory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qierpan.employeedirectory.ui.EmployeeDirectoryListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpTransaction()
    }

    private fun setUpTransaction() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, EmployeeDirectoryListFragment())
        ft.commit()
    }
}
