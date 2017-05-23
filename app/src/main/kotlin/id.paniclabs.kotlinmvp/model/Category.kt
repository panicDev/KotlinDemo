package id.paniclabs.kotlinmvp.model

import com.google.gson.annotations.SerializedName

/**
 * @author ali@pergikuliner
 * @created 5/23/17.
 * @project BelajarKotlinMVP.
 */
data class Category (
        @SerializedName("id") val id: Int? = null,
        @SerializedName("title") val title: String? = null,
        @SerializedName("photo_count") val photoCount: Int? = null,
        @SerializedName("links") val links: Links? = null
)