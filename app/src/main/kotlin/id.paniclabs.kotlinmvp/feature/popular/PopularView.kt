package id.paniclabs.kotlinmvp.feature.popular

import id.paniclabs.kotlinmvp.model.Photo

/**
 * @author ali@pergikuliner
 * @created 5/23/17.
 * @project BelajarKotlinMVP.
 */
interface PopularView{
    fun showError(errorMsg: String)
    fun showData(list: List<Photo>)
    fun isLoading(isLoading: Boolean)
}

