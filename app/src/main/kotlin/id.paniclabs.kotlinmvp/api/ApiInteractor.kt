package id.paniclabs.kotlinmvp.api

import id.paniclabs.kotlinmvp.api.response.DataHargaResponse
import io.reactivex.Flowable


/**
 * @author      paniclabs.
 * @created     on 5/23/17.
 * @email       panic.inc.dev@gmail.com
 * @projectName Belajarkotlin-android
 */
interface ApiInteractor {

    fun ambilDataHarga(month: Int, year: Int) : Flowable<DataHargaResponse>
}