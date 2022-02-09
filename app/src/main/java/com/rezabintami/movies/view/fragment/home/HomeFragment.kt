package com.rezabintami.movies.view.fragment.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rezabintami.common.data.Resource
import com.rezabintami.movies.R
import com.rezabintami.movies.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding) { "Binding view must not be null" }
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModelObserver()
    }

    private fun initView(){
        binding.header.textViewHeader.text = getString(R.string.home)
    }

    @SuppressLint("SetTextI18n")
    private fun initViewModelObserver(){
        viewModel.moviesNowPlaying.observe(viewLifecycleOwner, { moviesNowPlaying ->
            if(moviesNowPlaying != null) {
                when(moviesNowPlaying){
                    is Resource.Loading -> binding.progressBarNowPlaying.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarNowPlaying.visibility = View.GONE
                    // Add Adapter
                    }
                    is Resource.Error -> {
                        binding.progressBarNowPlaying.visibility = View.GONE
                        binding.viewErrorNowPlaying.root.visibility = View.VISIBLE
                        binding.viewErrorNowPlaying.tvError.text = moviesNowPlaying.message ?: getString(R.string.something_wrong)

                    }
                }
            }

        })
    }
}