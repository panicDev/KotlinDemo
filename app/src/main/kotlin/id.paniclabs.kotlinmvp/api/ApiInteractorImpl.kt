package id.paniclabs.kotlinmvp.api

import id.paniclabs.kotlinmvp.model.Order
import id.paniclabs.kotlinmvp.model.Photo
import id.paniclabs.kotlinmvp.rx.Schedulers
import io.reactivex.Observable
import javax.inject.Inject


/**
 * @author      paniclabs.
 * @created     on 5/23/17.
 * @email       panic.inc.dev@gmail.com
 * @projectName Belajarkotlin-android
 */
class ApiInteractorImpl
@Inject constructor(val apiService: ApiService,
                    val rxSchedulers: Schedulers) : ApiInteractor {

    private fun getPhotos(orderBy: Order?): Observable<List<Photo>> {
        return apiService.getPhotos(1,20,orderBy).subscribeOn(rxSchedulers.io())
    }

    private fun getCuratedPhotos(orderBy: Order?): Observable<List<Photo>> {
        return apiService.getCuratedPhotos(1, 20, orderBy).subscribeOn(rxSchedulers.io())
    }

    override fun getLatestPhotos(): Observable<List<Photo>> {
        return getPhotos(Order.latest)
    }

    override fun getOldestPhotos(): Observable<List<Photo>> {
        return getPhotos(Order.oldest)
    }

    override fun getPopularPhotos(): Observable<List<Photo>> {
        return getPhotos(Order.popular)
    }

    override fun getLatestCuratedPhotos(): Observable<List<Photo>> {
        return getCuratedPhotos(Order.latest)
    }

    override fun getOldestCuratedPhotos(): Observable<List<Photo>> {
        return getCuratedPhotos(Order.oldest)
    }

    override fun getPopularCuratedPhotos(): Observable<List<Photo>> {
        return getCuratedPhotos(Order.popular)
    }

    override fun getPhoto(id: Int): Observable<Photo> {
        return apiService.getPhoto(id).subscribeOn(rxSchedulers.io())
    }
}