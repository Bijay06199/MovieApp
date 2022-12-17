package com.example.movielover.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    /**
     * Insert an object in the database.
     *
     * @param obj the object to be inserted.
     * @return id of inserted item
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T): Long

    /**
     * Insert an array of objects in the database.
     *
     * @param obj the objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: List<T>)

    /**
     * Update an object from the database.
     *
     * @param obj the object to be updated
     * @return number of row updated in database
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateWithReplace(obj: T): Int

    /**
     * Update an object from the database.
     *
     * @param obj the object to be updated
     * @return number of row updated in database
     */
    @Update
    fun update(obj: T): Int

    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     * @return number of row removed from the database.
     */
    @Delete
    fun delete(obj: T): Int
}