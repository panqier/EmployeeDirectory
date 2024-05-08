package com.qierpan.employeedirectory.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qierpan.employeedirectory.ui.EmployeeListUiState

@Composable
fun EDLoadingContent(
    uiState: EmployeeListUiState,
    dismiss: () -> Unit,
    navigateTo: (route: EmployeeDirectoryNavigationGraph) -> Unit
) {
    when {
        uiState.isLoading -> {
            EDLoadingView()
        }
        uiState.error.isNotEmpty() -> {
            EDErrorDialog(dismiss)
        }
        else -> {
            LaunchedEffect(Unit) {
                navigateTo(EmployeeDirectoryNavigationGraph.ListContent)
            }
        }
    }
}

@Composable
fun EDLoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator(
                modifier =
                Modifier
                    .padding(top = 5.dp, bottom = 5.dp)
                    .size(48.dp),
                strokeWidth = 3.dp,
                color = colorResource(id = com.qierpan.employeedirectory.R.color.blue_light),
            )
            Text(
                text = stringResource(id = com.qierpan.employeedirectory.R.string.loading),
                modifier = Modifier.padding(top = 15.dp),
                color = colorResource(id = com.qierpan.employeedirectory.R.color.white),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun EDErrorDialog(
    dismiss: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = { },
        dismissButton = {
            TextButton(onClick = dismiss) {
                Text(
                    text = stringResource(id = com.qierpan.employeedirectory.R.string.error_button),
                    textAlign = TextAlign.Center,
                    fontSize = 17.sp,
                )
            }
        },
        title = {
            Text(
                text = stringResource(id = com.qierpan.employeedirectory.R.string.error_title),
                fontSize = 17.sp,
            )
        },
    )
}
