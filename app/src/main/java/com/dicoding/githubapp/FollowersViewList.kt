package com.dicoding.githubapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewList : ViewModel() {

    companion object {
        private const val TAG = "MainActivity"
    }

    val listFollower = MutableLiveData<ArrayList<ItemsItem>>()

    fun setListFollower(username: String) {
        ApiConfig.getApi()
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<ItemsItem>> {
                override fun onResponse(
                    call: Call<ArrayList<ItemsItem>>,
                    response: Response<ArrayList<ItemsItem>>
                ) {
                    if (response.isSuccessful) {
                        listFollower.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<ItemsItem>>, t: Throwable) {
                    Log.d(TAG, "Failure: ${t.message}")
                }

            })
    }

    fun getListFollower(): LiveData<ArrayList<ItemsItem>> {
        return listFollower
    }
}