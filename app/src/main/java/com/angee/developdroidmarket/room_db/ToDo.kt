package com.angee.developdroidmarket.room_db

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.math.truncate


@Entity
data class ToDo (
    @PrimaryKey(autoGenerate= true) val id:Int,
    val referencia: String?,
    val marca: String?,
    val precio: String?,
    )
