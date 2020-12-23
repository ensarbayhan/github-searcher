package com.eb.githubsearcher.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eb.githubsearcher.R
import com.eb.githubsearcher.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    companion object {
        const val DETAILS_TEXT_KEY = "DETAILS_TEXT_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.details)
        initViews()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun initViews() {
        val detailsText = intent.extras?.getString(DETAILS_TEXT_KEY) ?: ""
        if (detailsText.isEmpty()) {
            onBackPressed()
        } else {
            binding.detailsText.text = detailsText
        }
    }
}