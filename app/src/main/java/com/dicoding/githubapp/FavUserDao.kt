package com.dicoding.githubapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavUserDao {
    @Insert
    suspend fun addToFavorite(favUser: FavUser)

    @Query("SELECT * FROM fav_user")
    fun getFavUser(): LiveData<List<FavUser>>

    @Query("SELECT count(*) FROM fav_user WHERE fav_user.id= :id")
    suspend fun checkUser(id: Int): Int

    @Query("DELETE FROM fav_user WHERE fav_user.id = :id")
    suspend fun removeFromFav(id: Int): Int
}