package com.example.taskapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int?= null,
    var title: String?= null,
    var desc: String? = null
):java.io.Serializable
