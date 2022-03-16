package com.juple.githubuser

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.juple.githubuser.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Detail User"
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        val image = user.avatar
        val name = user.name.toString()
        val userName = user.userName.toString()
        val location = "Location: ${user.location.toString()}"
        val repo = user.repository.toString()
        val company = "Company: ${user.company.toString()}"
        val followers = user.followers.toString()
        val following = user.following.toString()

        Glide.with(this)
            .load(image)
            .circleCrop()
            .into(binding.detailAvatar)
        binding.apply {
            tvDetailName.text = name
            tvDetailUsername.text = userName
            tvDetailLocation.text = location
            tvRepo.text = repo
            tvDetailCompany.text = company
            tvFollowers.text = followers
            tvFollowing.text = following
        }
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}