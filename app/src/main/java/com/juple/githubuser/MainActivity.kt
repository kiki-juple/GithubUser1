package com.juple.githubuser

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juple.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvUsers: RecyclerView
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvUsers = binding.rvUsers
        rvUsers.setHasFixedSize(true)

        list.addAll(listUsers)
        showRecyclerList()
    }

    private val listUsers: ArrayList<User>
        get() {
            val userName = resources.getStringArray(R.array.name)
            val userUserName = resources.getStringArray((R.array.username))
            val userAvatar = resources.obtainTypedArray(R.array.avatar)
            val userLocation = resources.getStringArray(R.array.location)
            val userRepository = resources.getStringArray(R.array.repository)
            val userCompany = resources.getStringArray(R.array.company)
            val userFollowers = resources.getStringArray(R.array.followers)
            val userFollowing = resources.getStringArray(R.array.following)
            val listUser = ArrayList<User>()
            for (i in userName.indices) {
                val user = User(
                    userName[i],
                    userUserName[i],
                    userAvatar.getResourceId(i, -1),
                    userLocation[i],
                    userRepository[i],
                    userCompany[i],
                    userFollowers[i],
                    userFollowing[i]
                )
                listUser.add(user)
            }
            userAvatar.recycle()
            return listUser
        }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvUsers.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvUsers.layoutManager = LinearLayoutManager(this)
        }
        val listUserAdapter = ListUserAdapter(list)
        rvUsers.adapter = listUserAdapter
        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(user: User) {
        val intentToDetail = Intent(this, DetailActivity::class.java)
        intentToDetail.putExtra(DetailActivity.EXTRA_USER, user)
        startActivity(intentToDetail)
    }
}