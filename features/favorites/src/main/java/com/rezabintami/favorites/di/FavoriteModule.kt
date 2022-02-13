package com.rezabintami.favorites.di


import android.content.Context
import com.rezabintami.common.di.FavoritesModuleDependencies
import com.rezabintami.favorites.activity.FavoritesActivity
import com.rezabintami.favorites.fragment.FavoritesFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoritesModuleDependencies::class])
interface FavoriteComponent {

    fun inject(activity: FavoritesFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(mapsModuleDependencies: FavoritesModuleDependencies): Builder
        fun build(): FavoriteComponent
    }

}