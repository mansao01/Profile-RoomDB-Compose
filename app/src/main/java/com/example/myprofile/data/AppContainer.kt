package com.example.myprofile.data

import android.content.Context

interface AppContainer {
    val myProfileRepository: MyProfileRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val myProfileRepository: MyProfileRepository by lazy {
        OfflineMyProfileRepository(
            MyProfileDatabase.getDatabase(context).profileDao()
        )
    }

}