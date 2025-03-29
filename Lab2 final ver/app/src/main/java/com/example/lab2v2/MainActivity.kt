package com.example.lab2v2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab2v2.models.BitcoinViewModel
import com.example.lab2v2.ui.theme.Lab2V2Theme

class MainActivity : ComponentActivity() {
    private val bitcoinViewModel: BitcoinViewModel by viewModels()
    // val bitcoinViewModel = ViewModelProvider(this)[BitcoinViewModel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // bitcoinViewModel = ViewModelProvider(this).get(BitcoinViewModel::class.java)

        setContent {
            Lab2V2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "User Rislyn",
                        btcViewModel = bitcoinViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

}

@Composable
fun Greeting(name: String, btcViewModel: BitcoinViewModel, modifier: Modifier = Modifier) {
    val block by btcViewModel.bitcoinBlock.observeAsState()
    Column(modifier = modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Hello $name!",
            modifier = modifier,
            fontSize = 22.sp
        )
        Button(onClick = { btcViewModel.getOneBitcoin() }) {
            Text(text = "Get a bitcoin", fontSize = 32.sp)
        }
        if (block != null) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Bitcoin Information!", fontSize = 22.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "ID: ${block!!.id}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Height: ${block!!.height}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Version: ${block!!.version}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Size: ${block!!.size} bytes", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Weight: ${block!!.weight}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Nonce: ${block!!.nonce}", fontSize = 18.sp)
        }
    }
}
