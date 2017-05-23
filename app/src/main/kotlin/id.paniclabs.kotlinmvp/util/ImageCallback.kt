package id.paniclabs.kotlinmvp.util


/**
 * @author      paniclabs.
 * @created     on 5/24/17.
 * @email       panic.inc.dev@gmail.com
 * @projectName Belajarkotlin-android
 */
interface ImageCallback{
    fun onSuccess(url: String)

    fun onFailed()
}