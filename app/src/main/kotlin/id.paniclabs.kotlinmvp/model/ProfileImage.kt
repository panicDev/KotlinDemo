package id.paniclabs.kotlinmvp.model

import com.google.gson.annotations.SerializedName

/**
 * @author ali@pergikuliner
 * @created 5/23/17.
 * @project BelajarKotlinMVP.
 */
data class ProfileImage (
        @SerializedName("small") val small: String? = null,
        @SerializedName("medium") val medium: String? = null,
        @SerializedName("large") val large: String? = null
        )