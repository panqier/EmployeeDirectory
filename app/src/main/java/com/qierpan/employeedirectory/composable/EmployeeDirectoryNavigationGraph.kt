package com.qierpan.employeedirectory.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.qierpan.employeedirectory.ui.EmployeeDirectoryViewModel

const val ED_LOADING_CONTENT = "employee_directory_loading_content"
const val ED_LIST_CONTENT = "employee_directory_list_content"

sealed class EmployeeDirectoryNavigationGraph(val route: String) {
    object LoadingContent :
            EmployeeDirectoryNavigationGraph(route = ED_LOADING_CONTENT)

    object ListContent :
            EmployeeDirectoryNavigationGraph(route = ED_LIST_CONTENT)
}

@Composable
fun SetUpEmployeeDirectoryNavigationGraph(
    viewModel: EmployeeDirectoryViewModel,
    navController: NavHostController,
    dismiss: () -> Unit = {}
){
    val uiState = viewModel.uiState.collectAsState().value
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavHost(
            navController = navController,
            startDestination = EmployeeDirectoryNavigationGraph.LoadingContent.route
        ) {
            composable(
                route = EmployeeDirectoryNavigationGraph.LoadingContent.route,
            ) {
                EnterAnimation {
                    EDLoadingContent(
                        uiState = uiState,
                        dismiss = dismiss,
                        navigateTo = {
                            navController.navigate(it.route)
                        },
                    )
                }
            }

            composable(
                route = EmployeeDirectoryNavigationGraph.ListContent.route,
            ) {
                EnterAnimation {
                    EDListContent(uiState.employeesList.employees, viewModel = viewModel)
                }
            }
        }

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EnterAnimation(content: @Composable AnimatedVisibilityScope.() -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter =
            slideInHorizontally(
                initialOffsetX = { it }, animationSpec = tween(durationMillis = 500),
            ) + fadeIn(),
        exit =
            slideOutHorizontally(
                targetOffsetX = { it }, animationSpec = tween(durationMillis = 500),
            ) + fadeOut(),
        content = content,
    )
}