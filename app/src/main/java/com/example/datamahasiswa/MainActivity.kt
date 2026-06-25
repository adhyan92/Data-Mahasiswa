package com.example.datamahasiswa

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
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
fun MahasiswaDetail(
    @StringRes mahasiswaEmail: Int,
    modifier: Modifier = Modifier
){
    Column(modifier = Modifier){
        Text(
            text = stringResource(R.string.email_text),
            style = MaterialTheme.typography.labelSmall,
        )
        Text(
            text = stringResource(mahasiswaEmail),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun Mahasiswacard(mahasiswa: Mahasiswa, modifier: Modifier = Modifier) {
    var expended by remember { mutableStateOf(false) }
    Card {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        ){
            Image(
                painter = painterResource(mahasiswa.imageResourceId),
                contentDescription = stringResource(mahasiswa.nameResourceId),
                modifier = modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.height_image)),
                contentScale = ContentScale.Crop

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ){
                Text(
                    text = stringResource(mahasiswa.nameResourceId),
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                    style = MaterialTheme.typography.displaySmall
                )
                Spacer(modifier = Modifier.weight(1f))
                MahasiswaItemButton(
                    expended = expended,
                    onClick = { expended = !expended }
                )
            }
            if (expended){
                MahasiswaDetail(
                    mahasiswa.emailResourceId,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
                )
            }
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
                Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
                Text(
                    stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge)
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
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Composable
fun MahasiswaItemButton(
    expended: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    IconButton(
        onClick = onClick,
        modifier = Modifier
    ){
        Icon(
            imageVector = if (expended)Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DataMahasiswaTheme (darkTheme = true){
        MahasiswaApp()
 //       Mahasiswacard(Mahasiswa(R.string.Adhyan, R.drawable.wanita))
        MahasiswaTopAppBar()
    }
}