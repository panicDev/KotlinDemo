package id.paniclabs.kotlinmvp.api.response

import com.google.gson.annotations.SerializedName

data class DataHargaResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)