package id.paniclabs.kotlinmvp

import android.app.Application
import id.paniclabs.kotlinmvp.di.AppComponent
import id.paniclabs.kotlinmvp.di.DaggerAppComponent


class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }
}