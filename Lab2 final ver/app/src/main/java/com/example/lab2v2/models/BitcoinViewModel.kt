package com.example.lab2v2.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab2v2.services.GetBitcoin
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class BitcoinViewModel: ViewModel() {

    private lateinit var getBitcoinInterface: GetBitcoin

    private var _bitcoinBlock = MutableLiveData<BitcoinContainer?>() // ("None yet")
    val bitcoinBlock : LiveData<BitcoinContainer?> = _bitcoinBlock

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl("https://blockstream.info/") // old: .baseUrl("https://10.0.2.2:3003/")
            .build()
        getBitcoinInterface = retrofit.create(GetBitcoin::class.java)
    }

    fun getOneBitcoin() {
        viewModelScope.launch {
            val bitcoin: BitcoinContainer = getBitcoinInterface.getBitcoin()
            Log.i("RFR", "< ${bitcoin.toString()} >")
            _bitcoinBlock.value = bitcoin
        }
    }

}