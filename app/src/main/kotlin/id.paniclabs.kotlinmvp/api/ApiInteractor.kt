package id.paniclabs.kotlinmvp.api

import id.paniclabs.kotlinmvp.model.Photo
import io.reactivex.Observable


/**
 * @author      paniclabs.
 * @created     on 5/23/17.
 * @email       panic.inc.dev@gmail.com
 * @projectName Belajarkotlin-android
 */
interface ApiInteractor {

    fun getPhoto(id:Int)          : Observable<Photo>
    fun getLatestPhotos()         : Observable<List<Photo>>
    fun getOldestPhotos()         : Observable<List<Photo>>
    fun getPopularPhotos()        : Observable<List<Photo>>
    fun getLatestCuratedPhotos()  : Observable<List<Photo>>
    fun getOldestCuratedPhotos()  : Observable<List<Photo>>
    fun getPopularCuratedPhotos() : Observable<List<Photo>>
}