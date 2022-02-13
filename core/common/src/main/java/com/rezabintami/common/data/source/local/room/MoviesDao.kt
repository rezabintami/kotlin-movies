package com.rezabintami.common.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rezabintami.common.data.source.local.entity.MoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getAllMoviesFavorites(): Flow<List<MoviesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesToFavorites(movies: MoviesEntity)

    @Query("SELECT EXISTS(SELECT * FROM movies WHERE id = :id)")
    fun isFavorite(id: String): Flow<Boolean>

    @Delete
    fun removeMovies(movies: MoviesEntity)
}
