package com.example.myprofile.data

import com.example.myprofile.data.model.Profile
import kotlinx.coroutines.flow.Flow


interface MyProfileRepository {

    fun getAllData(): Flow<List<Profile>>
    fun getProfileById(id: Int): Flow<Profile>
    suspend fun getProfileByIdForEdit(id: Int):Profile
    suspend fun insert(profile: Profile)
    suspend fun delete(profile: Profile)
    suspend fun update(profile: Profile)


}