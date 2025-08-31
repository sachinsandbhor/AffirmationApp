package com.shreejacreation.affirmationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AffirmationAppTheme
import com.shreejacreation.affirmationapp.data.Affirmation
import com.shreejacreation.affirmationapp.data.DataSource
import com.shreejacreation.affirmationapp.ui.AffirmationCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AffirmationAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AffirmationsApp()
                }
            }
        }
    }
}

@Composable
fun AffirmationsApp() {
    val affirmationList = DataSource().loadAffirmation()
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier.fillMaxSize().statusBarsPadding().
        padding(start = WindowInsets.safeDrawing.asPaddingValues().calculateStartPadding(layoutDirection),
            end = WindowInsets.safeDrawing.asPaddingValues().calculateEndPadding(layoutDirection))
    ){
        AffirmationList(affirmationList)
    }
}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            AffirmationTopBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it, modifier = modifier) {
            items(affirmationList) { affirmation ->
                AffirmationCard(
                    affirmation = affirmation,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AffirmationTopBar(modifier: Modifier= Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(50.dp)
                        .padding(16.dp),
                    contentDescription = null,
                    painter = painterResource(R.drawable.ic_launcher_background)
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AffirmationAppTheme(darkTheme = true) {
        AffirmationsApp()
    }
}
