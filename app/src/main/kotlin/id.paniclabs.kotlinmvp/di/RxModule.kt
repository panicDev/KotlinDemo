package id.paniclabs.kotlinmvp.di

import dagger.Module
import dagger.Provides
import id.paniclabs.kotlinmvp.rx.Schedulers
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Singleton

@Module
class RxModule {

    @Provides
    @Singleton
    fun schedulers() : Schedulers = SchedulersApp
}

object SchedulersApp : Schedulers {
    override fun io(): Scheduler = io.reactivex.schedulers.Schedulers.io()
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}
