package com.rezabintami.common.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rezabintami.common.data.source.local.entity.MoviesEntity

@Database(entities = [MoviesEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

}