package com.example.myprofile.data

import android.content.ContentValues
import android.content.Context
import androidx.room.Database
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myprofile.data.model.Profile

@Database(entities = [Profile::class], version = 1, exportSchema = false)
abstract class MyProfileDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao

    companion object {
        @Volatile
        private var INSTANCE: MyProfileDatabase? = null

        fun getDatabase(context: Context): MyProfileDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, MyProfileDatabase::class.java, "profile_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
                    .also { INSTANCE = it }
            }
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val initialProfiles = listOf(
                    Profile(1, "Mansao", 25, "Kudus"),
                    Profile(2, "Hu Tao", 30, "Liuye")
                )
                insertInitialData(db, initialProfiles)
            }
        }

        private fun insertInitialData(
            db: SupportSQLiteDatabase,
            profiles: List<Profile>
        ) {
            db.beginTransaction()
            try {
                for (profile in profiles) {
                    val values = ContentValues()
                    values.put("id", profile.id)
                    values.put("name", profile.name)
                    values.put("age", profile.age)
                    values.put("address", profile.address)
                    db.insert("profile", OnConflictStrategy.IGNORE, values)
                }
                db.setTransactionSuccessful()
            } finally {
                db.endTransaction()
            }
        }
    }
}
