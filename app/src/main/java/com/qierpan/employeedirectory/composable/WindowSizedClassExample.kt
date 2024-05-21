package com.qierpan.employeedirectory.composable


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp


/*class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}*/

@Composable
fun MyApp() {
    val windowSizeClass = rememberWindowSizeClass()

    MaterialTheme {
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> CompactLayout()
            WindowWidthSizeClass.Medium -> MediumLayout()
            WindowWidthSizeClass.Expanded -> ExpandedLayout()
        }
    }
}

@Composable
fun CompactLayout() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .border(2.dp, Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text("Compact Layout")
    }
}

@Composable
fun MediumLayout() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Medium Layout")
    }
}

@Composable
fun ExpandedLayout() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Expanded Layout")
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun rememberWindowSizeClass(): WindowSizeClass {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp
    val windowSize = DpSize(screenWidthDp, screenHeightDp)

    return remember(windowSize) {
        WindowSizeClass.calculateFromSize(windowSize)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}


