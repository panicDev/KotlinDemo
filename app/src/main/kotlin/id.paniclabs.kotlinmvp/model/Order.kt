package id.paniclabs.kotlinmvp.model

import com.google.gson.annotations.SerializedName

/**
 * @author ali@pergikuliner
 * @created 5/23/17.
 * @project BelajarKotlinMVP.
 */
enum class Order {
    @SerializedName("latest") LATEST,
    @SerializedName("oldest") OLDEST,
    @SerializedName("popular") POPULAR
}