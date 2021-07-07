package com.example.anylinecc.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import com.example.anylinecc.R
import com.example.anylinecc.utils.UserComparator
import com.example.anylinecc.data.UserRepo
import com.example.anylinecc.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    private val repo: UserRepo by inject()
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(this, repo) }
    private lateinit var binding: FragmentMainBinding
    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagingAdapter = UserPagingAdapter(UserComparator).apply {
            setOnItemClickListener {
                view.findNavController().navigate(
                    R.id.action_pagingFragment_to_detailFragment,
                    bundleOf("userId" to it.id)
                )
            }
            setOnUpdateListener {
                binding.count = it
            }
        }

        pagingList.adapter = pagingAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            pagingAdapter.loadStateFlow
                .collectLatest {
                    binding.loading = it.refresh is LoadState.Loading
                }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchJob?.cancel()
                searchJob = viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.getUsers(query)?.collectLatest { pagingData ->
                        pagingAdapter.submitData(pagingData)
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}