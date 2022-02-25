package com.tandem.codingchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tandem.codingchallenge.data.MyApi
import com.tandem.codingchallenge.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var communitiesViewModel: CommunitiesViewModel
    lateinit var communitiesAdapter: CommunitiesAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        title = "Community"

        setupViewModel()
        setupList()
        setupView()
    }

    private fun setupViewModel() {
        val factory = CommunitiesViewModelFactory(MyApi())
        communitiesViewModel = ViewModelProvider(this, factory).get(CommunitiesViewModel::class.java)
    }

    private fun setupList() {
        communitiesAdapter = CommunitiesAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = communitiesAdapter.withLoadStateHeaderAndFooter(
                header = CommunitiesLoadStateAdapter { communitiesAdapter.retry() },
                footer = CommunitiesLoadStateAdapter { communitiesAdapter.retry() }
            )
            setHasFixedSize(true)
        }

    }

    private fun setupView() {
        lifecycleScope.launch {
            communitiesViewModel.passengers.collectLatest { pagedData ->
                communitiesAdapter.submitData(pagedData)
            }
        }
    }
}