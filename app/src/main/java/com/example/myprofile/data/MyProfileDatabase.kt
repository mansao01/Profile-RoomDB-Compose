package com.example.myprofile.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Profile::class], version = 1, exportSchema = false)
sealed class MyProfileDatabase:RoomDatabase() {
    abstract fun profileDao():ProfileDao

    companion object{
        @Volatile
        private var INSTANCE: MyProfileDatabase? = null

        fun getDatabase(context: Context): MyProfileDatabase{
            return INSTANCE?: synchronized(this){
                Room.databaseBuilder(context, MyProfileDatabase::class.java, "profile_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}