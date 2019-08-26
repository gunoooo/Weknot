package com.example.weknot_android.base

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface BaseDao<TE> {

    @Insert
    fun insert(entity: TE)

    @Update
    fun update(entity: TE)

    @Delete
    fun delete(entity: TE)

}
