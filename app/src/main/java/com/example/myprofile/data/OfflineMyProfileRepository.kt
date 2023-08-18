package com.example.myprofile.data

import kotlinx.coroutines.flow.Flow

class OfflineMyProfileRepository(private val profileDao: ProfileDao) : MyProfileRepository {
    override fun getAllData(): Flow<List<Profile>> = profileDao.getAllProfile()

    override fun getProfileById(id: Int): Flow<Profile> = profileDao.getProfileById(id)

    override suspend fun insert(profile: Profile) = profileDao.insert(profile)

    override suspend fun delete(profile: Profile) = profileDao.delete(profile)

    override suspend fun update(profile: Profile) = profileDao.update(profile)
}