package id.paniclabs.kotlinmvp.model

import com.google.gson.annotations.SerializedName

/**
 * @author ali@pergikuliner
 * @created 5/23/17.
 * @project BelajarKotlinMVP.
 */
data class PhotoUrls(
        @SerializedName("raw") val raw: String? = null,
        @SerializedName("full") val full: String? = null,
        @SerializedName("regular") val regular: String? = null,
        @SerializedName("small") val small: String? = null,
        @SerializedName("thumb") val thumb: String? = null
        )