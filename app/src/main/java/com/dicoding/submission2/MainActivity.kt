package com.dicoding.submission2
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission2.databinding.ActivityMainBinding
import com.google.android.material.switchmaterial.SwitchMaterial


private val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var detail: SearchUser
    private lateinit var adapter: UserAdapter

    @SuppressLint("NotifyDataSetChanged")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)
        val pref = Setting.getInstance(dataStore)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        val mainUser = ViewModelProvider(this,SettingFactory(pref)).get(MainUser::class.java)
        mainUser.getThemeSettings().observe(this,){
                isDarkModeActive:Boolean ->
            if (isDarkModeActive){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            }else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }
        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            mainUser.saveThemeSetting(isChecked)
        }


        adapter.setOnItemClickCallback(object :UserAdapter.OnItemClickedCallback{
            override fun onItemClick(data: PostResponse) {
                Intent(this@MainActivity,DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME,data.login)
                    it.putExtra(DetailUserActivity.Extra_ID,data.id)
                    it.putExtra(DetailUserActivity.Extra_URL,data.avatar_url)
                    startActivity(it)
                }
            }
        })

        detail = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SearchUser::class.java)

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter
            btnSearch.setOnClickListener{
                searchUser()
            }
            query.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
        detail.getSearchUser().observe(this) {
            if (it != null) {
                adapter.setList(it)
            }

        }


        detail.loading.observe(this) {
            showLoading(it)
        }
    }
    private fun searchUser(){
        binding.apply {
            val text = query.text.toString()
                detail.setUser(text)
        }
    }

    private fun showLoading(isLoading: Boolean){
        if (isLoading) {
            binding.progres.visibility = View.VISIBLE
        } else {
            binding.progres.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favorite ->
                Intent(this ,FavoriteActivity2::class.java).also {
                    startActivity(it)
                }
        }
        return super.onOptionsItemSelected(item)
    }
}