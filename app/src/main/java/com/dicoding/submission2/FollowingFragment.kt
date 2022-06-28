package com.dicoding.submission2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dicoding.submission2.databinding.FragmentFollowingBinding

class FollowingFragment : Fragment(R.layout.fragment_followers) {
    private val binding : FragmentFollowingBinding by viewBinding ()
    private lateinit var viewModel:FollowingUser
    private lateinit var  adapter: UserAdapter
    private lateinit var login :String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentFollowingBinding.inflate (inflater, container, false).root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arg = arguments
        login = arg?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        adapter = UserAdapter()


        binding.apply {
            rvFollowing.setHasFixedSize(true)
            rvFollowing.layoutManager = LinearLayoutManager(activity)
            rvFollowing.adapter = adapter

        }
        showLoading(true)
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FollowingUser::class.java)
        viewModel.setFollowing(login)
        viewModel.getFollowing().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }

        }

    }

    private fun showLoading(isLoading: Boolean){
        if (isLoading) {
            binding.progres.visibility = View.VISIBLE
        } else {
            binding.progres.visibility = View.GONE
        }
    }

}