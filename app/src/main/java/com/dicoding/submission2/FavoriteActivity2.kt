package com.dicoding.submission2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission2.databinding.ActivityFavoriteBinding

class  FavoriteActivity2 : AppCompatActivity() {
    private lateinit var binding:ActivityFavoriteBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavoriteUser



    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoriteUser::class.java)
        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickedCallback{
            override fun onItemClick(data: PostResponse) {
                Intent (this@FavoriteActivity2, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME,data.login)
                    it.putExtra(DetailUserActivity.Extra_ID,data.id)
                    it.putExtra(DetailUserActivity.Extra_URL,data.avatar_url)
                    startActivity(it)
                }
            }
        })

        binding.apply {
            rvfavorite.setHasFixedSize(true)
            rvfavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity2)
            rvfavorite.adapter = adapter
        }
        viewModel.getFavorite()?.observe(this) {
            if (it != null) {
                val list_users = mapList(it)
                adapter.setList(list_users)
            }
        }

    }

    private fun mapList(list: List<FavoriteData>): ArrayList<PostResponse> {
        val listUsers = ArrayList<PostResponse>()
        for (data in list){
            val userMap = PostResponse(data.login, data.id, data.avatar_url)
            listUsers.add(userMap)
        }
        return  listUsers

    }

}