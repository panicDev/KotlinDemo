package id.paniclabs.kotlinmvp.di

import dagger.Module
import dagger.Provides
import id.paniclabs.kotlinmvp.api.ApiInteractor
import id.paniclabs.kotlinmvp.api.ApiInteractorImpl
import javax.inject.Singleton


/**
 * @author      paniclabs.
 * @created     on 5/23/17.
 * @email       panic.inc.dev@gmail.com
 * @projectName Belajarkotlin-android
 */

@Module(includes = arrayOf(ApiServiceModule::class,RxModule::class))
class ApiInteratorModule {

    @Provides
    @Singleton
    fun provideApiInteractor(apiInteractorImpl: ApiInteractorImpl): ApiInteractor{
        return apiInteractorImpl
    }
}