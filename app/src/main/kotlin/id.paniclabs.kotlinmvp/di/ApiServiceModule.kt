package id.paniclabs.kotlinmvp.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


/**
 * @author      paniclabs.
 * @created     on 5/23/17.
 * @email       panic.inc.dev@gmail.com
 * @projectName Belajarkotlin-android
 */

@Module
class ApiServiceModule {

    @Provides
    @Singleton
    fun retrofit() = id.paniclabs.kotlinmvp.api.retrofit()

    @Provides
    @Singleton
    fun apiService(retrofit: Retrofit) = id.paniclabs.kotlinmvp.api.apiService(retrofit)
}