package com.rezabintami.common.data.source.local.room

import androidx.room.*
import com.rezabintami.common.data.source.local.entity.MoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getAllMoviesFavorites(): Flow<List<MoviesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesToFavorites(movies: MoviesEntity)

    @Delete
    fun removeMovies(movies: MoviesEntity)
}
