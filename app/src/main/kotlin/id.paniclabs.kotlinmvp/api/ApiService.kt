package id.paniclabs.kotlinmvp.api

import id.paniclabs.kotlinmvp.model.Order
import id.paniclabs.kotlinmvp.model.Photo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * @author      paniclabs.
 * @created     on 5/23/17.
 * @email       panic.inc.dev@gmail.com
 * @projectName Belajarkotlin-android
 */
interface ApiService {

    @GET("photos")
    fun getPhotos(@Query("page") page: Int,
                  @Query("per_page") perPage: Int,
                  @Query("order_by") orderBy: Order? = null): Observable<List<Photo>>

    @GET("/photos/curated")
    fun getCuratedPhotos(@Query("page") page: Int,
                         @Query("per_page") perPage: Int,
                         @Query("order_by") orderBy: Order? = null): Observable<List<Photo>>

    @GET("photos/{id}")
    fun getPhoto(@Path("id") id: Int): Observable<Photo>
}