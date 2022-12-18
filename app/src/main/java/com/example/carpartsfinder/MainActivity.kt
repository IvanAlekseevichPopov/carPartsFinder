package com.example.carpartsfinder

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.carpartsfinder.api.PartsApi
import com.example.carpartsfinder.api.RetrofitHelper
import com.example.carpartsfinder.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



//        binding.bCrashTest.setOnClickListener {
//            throw RuntimeException("Test Crash") // Force a crash
//        }

        //TODO Сделать кнопку для быстрой вставки из контекстного меню

        binding.bSearch.setOnClickListener {
            val quotesApi = RetrofitHelper.getInstance().create(PartsApi::class.java)

            GlobalScope.launch {
                val result = quotesApi.getParts()

                if(result != null) {

                    Log.d("1111", result.body().toString())
                }
            }
        }
    }
}