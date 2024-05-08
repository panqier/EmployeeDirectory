package com.qierpan.employeedirectory.composable

import android.content.Context
import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.ui.input.pointer.pointerInput
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import coil.decode.ImageSource
import com.qierpan.employeedirectory.R
import com.qierpan.employeedirectory.data.Employee
import com.qierpan.employeedirectory.ui.EmployeeDirectoryViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EDListContent(
    employees: List<Employee>,
    viewModel: EmployeeDirectoryViewModel
) {

    val sortedEmployees = employees.sortedBy { it.full_name }
    // State for refreshing and item count
    var refreshing by remember { mutableStateOf(false) }
    var itemCount by remember { mutableStateOf(15) }

    // CoroutineScope for managing refresh operation
    val refreshScope = rememberCoroutineScope()

    // Function to simulate refresh operation
    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1500)
        viewModel.fetchEmployeeApiService()
        // Update itemCount and refreshing state accordingly
        itemCount += 5
        refreshing = false
    }

    // Remember PullRefreshState with refresh function
    val state = rememberPullRefreshState(refreshing, ::refresh)

    Box(Modifier.pullRefresh(state)) {
        LazyColumn(Modifier.fillMaxSize()) {
            // Show items only if not refreshing
            if (!refreshing) {
                items(sortedEmployees) { employee ->
                    ItemCard(employee = employee)
                }
            }
        }
        // PullRefreshIndicator at top center position
        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
    }
}


@Composable
fun ItemCard(
    employee: Employee,
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = employee.photo_url_small)
                        .apply {
                            crossfade(true)
                            placeholder(R.drawable.placeholder)
                            error(R.drawable.error)
                        }.build()
                ),
                contentDescription = null,
                modifier = Modifier.size(76.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = employee.full_name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(6.dp)
                    )
                Text(
                    text = "Department: " + employee.team,
                    modifier = Modifier.padding(6.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 6.dp, end = 0.dp, top = 0.dp, bottom = 6.dp)
                ) {
                    Text(text = employee.email_address)
                    IconButton(onClick = {
                        val clip = androidx.compose.ui.text.AnnotatedString(employee.email_address)
                        clipboardManager.setText(clip)
                        Toast.makeText(context, R.string.email_copy_string, Toast.LENGTH_SHORT).show()
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.copied),
                            modifier =
                                Modifier
                                    .size(15.dp),
                            contentDescription = stringResource(id = R.string.email_copy_icon)
                        )
                    }
                }
            }
        }
    }
}

