package id.paniclabs.kotlinmvp.model

import com.google.gson.annotations.SerializedName

/**
 * @author ali@pergikuliner
 * @created 5/23/17.
 * @project BelajarKotlinMVP.
 */
data class Links (
        @SerializedName("self") val self: String? = null,
        @SerializedName("html") val html: String? = null,
        @SerializedName("photos") val photos: String? = null,
        @SerializedName("likes") val likes: String? = null,
        @SerializedName("portfolio") val portfolio: String? = null,
        @SerializedName("download") val download: String? = null,
        @SerializedName("download_location") val downloadLocation: String? = null
        )