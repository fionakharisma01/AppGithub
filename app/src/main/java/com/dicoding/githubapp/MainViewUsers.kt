package com.dicoding.githubapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewUsers : ViewModel(){
    companion object {
        private const val TAG = "MainActivity"
    }
    val listUsers = MutableLiveData<ArrayList<ItemsItem>>()
    fun setSearchUsers(query: String){

        ApiConfig.getApi()
            .getSearchUsers(query)
            .enqueue(object : Callback<GithubResponse>{
                override fun onResponse(
                    call: Call<GithubResponse>,
                    response: Response<GithubResponse>
                ) {
                    if (response.isSuccessful){
                        listUsers.value = response.body()?.items

                    }
                }

                override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }

            })
    }
    fun getSearchUsers(): LiveData<ArrayList<ItemsItem>>{
        return listUsers
    }
}