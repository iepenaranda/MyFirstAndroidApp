package com.example.mysidneyapprequiem

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    //TODO: ViewBinding, navigation, transform to fragments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(MAIN, "OnCreate")

        val navController = this.findNavController(R.id.myNavHostFragment)
    }

    companion object {
        private const val MAIN = "Main Activity"
        private const val TAG_LIST = "listPoi"
    }
}