package com.laupdev.projectx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.laupdev.projectx.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        application.deleteDatabase("project_x_database")
    }
}