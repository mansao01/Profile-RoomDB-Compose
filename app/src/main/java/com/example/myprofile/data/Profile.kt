package com.example.myprofile.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("profile")
data class Profile(
    @PrimaryKey
    val id:Int,
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name= "age")
    val age:Int,
    @ColumnInfo(name ="address")
    val address:String
)
