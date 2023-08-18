package com.example.myprofile.data

import kotlinx.coroutines.flow.Flow


interface MyProfileRepository {

    fun getAllData(): Flow<List<Profile>>
    fun getProfileById(id: Int): Flow<Profile>
    suspend fun insert(profile: Profile)
    suspend fun delete(profile: Profile)
    suspend fun update(profile: Profile)


}