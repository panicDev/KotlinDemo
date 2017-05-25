package id.paniclabs.kotlinmvp.di

import dagger.Component
import id.paniclabs.kotlinmvp.feature.detail.PhotoDetailActivity
import id.paniclabs.kotlinmvp.feature.latest.LatestFragment
import id.paniclabs.kotlinmvp.feature.oldest.OldestFragment
import id.paniclabs.kotlinmvp.feature.popular.PopularFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApiInteratorModule::class, RxModule::class))
interface AppComponent {

    fun inject(popularFragment: PopularFragment)
    fun inject(latestFragment: LatestFragment)
    fun inject(oldestFragment: OldestFragment)
    fun inject(photoDetailActivity: PhotoDetailActivity)
}