package id.paniclabs.kotlinmvp.util

import android.content.Context
import android.os.Environment
import android.os.storage.StorageManager
import java.io.File
import java.lang.reflect.InvocationTargetException
import java.util.*

/**
 * @author paniclabs.
 * *
 * @created on 5/24/17.
 * *
 * @email panic.inc.dev@gmail.com
 * *
 * @projectName Belajarkotlin-android
 */
object DownFileUtils {
    val IMAGES_DIR = "img"
    val ROOT_DIR = "panicdownloader"

    fun getImagesDir(context: Context, time: String): String {
        return getDir(IMAGES_DIR, time, context)!!
    }

    val isSDCardAvailable: Boolean
        get() {
            if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
                return true
            } else {
                return false
            }
        }

    fun getDir(name: String, time: String, context: Context): String? {
        var sb = StringBuilder()
        if (isSDCardAvailable) {
            sb.append(externalStoragePath)
        } else {
            sb.append(getCachePath(context))
        }
        sb.append(name)
        sb.append(File.separator)
        sb.append(time)
        sb.append(File.separator)
        var path = sb.toString()
        if (createDirs(path)) {
            return path
        } else {
            val extSDCardPaths = getExtSDCardPath(context)
            if (extSDCardPaths != null && extSDCardPaths.size >= 2) {
                sb = StringBuilder()
                sb.append(getExtSDCardPath(context)!![1])
                sb.append(File.separator)
                sb.append(ROOT_DIR)
                sb.append(File.separator)
                sb.append(name)
                sb.append(File.separator)
                sb.append(time)
                sb.append(File.separator)
                path = sb.toString()
                if (createDirs(path)) {
                    return path
                } else {
                    return null
                }
            }
            return null
        }
    }

    fun getExtSDCardPath(context: Context): List<String>? {
        val lResult = ArrayList<String>()
        val storageManager = context.getSystemService(Context.STORAGE_SERVICE) as StorageManager
        try {
            val paramClasses = arrayOf<Class<*>>()
            val getVolumePathsMethod = StorageManager::class.java.getMethod("getVolumePaths", *paramClasses)
            getVolumePathsMethod.isAccessible = true
            val params = arrayOf<Any>()
            val invoke = getVolumePathsMethod.invoke(storageManager, *params)
            for (i in 0..(invoke as Array<String>).size - 1) {
                lResult.add(invoke[i])
            }
        } catch (e1: NoSuchMethodException) {
            e1.printStackTrace()
        } catch (e1: InvocationTargetException) {
            e1.printStackTrace()
        } catch (e1: IllegalArgumentException) {
            e1.printStackTrace()
        } catch (e1: IllegalAccessException) {
            e1.printStackTrace()
        }

        return lResult
    }

    fun createDirs(dirPath: String): Boolean {
        val file = File(dirPath)
        if (!file.exists() || !file.isDirectory) {
            return file.mkdirs()
        }
        return true
    }

    val externalStoragePath: String
        get() {
            val sb = StringBuilder()
            sb.append(Environment.getExternalStorageDirectory().absolutePath)
            sb.append(File.separator)
            sb.append(ROOT_DIR)
            sb.append(File.separator)
            return sb.toString()
        }

    fun getCachePath(context: Context): String? {
        val f = context.cacheDir
        if (null == f) {
            return null
        } else {
            return f.absolutePath + "/"
        }
    }
}
