package com.dicoding.githubapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.firebase.firestore.auth.User

@Database(
    entities = [FavUser::class],
    version = 1
)
abstract class DatabaseUser: RoomDatabase() {
    companion object{
        var INSTANCE : DatabaseUser? = null

        fun getDatabase(context: Context): DatabaseUser?{
            if (INSTANCE==null){
                synchronized(DatabaseUser::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, DatabaseUser::class.java,"Database User").build()
                }
            }
            return INSTANCE
        }
    }
    abstract fun favUserDao(): FavUserDao
}