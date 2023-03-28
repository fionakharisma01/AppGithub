package com.dicoding.githubapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubapp.databinding.FragmentfollowBinding

class FragmentFollowers : Fragment(R.layout.fragmentfollow) {

    private var _binding: FragmentfollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var ViewModel: FollowersViewList
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentfollowBinding.bind(view)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapter
        }
        showLoading(true)
        ViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewList::class.java)
        ViewModel.setListFollower(username)
        ViewModel.getListFollower().observe(viewLifecycleOwner,{
            if (it!=null){
                adapter.setList(it)
                showLoading((false))
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(state: Boolean){
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}