package com.example.myprofile.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myprofile.data.model.Profile
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(profile: Profile)

    @Update
    suspend fun update(profile: Profile)

    @Delete
    suspend fun delete(profile: Profile)

    @Query("SELECT * FROM profile ORDER BY name ASC")
    fun getAllProfile(): Flow<List<Profile>>

    @Query("SELECT * FROM profile WHERE id = :id")
    fun getProfileById(id:Int): Flow<Profile>


}