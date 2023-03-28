package com.dicoding.githubapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewUsers(application: Application) : AndroidViewModel(application) {

    val user = MutableLiveData<DetailUserResponse>()

    private var userDao: FavUserDao?
    private var userDb: DatabaseUser?

    init {
        userDb = DatabaseUser.getDatabase(application)
        userDao = userDb?.favUserDao()
    }

    fun setUserDetail(username: String) {
        ApiConfig.getApi()
            .getDetailUser(username)
            .enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.d(TAG, "Failure: ${t.message}")
                }

            })
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    fun getUserDetail(): LiveData<DetailUserResponse> {
        return user
    }

    fun addToFav(username: String, id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            var user = FavUser(
                username,
                id
            )
            userDao?.addToFavorite(user)
        }
    }
    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFromFav(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFromFav(id)
        }
    }
}