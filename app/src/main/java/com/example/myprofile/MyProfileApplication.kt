package com.example.myprofile

import android.app.Application
import com.example.myprofile.data.AppContainer
import com.example.myprofile.data.AppDataContainer

class MyProfileApplication:Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}