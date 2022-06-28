package com.dicoding.submission2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.submission2.databinding.ActivityDetailUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUser
    private lateinit var rofiqyudha:String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        showLoading(true)
        setContentView(binding.root)

        val login = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(Extra_ID,0)
        val avatar = intent.getStringExtra(Extra_URL)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME,login)


        viewModel = ViewModelProvider(this).get(DetailUser::class.java)
        viewModel.setUserDetail(login.toString())
        viewModel.getUserDetail().observe(this) {
            if (it != null) {
                binding.apply {
                    tvusername.text = it.login
                    Glide.with(this@DetailUserActivity).load(it.avatar_url).centerCrop().into(avatarurl)
                    name.text = it.name
                    company.text = it.company
                    blog.text = it.blog
                    location.text = it.location
                    publicRepos.text = it.public_repos + "Repositories"
                    publicGeits.text = it.public_geits + "Save"
                    follow.text = it.followers + "Followers"
                    following.text = it.following + "Following"
                    showLoading(false)

                }
            }
        }

        var isCheck =false
        CoroutineScope(Dispatchers.IO).launch {
            val count =viewModel.check(id)
            withContext(Dispatchers.Main){
                if (count != null){
                    if (count>0){
                        binding.toggle.isChecked=true
                        isCheck =true

                    }else{
                        binding.toggle.isChecked=false
                        isCheck =false
                    }
                }
            }
        }
        binding.toggle.setOnClickListener{
            isCheck =!isCheck
            if (isCheck){
                if (login != null) {
                    if (avatar != null) {
                        viewModel.addToFavorite(login,id,avatar)
                    }
                }

            }else{
                viewModel.removeFavorite(id)
            }
            binding.toggle.isChecked = isCheck
        }

        val followersFollowingAdapter = FollowersFollowingAdapter(this, supportFragmentManager,bundle)
        binding.apply {
            viewPager.adapter = followersFollowingAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }
    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val Extra_ID ="extra_ID"
        const val Extra_URL = "extra_url"


    }
    private fun showLoading(isLoading: Boolean){
        if (isLoading) {
            binding.progres.visibility = View.VISIBLE
        } else {
            binding.progres.visibility = View.GONE
        }
    }
}