package id.paniclabs.kotlinmvp.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author ali@pergikuliner
 * @created 5/23/17.
 * @project BelajarKotlinMVP.
 */
data class Photo (
        @SerializedName("id") val id: String? = null,
        @SerializedName("created_at") val createdAt: Date? = null,
        @SerializedName("width") val width: Int? = null,
        @SerializedName("height") val height: Int? = null,
        @SerializedName("color") val color: String? = null,
        @SerializedName("likes") val likes: Int? = null,
        @SerializedName("liked_by_user") val likedByUser: Boolean? = null,
        @SerializedName("user") val user: User? = null,
        @SerializedName("urls") val urls: PhotoUrls? = null,
        @SerializedName("links") val links: Links? = null,
        @SerializedName("categories") val categories: List<Category>? = null
        )