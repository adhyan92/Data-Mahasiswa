package com.example.datamahasiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.datamahasiswa.model.Mahasiswa
import com.example.datamahasiswa.ui.theme.DataMahasiswaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataMahasiswaTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { MahasiswaTopAppBar()}
                    ) { innerPadding ->
                    MahasiswaApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Mahasiswacard(mahasiswa: Mahasiswa, modifier: Modifier = Modifier) {
    Card {
        Column {
            Image(
                painter = painterResource(mahasiswa.imageResourceId),
                contentDescription = stringResource(mahasiswa.nameResourceId),
                modifier = modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop

            )
            Text(
                text = stringResource(mahasiswa.nameResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
fun MahasiswaApp(modifier: Modifier = Modifier) {
    MahasiswaList(
        mahasiswaList = MahasiswaSource().loadMahasiswa(),
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MahasiswaTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_mahasiswa),
                    contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.app_name))
            }
        },
        modifier = modifier
    )
}

@Composable
fun MahasiswaList(mahasiswaList: List<Mahasiswa>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(mahasiswaList) { mahasiswa ->
            Mahasiswacard(
                mahasiswa = mahasiswa,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DataMahasiswaTheme {
 //       Mahasiswacard(Mahasiswa(R.string.Adhyan, R.drawable.wanita))
        MahasiswaTopAppBar()
    }
}