package id.paniclabs.kotlinmvp.di

import dagger.Component
import id.paniclabs.kotlinmvp.feature.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApiInteratorModule::class, RxModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}