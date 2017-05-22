package id.paniclabs.kotlinmvp.api.response

import com.google.gson.annotations.SerializedName


/**
 * @author      paniclabs.
 * @created     on 5/23/17.
 * @email       panic.inc.dev@gmail.com
 * @projectName Belajarkotlin-android
 */
data class Data (
        @field:SerializedName("tanggal") val tanggal: String? = null,
        @field:SerializedName("harga") val harga: String? = null
        )