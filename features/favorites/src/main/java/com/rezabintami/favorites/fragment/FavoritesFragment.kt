package com.rezabintami.favorites.fragment

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.rezabintami.common.di.FavoritesModuleDependencies
import com.rezabintami.favorites.adapter.FavoritesAdapter
import com.rezabintami.favorites.databinding.FragmentFavoritesBinding
import com.rezabintami.favorites.di.DaggerFavoriteComponent
import com.rezabintami.favorites.R
import com.rezabintami.favorites.factory.ViewModelFactory
import com.rezabintami.movies.view.activity.detailmovies.DetailMoviesActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoritesFragment: Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = requireNotNull(_binding) { "Binding view must not be null" }
    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: FavoritesViewModel by viewModels {
        factory
    }

    private var adapterFavorites: FavoritesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initDagger()
        super.onViewCreated(view, savedInstanceState)

        initView()
        initClickListener()
        initViewModelObserver()
    }

    private fun initDagger(){
        DaggerFavoriteComponent.builder()
            .context(requireActivity())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity(),
                    FavoritesModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    private fun initClickListener(){
        adapterFavorites?.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailMoviesActivity::class.java)
            intent.putExtra(DetailMoviesActivity.MOVIE_ID, selectedData.id)
            startActivity(intent)
        }
    }

    private fun initView(){
        binding.progressBar.visibility = View.VISIBLE
        binding.rvFavorites.visibility = View.GONE
        binding.header.textViewHeader.text = getString(R.string.favorites)
        adapterFavorites = FavoritesAdapter()

        binding.rvFavorites.adapter = adapterFavorites

    }

    @SuppressLint("SetTextI18n")
    private fun initViewModelObserver() {
        viewModel.favoritesList.observe(viewLifecycleOwner, { favorites ->
            if(favorites.isNullOrEmpty()) {
                binding.viewError.tvError.visibility = View.VISIBLE
                binding.viewError.tvError.text = getString(R.string.empty_data)
                binding.progressBar.visibility = View.GONE
                binding.rvFavorites.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.rvFavorites.visibility = View.VISIBLE
                adapterFavorites?.setData(favorites)
            }
        })
    }
}