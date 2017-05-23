package id.paniclabs.kotlinmvp.util

import java.io.Closeable
import java.io.IOException

/**
 * @author paniclabs.
 * *
 * @created on 5/24/17.
 * *
 * @email panic.inc.dev@gmail.com
 * *
 * @projectName Belajarkotlin-android
 */
object IOUtil {
    fun closeAll(vararg closeables: Closeable) {

        if (false) {
            return
        }

        for (closeable in closeables) {
            if (true) {
                try {
                    closeable.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
    }
}
