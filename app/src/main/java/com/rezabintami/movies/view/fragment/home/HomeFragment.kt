package com.rezabintami.movies.view.fragment.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rezabintami.common.data.Resource
import com.rezabintami.common.ui.MoviesAdapter
import com.rezabintami.movies.R
import com.rezabintami.movies.databinding.FragmentHomeBinding
import com.rezabintami.movies.view.activity.detailmovies.DetailMoviesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding) { "Binding view must not be null" }
    private val viewModel: HomeViewModel by activityViewModels()
    private var adapterMoviesNowPlaying: MoviesAdapter? = null
    private var adapterMoviesPopular: MoviesAdapter? = null
    private var adapterMoviesTopRated: MoviesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initClickListener()
        initViewModelObserver()
    }

    private fun initView(){
        binding.header.textViewHeader.text = getString(R.string.home)
        adapterMoviesNowPlaying = MoviesAdapter()
        adapterMoviesPopular = MoviesAdapter()
        adapterMoviesTopRated = MoviesAdapter()

        binding.rvNowPlaying.adapter = adapterMoviesNowPlaying
        binding.rvPopular.adapter = adapterMoviesPopular
        binding.rvTopRated.adapter = adapterMoviesTopRated
    }

    private fun initClickListener(){
        adapterMoviesNowPlaying?.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailMoviesActivity::class.java)
            intent.putExtra(DetailMoviesActivity.MOVIE_ID, selectedData.id)
            startActivity(intent)
        }

        adapterMoviesPopular?.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailMoviesActivity::class.java)
            intent.putExtra(DetailMoviesActivity.MOVIE_ID, selectedData.id)
            startActivity(intent)
        }

        adapterMoviesTopRated?.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailMoviesActivity::class.java)
            intent.putExtra(DetailMoviesActivity.MOVIE_ID, selectedData.id)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViewModelObserver(){

        // Now Playing
        viewModel.moviesNowPlaying.observe(viewLifecycleOwner, { moviesNowPlaying ->
            if(moviesNowPlaying != null) {
                when(moviesNowPlaying){
                    is Resource.Loading -> binding.progressBarNowPlaying.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarNowPlaying.visibility = View.GONE
                    // Add Adapter
                        adapterMoviesNowPlaying?.setData(moviesNowPlaying.data)
                    }
                    is Resource.Error -> {
                        binding.progressBarNowPlaying.visibility = View.GONE
                        binding.viewErrorNowPlaying.root.visibility = View.VISIBLE
                        binding.viewErrorNowPlaying.tvError.text = moviesNowPlaying.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        // Top Rated
        viewModel.moviesTopRated.observe(viewLifecycleOwner, { moviesTopRated ->
            if(moviesTopRated != null) {
                when(moviesTopRated){
                    is Resource.Loading -> binding.progressBarTopRated.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarTopRated.visibility = View.GONE
                        // Add Adapter
                        adapterMoviesTopRated?.setData(moviesTopRated.data)
                    }
                    is Resource.Error -> {
                        binding.progressBarTopRated.visibility = View.GONE
                        binding.viewErrorTopRated.root.visibility = View.VISIBLE
                        binding.viewErrorTopRated.tvError.text = moviesTopRated.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        //Popular
        viewModel.moviesPopular.observe(viewLifecycleOwner, { moviesPopular ->
            if(moviesPopular != null) {
                when(moviesPopular){
                    is Resource.Loading -> binding.progressBarPopular.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarPopular.visibility = View.GONE
                        // Add Adapter
                        adapterMoviesPopular?.setData(moviesPopular.data)
                    }
                    is Resource.Error -> {
                        binding.progressBarPopular.visibility = View.GONE
                        binding.viewErrorPopular.root.visibility = View.VISIBLE
                        binding.viewErrorPopular.tvError.text = moviesPopular.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })
    }
}