package com.eb.githubsearcher.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eb.githubsearcher.databinding.ActivityMainBinding
import com.eb.githubsearcher.models.*
import com.eb.githubsearcher.util.hideKeyboard
import com.eb.githubsearcher.util.onActionDone
import com.eb.githubsearcher.util.printMessage
import com.eb.githubsearcher.util.toPrettyJson
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by ebayhan on 12/23/20.
 */
class MainActivity : AppCompatActivity(), ResultAdapter.ItemClickListener {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        binding.searchEditText.onActionDone {
            binding.sendButton.performClick()
        }

        binding.sendButton.setOnClickListener {
            binding.searchEditText.hideKeyboard()
            val searchText = binding.searchEditText.text.toString()
            if (searchText.isEmpty()) {
                printMessage("Please enter the word you want to search.")
                return@setOnClickListener
            }
            viewModel.sendSearchRequest(searchText)
        }

        viewModel.getResults().observe(this, {
            updateRecyclerView(it)
        })
    }

    private fun updateRecyclerView(results: List<BaseModel>) {
        binding.resultsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
            this.adapter = ResultAdapter(results, this@MainActivity).apply {
                notifyDataSetChanged()
            }
        }
    }

    override fun onRepositoryItemClicked(repository: Repository) {
        val detailsText = repository.toPrettyJson() ?: ""
        startActivity(Intent(this, DetailsActivity::class.java).apply {
            putExtras(bundleOf(DetailsActivity.DETAILS_TEXT_KEY to detailsText))
        })
    }

    override fun onUserItemClicked(user: User) {
        val detailsText = user.toPrettyJson() ?: ""
        startActivity(Intent(this, DetailsActivity::class.java).apply {
            putExtras(bundleOf(DetailsActivity.DETAILS_TEXT_KEY to detailsText))
        })
    }
}