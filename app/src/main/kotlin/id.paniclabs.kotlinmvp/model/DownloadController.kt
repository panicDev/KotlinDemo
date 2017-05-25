package id.paniclabs.kotlinmvp.model


import com.dmitrymalkovich.android.ProgressFloatingActionButton
import zlc.season.rxdownload2.entity.DownloadEvent
import zlc.season.rxdownload2.entity.DownloadFlag

/**
 * @author paniclabs.
 * *
 * @created on 5/26/17.
 * *
 * @email panic.inc.dev@gmail.com
 * *
 * @projectName KotlinDemo
 */
class DownloadController(private val mAction: ProgressFloatingActionButton) {
    private var mState: DownloadState? = null

    init {
        setState(Normal())
    }

    fun setState(state: DownloadState) {
        mState = state
        mState!!.setText(mAction)
    }

    fun setEvent(event: DownloadEvent) {
        val flag = event.flag
        when (flag) {
            DownloadFlag.NORMAL -> setState(DownloadController.Normal())
            DownloadFlag.WAITING -> setState(DownloadController.Waiting())
            DownloadFlag.STARTED -> setState(DownloadController.Started())
            DownloadFlag.PAUSED -> setState(DownloadController.Paused())
            DownloadFlag.COMPLETED -> setState(DownloadController.Completed())
            DownloadFlag.FAILED -> setState(DownloadController.Failed())
            DownloadFlag.DELETED -> setState(DownloadController.Deleted())
        }
    }

    fun handleClick(callback: Callback) {
        mState!!.handleClick(callback)
    }

    interface Callback {
        fun startDownload()

        fun pauseDownload()

        fun install()
    }

    abstract class DownloadState {

        internal abstract fun setText(button: ProgressFloatingActionButton)

        internal abstract fun handleClick(callback: Callback)
    }

    class Normal : DownloadState() {

        override fun setText(button: ProgressFloatingActionButton) {

        }

        override fun handleClick(callback: Callback) {
            callback.startDownload()
        }
    }

    class Waiting : DownloadState() {
        override fun setText(button: ProgressFloatingActionButton) {

        }

        override fun handleClick(callback: Callback) {
            callback.pauseDownload()
        }
    }

    class Started : DownloadState() {
        override fun setText(button: ProgressFloatingActionButton) {

        }

        override fun handleClick(callback: Callback) {
            callback.pauseDownload()
        }
    }

    class Paused : DownloadState() {
        override fun setText(button: ProgressFloatingActionButton) {

        }

        override fun handleClick(callback: Callback) {
            callback.startDownload()
        }
    }

    class Failed : DownloadState() {
        override fun setText(button: ProgressFloatingActionButton) {

        }

        override fun handleClick(callback: Callback) {
            callback.startDownload()
        }
    }


    class Completed : DownloadState() {
        override fun setText(button: ProgressFloatingActionButton) {

        }

        override fun handleClick(callback: Callback) {
            callback.install()
        }
    }

    class Deleted : DownloadState() {

        override fun setText(button: ProgressFloatingActionButton) {

        }

        override fun handleClick(callback: Callback) {
            callback.startDownload()
        }
    }
}
