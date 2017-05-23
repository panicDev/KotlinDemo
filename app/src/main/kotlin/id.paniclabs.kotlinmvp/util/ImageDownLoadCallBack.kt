package id.paniclabs.kotlinmvp.util

import java.io.File


/**
 * @author      paniclabs.
 * @created     on 5/24/17.
 * @email       panic.inc.dev@gmail.com
 * @projectName Belajarkotlin-android
 */
interface ImageDownLoadCallBack{
    fun onDownLoadSuccess(file: File)

    fun onDownLoadFailed()
}