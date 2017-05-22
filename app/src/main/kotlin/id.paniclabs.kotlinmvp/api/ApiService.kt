package id.paniclabs.kotlinmvp.api

import id.paniclabs.kotlinmvp.api.response.DataHargaResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * @author      paniclabs.
 * @created     on 5/23/17.
 * @email       panic.inc.dev@gmail.com
 * @projectName Belajarkotlin-android
 */
interface ApiService {

    /**
     * GET Harga kebutuhan pokok nasional
     */
    @GET("macros/exec?service=AKfycbxoZDvBSaC2QRdzvoRlFzr6EzDLoimdqewnpeMoGoMFAT2sD3cB")
    fun listHarga(@Query("bulan") month: Int, @Query("tahun") year: Int) : Flowable<DataHargaResponse>
}