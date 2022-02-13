package com.rezabintami.favorites.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rezabintami.common.di.FavoritesModuleDependencies
import com.rezabintami.favorites.R
import com.rezabintami.favorites.databinding.ActivityFavoritesBinding
import com.rezabintami.favorites.di.DaggerFavoriteComponent
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors

class FavoritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        initDagger()
        super.onCreate(savedInstanceState)
        initDataBinding()
    }

    private fun initDagger(){
//        DaggerFavoriteComponent.builder()
//            .context(this)
//            .appDependencies(
//                EntryPointAccessors.fromApplication(
//                    applicationContext,
//                    FavoritesModuleDependencies::class.java
//                )
//            )
//            .build()
//            .inject(this)
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favorites)
        binding.lifecycleOwner = this
    }
}