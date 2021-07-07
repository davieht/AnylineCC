package com.example.pagingexample.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.pagingexample.data.UserRepo
import com.example.pagingexample.databinding.FragmentDetailBinding
import org.koin.android.ext.android.inject

private const val ARG_USER_ID = "userId"

class DetailFragment : Fragment() {

    private val repo: UserRepo by inject()
    private val viewModel: DetailViewModel by viewModels { DetailViewModelFactory(this, repo) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel.setUserId(it.getString(ARG_USER_ID))
        }
    }
}