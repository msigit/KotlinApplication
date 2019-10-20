package com.mcc.kotlinapplication.database.dao

import androidx.room.*
import com.mcc.kotlinapplication.model.FavouritePostModel

/**
 * Created by Sahidul Islam on 9/26/2019.
 */
@Dao
interface FavouritePostDao {

    @Insert
    fun insert(favouritePostModel: FavouritePostModel)

    @Delete
    fun delete(favouritePostModel: FavouritePostModel)

    @Update
    fun updateTodo(favouritePostModel: FavouritePostModel)

    @Query("SELECT * FROM FavouritePostModel ORDER BY autoId DESC")
    fun getAll(): List<FavouritePostModel>

    @Query("SELECT * FROM FavouritePostModel WHERE id = :postId LIMIT 1")
    fun getPost(postId: Int): List<FavouritePostModel>

    @Query("DELETE FROM FavouritePostModel WHERE id = :postId")
    fun deletePost(postId: Int)

}