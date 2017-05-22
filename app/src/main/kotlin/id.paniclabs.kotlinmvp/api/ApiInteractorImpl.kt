package id.paniclabs.kotlinmvp.api

import id.paniclabs.kotlinmvp.api.response.DataHargaResponse
import id.paniclabs.kotlinmvp.api.response.DataItem
import id.paniclabs.kotlinmvp.rx.Schedulers
import io.reactivex.Flowable
import io.reactivex.Single
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

    /**
     * Implement Interactor api
     */
    override fun ambilDataHarga(month: Int, year: Int): Flowable<DataHargaResponse> {
        return apiService.listHarga(month,year).subscribeOn(rxSchedulers.io())
    }


}