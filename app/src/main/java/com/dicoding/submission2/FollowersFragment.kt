package com.dicoding.submission2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission2.databinding.FragmentFollowersBinding

class FollowersFragment : Fragment(R.layout.fragment_followers) {
    private var heybinding : FragmentFollowersBinding? = null
    private val binding get() = heybinding!!
    private lateinit var viewModel:FollowersUser
    private lateinit var  adapter: UserAdapter
    private lateinit var login :String


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arg = arguments
        login = arg?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        heybinding =FragmentFollowersBinding.bind(view)
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvFollowers.setHasFixedSize(true)
            rvFollowers.layoutManager = LinearLayoutManager(activity)
            rvFollowers.adapter = adapter

        }
        showLoading(true)
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FollowersUser::class.java)
        viewModel.setFollowers(login)
        viewModel.getFollowers().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)

            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        heybinding = null
    }
    private fun showLoading(isLoading: Boolean){
        if (isLoading) {
            binding.progres.visibility = View.VISIBLE
        } else {
            binding.progres.visibility = View.GONE
        }
    }

}