package com.pry.kspiguana

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pry.kspiguana.ui.theme.KsPIguanaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val sharedViewModel: viewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KsPIguanaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    sharedViewModel.getListPersons()
                    val listPersons by sharedViewModel.listPersons.observeAsState(initial = emptyList())
                    MyLazyColum(listPersons)
                }
            }
        }
    }
}

@Composable
fun MyLazyColum(listPersons: List<String>) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        listPersons.forEach {
            HelloWorld(it.toString())
        }
        val (checkedState, onStateChange) = remember { mutableStateOf(true) }
        MycheckBox(checkedState, onStateChange)
    }
}

@Composable
fun MycheckBox(checkedState: Boolean, onStateChange: (Boolean) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .toggleable(
                value = checkedState,
                onValueChange = { onStateChange(!checkedState) },
                role = Role.Checkbox
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = null // null recommended for accessibility with screenreaders
        )
        Text(
            text = "Option selection",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun HelloWorld(name: String) {
    val context = LocalContext.current
    Button(
        onClick = {
            Toast.makeText(context, name, Toast.LENGTH_LONG).show()
        },
        modifier = Modifier.padding(10.dp)
    ) {
        Text(text = stringResource(id = R.string.hello_worl)+"  $name", fontSize = 20.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    //HelloWorld(name = "juan")
    val (checkedState, onStateChange) = remember { mutableStateOf(true) }
    MycheckBox(checkedState, onStateChange)
}
