package com.dicoding.githubapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewList : ViewModel() {

    companion object {
        private const val TAG = "MainActivity"
    }

    val _listFollowing = MutableLiveData<ArrayList<ItemsItem>>()

    fun setListFollowing(username: String) {
        ApiConfig.getApi()
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<ItemsItem>> {
                override fun onResponse(
                    call: Call<ArrayList<ItemsItem>>,
                    response: Response<ArrayList<ItemsItem>>
                ) {
                    if (response.isSuccessful) {
                        _listFollowing.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<ItemsItem>>, t: Throwable) {
                    Log.d(TAG, "Failure: ${t.message}")
                }
            })
    }

    fun getListFollower(): LiveData<ArrayList<ItemsItem>> {
        return _listFollowing
    }
}