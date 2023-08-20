package com.example.myprofile.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("profile")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    @ColumnInfo(name = "name")
    var name:String ="",
    @ColumnInfo(name= "age")
    var age:Int = 0,
    @ColumnInfo(name ="address")
    var address:String = ""
)
