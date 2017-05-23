package id.paniclabs.kotlinmvp.util

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.ContextCompat

import java.util.ArrayList

/**
 * @author paniclabs.
 * *
 * @created on 5/24/17.
 * *
 * @email panic.inc.dev@gmail.com
 * *
 * @projectName Belajarkotlin-android
 */
object XPermissionUtils {

    private var mRequestCode = -1

    private var mOnPermissionListener: OnPermissionListener? = null

    interface OnPermissionListener {

        fun onPermissionGranted()

        fun onPermissionDenied()
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissions(context: Context, requestCode: Int, permissions: Array<String>, listener: OnPermissionListener) {
        if (context is Activity) {
            mOnPermissionListener = listener
            val deniedPermissions = getDeniedPermissions(context, *permissions)
            if (deniedPermissions.size > 0) {
                mRequestCode = requestCode
                context.requestPermissions(deniedPermissions
                        .toTypedArray(), requestCode)

            } else {
                if (mOnPermissionListener != null)
                    mOnPermissionListener!!.onPermissionGranted()
            }
        } else {
            throw RuntimeException("Context must be an Activity")
        }
    }

    private fun getDeniedPermissions(context: Context, vararg permissions: String): List<String> {
        val deniedPermissions = ArrayList<String>()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(permission)
            }
        }
        return deniedPermissions
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (mRequestCode != -1 && requestCode == mRequestCode) {
            if (mOnPermissionListener != null) {
                if (verifyPermissions(grantResults)) {
                    mOnPermissionListener!!.onPermissionGranted()
                } else {
                    mOnPermissionListener!!.onPermissionDenied()
                }
            }
        }
    }

    private fun verifyPermissions(grantResults: IntArray): Boolean {
        for (grantResult in grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }
}
