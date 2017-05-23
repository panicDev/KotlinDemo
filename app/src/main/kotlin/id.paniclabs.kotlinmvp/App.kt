package id.paniclabs.kotlinmvp

import android.app.Application
import android.content.Context
import id.paniclabs.kotlinmvp.di.AppComponent
import id.paniclabs.kotlinmvp.di.DaggerAppComponent


class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }

    companion object {
        operator fun get(context: Context): App {
            return context.applicationContext as App
        }
    }
}