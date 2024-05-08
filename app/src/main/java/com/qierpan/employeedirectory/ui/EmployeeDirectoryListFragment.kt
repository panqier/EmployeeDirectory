package com.qierpan.employeedirectory.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.qierpan.employeedirectory.composable.SetUpEmployeeDirectoryNavigationGraph
import com.qierpan.employeedirectory.databinding.EmployeeDirectoryListFragmentBinding
import kotlinx.coroutines.launch

class EmployeeDirectoryListFragment: Fragment() {
    private lateinit var binding: EmployeeDirectoryListFragmentBinding
    private lateinit var sharedViewModel: EmployeeDirectoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity())[EmployeeDirectoryViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EmployeeDirectoryListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setUpRefresh()
        sharedViewModel.fetchEmployeeApiService()
        binding.composeView.setContent {
            val navController = rememberNavController()
            navController.enableOnBackPressed(false)
            SetUpEmployeeDirectoryNavigationGraph(
                viewModel = sharedViewModel,
                navController = navController,
                dismiss = {parentFragmentManager.popBackStack()}
            )
        }
    }

    /*private fun setUpRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            sharedViewModel.fetchEmployeeApiService()
        }
        viewLifecycleOwner.lifecycleScope.launch {
            sharedViewModel.uiState.collect { uiState ->
                if (!uiState.isLoading) {
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        }
    }*/
}