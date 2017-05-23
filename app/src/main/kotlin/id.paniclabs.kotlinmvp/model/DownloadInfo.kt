package id.paniclabs.kotlinmvp.model

/**
 * @author paniclabs.
 * *
 * @created on 5/24/17.
 * *
 * @email panic.inc.dev@gmail.com
 * *
 * @projectName Belajarkotlin-android
 */
class DownloadInfo(val url: String) {
    var total: Long = 0
    var progress: Long = 0
    var fileName: String? = null

    companion object {
        val TOTAL_ERROR: Long = -1
    }
}
