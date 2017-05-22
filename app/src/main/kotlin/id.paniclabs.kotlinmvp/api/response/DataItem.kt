package id.paniclabs.kotlinmvp.api.response

import com.google.gson.annotations.SerializedName

data class DataItem(
	@field:SerializedName("unit")
	val unit: String? = null,

	@field:SerializedName("data")
	val datas: List<Data?>? = null,

	@field:SerializedName("komoditas")
	val komoditas: String? = null
)