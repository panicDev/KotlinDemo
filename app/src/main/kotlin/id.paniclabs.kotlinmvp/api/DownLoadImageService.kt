package id.paniclabs.kotlinmvp.api

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import id.paniclabs.kotlinmvp.util.ImageDownLoadCallBack
import java.io.File

/**
 * @author paniclabs.
 * *
 * @created on 5/24/17.
 * *
 * @email panic.inc.dev@gmail.com
 * *
 * @projectName Belajarkotlin-android
 */
class DownLoadImageService(
        private val context: Context, private val url: String, private val callBack: ImageDownLoadCallBack) : Runnable {

    override fun run() {
        var file: File? = null
        try {
            file = Glide.with(context)
                    .load(url)
                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (file != null) {
                callBack.onDownLoadSuccess(file)
            } else {
                callBack.onDownLoadFailed()
            }
        }
    }

}
