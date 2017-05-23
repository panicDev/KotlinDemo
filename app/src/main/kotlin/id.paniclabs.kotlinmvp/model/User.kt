package id.paniclabs.kotlinmvp.model

import com.google.gson.annotations.SerializedName

/**
 * @author ali@pergikuliner
 * @created 5/23/17.
 * @project BelajarKotlinMVP.
 */
data class User(
        @SerializedName("id") val id: String? = null,
        @SerializedName("username") val username: String? = null,
        @SerializedName("name") val name: String? = null,
        @SerializedName("portfolio_url") val portfolioUrl: String? = null,
        @SerializedName("bio") val bio: String? = null,
        @SerializedName("location") val location: String? = null,
        @SerializedName("total_likes") val totalLikes: Int? = null,
        @SerializedName("total_photos") val totalPhotos: String? = null,
        @SerializedName("total_collections") val totalCollections: String? = null,
        @SerializedName("profile_image") val profileImage: ProfileImage? = null,
        @SerializedName("links") val links: Links? = null
        )