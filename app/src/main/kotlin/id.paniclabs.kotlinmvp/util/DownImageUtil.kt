package id.paniclabs.kotlinmvp.util


import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import id.paniclabs.kotlinmvp.api.DownLoadImageService
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @author paniclabs.
 * *
 * @created on 5/24/17.
 * *
 * @email panic.inc.dev@gmail.com
 * *
 * @projectName Belajarkotlin-android
 */
class DownImageUtil(private val mContext: Context) {
    private var mImageCallBack: ImageCallback? = null

    fun setImageCallBack(imageCallBack: ImageCallback) {
        mImageCallBack = imageCallBack
    }


    fun runOnQueue(runnable: Runnable) {
        if (singleExecutor == null) {
            singleExecutor = Executors.newSingleThreadExecutor()
        }
        singleExecutor!!.submit(runnable)
    }

    fun onDownLoad(url: String, i: Int, sort: String) {
        val service = DownLoadImageService(mContext,
                url,
                object : ImageDownLoadCallBack {
                    override fun onDownLoadSuccess(file: File) {
                        val desPath = DownFileUtils.getImagesDir(mContext, sort) + i + ".png"
                        savePhotoToSDCard(file.path, desPath)
                        (mContext as Activity).runOnUiThread { mImageCallBack!!.onSuccess(desPath) }

                    }

                    override fun onDownLoadFailed() {
                        mImageCallBack!!.onFailed()
                    }
                })
        runOnQueue(service)
    }

    companion object {
        private var singleExecutor: ExecutorService? = null

        fun savePhotoToSDCard(path: String, desPath: String) {
            var bitmap: Bitmap? = null
            try {
                val file = File(path)
                if (!file.exists()) {
                    return
                }
                val opts = BitmapFactory.Options()
                opts.inJustDecodeBounds = true
                BitmapFactory.decodeFile(path, opts)
                opts.inJustDecodeBounds = false
                bitmap = BitmapFactory.decodeFile(path, opts)
                val baos = ByteArrayOutputStream()
                val options = 100
                bitmap!!.compress(Bitmap.CompressFormat.PNG, options, baos)
                val file2 = File(desPath)
                val fOut = FileOutputStream(file2)
                fOut.write(baos.toByteArray())
                fOut.flush()
                fOut.close()
                baos.flush()
                baos.close()
                bitmap.recycle()
                bitmap = null
            } catch (e: Exception) {
                e.printStackTrace()
                return
            }

        }
    }
}
