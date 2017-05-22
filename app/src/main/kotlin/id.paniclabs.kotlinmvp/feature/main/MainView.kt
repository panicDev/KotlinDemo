package id.paniclabs.kotlinmvp.feature.main

import id.paniclabs.kotlinmvp.api.response.DataItem


/**
 * @author      paniclabs.
 * @created     on 5/23/17.
 * @email       panic.inc.dev@gmail.com
 * @projectName Belajarkotlin-android
 */
interface MainView {
    fun onErrorView(errorMsg: String?)
    fun onResultView(it: List<DataItem>)
    fun onLoadingView(b:Boolean)

}