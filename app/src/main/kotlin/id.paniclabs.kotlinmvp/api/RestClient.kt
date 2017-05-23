package id.paniclabs.kotlinmvp.api

import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import id.paniclabs.kotlinmvp.BuildConfig
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


/**
 * @author      paniclabs.
 * @created     on 5/23/17.
 * @email       panic.inc.dev@gmail.com
 * @projectName Belajarkotlin-android
 */

class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
                .addHeader("Accept-Version", "v1")
                .addHeader("Authorization", String.format("Client-ID %s", BuildConfig.UNSPLASH_CLIENT_ID))
                .build()
        val response = chain.proceed(request)
        return response
    }
}

val client = OkHttpClient.Builder()
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(if(BuildConfig.DEBUG)Level.BASIC else Level.NONE)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .build())
        .build()

val converter = GsonConverterFactory.create(GsonBuilder().create())
val callAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())

fun retrofit() = Retrofit.Builder()
        .baseUrl("https://api.unsplash.com/")
        .client(client)
        .addConverterFactory(converter)
        .addCallAdapterFactory(callAdapter)
        .build()

fun apiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
