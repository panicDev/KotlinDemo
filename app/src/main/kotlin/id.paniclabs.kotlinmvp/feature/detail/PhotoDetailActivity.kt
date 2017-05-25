package id.paniclabs.kotlinmvp.feature.detail


import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View.GONE
import android.view.ViewTreeObserver
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.orhanobut.logger.Logger
import com.tbruyelle.rxpermissions2.RxPermissions
import id.paniclabs.kotlinmvp.App
import id.paniclabs.kotlinmvp.R
import id.paniclabs.kotlinmvp.model.DownloadController
import id.paniclabs.kotlinmvp.util.DragPhotoView
import kotlinx.android.synthetic.main.activity_drag_photo.*
import zlc.season.rxdownload2.RxDownload
import zlc.season.rxdownload2.entity.DownloadEvent
import zlc.season.rxdownload2.entity.DownloadFlag
import java.lang.Exception


/**
 * @author paniclabs.
 * *
 * @created on 5/24/17.
 * *
 * @email panic.inc.dev@gmail.com
 * *
 * @projectName Belajarkotlin-android
 */
class PhotoDetailActivity : AppCompatActivity(), RequestListener<String, GlideDrawable> {
    override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
        progressbar.setVisibility(GONE)
        dragphotoview.setImageDrawable(resource)
        return false
    }

    override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
        progressbar.setVisibility(GONE)
        Log.e("GLIDE", "ERROR",e)
        return false
    }


    internal var mOriginLeft: Int = 0
    internal var mOriginTop: Int = 0
    internal var mOriginHeight: Int = 0
    internal var mOriginWidth: Int = 0
    internal var mOriginCenterX: Int = 0
    internal var mOriginCenterY: Int = 0
    private var mTargetHeight: Float = 0.toFloat()
    private var mTargetWidth: Float = 0.toFloat()
    private var mScaleX: Float = 0.toFloat()
    private var mScaleY: Float = 0.toFloat()
    private var mTranslationX: Float = 0.toFloat()
    private var mTranslationY: Float = 0.toFloat()

    private var mRxDownload: RxDownload? = null
    private var mDownloadController: DownloadController? = null


    private var imgUrl: String? = null

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_drag_photo)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.colorPrimary)
        }
        App.get(applicationContext).appComponent.inject(this)

        imgUrl = intent.getStringExtra("imgUrl")

        mRxDownload = RxDownload.getInstance(this).defaultSavePath(getString(R.string.app_name))
        mDownloadController = DownloadController(fabprogress)


        Glide.with(applicationContext)
                .load(imgUrl)
                .listener(this)
                .into(dragphotoview)

        progressfab.setVisibility(GONE)
        fabprogress.setOnClickListener {
            (mDownloadController as DownloadController).handleClick(object : DownloadController.Callback {
                override fun pauseDownload() {
                    snack("Download Paused")
                }

                override fun install() {
                    snack("Download compeleted")
                }

                override fun startDownload() {
                    downloadImage(imgUrl)
                }

            })
        }

        mRxDownload?.receiveDownloadStatus(imgUrl)
                ?.subscribe { downloadEvent ->
                    if (downloadEvent.flag == DownloadFlag.FAILED) {
                        val throwable = downloadEvent.error
                        Logger.e(throwable.localizedMessage)
                    }
                    mDownloadController?.setEvent(downloadEvent)
                    updateProgress(downloadEvent)
                }

        dragphotoview?.setOnTapListener(object : DragPhotoView.OnTapListener{
            override fun onTap(view: DragPhotoView) {
                finishWithAnimation()
            }

        })

        dragphotoview?.setOnExitListener(object : DragPhotoView.OnExitListener{
            override fun onExit(view: DragPhotoView, translateX: Float, translateY: Float, w: Float, h: Float) {
                performExitAnimation(view, translateX, translateY, w, h)
            }

        })

        rootview.getViewTreeObserver()
                .addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        rootview.getViewTreeObserver().removeOnGlobalLayoutListener(this)

                        mOriginLeft = intent.getIntExtra("left", 0)
                        mOriginTop = intent.getIntExtra("top", 0)
                        mOriginHeight = intent.getIntExtra("height", 0)
                        mOriginWidth = intent.getIntExtra("width", 0)
                        mOriginCenterX = mOriginLeft + mOriginWidth / 2
                        mOriginCenterY = mOriginTop + mOriginHeight / 2

                        val location = IntArray(2)

                        val photoView = dragphotoview!!
                        photoView.getLocationOnScreen(location)

                        mTargetHeight = photoView.height.toFloat()
                        mTargetWidth = photoView.width.toFloat()
                        mScaleX = mOriginWidth.toFloat() / mTargetWidth
                        mScaleY = mOriginHeight.toFloat() / mTargetHeight

                        val targetCenterX = location[0] + mTargetWidth / 2
                        val targetCenterY = location[1] + mTargetHeight / 2

                        mTranslationX = mOriginCenterX - targetCenterX
                        mTranslationY = mOriginCenterY - targetCenterY
                        photoView.translationX = mTranslationX
                        photoView.translationY = mTranslationY

                        photoView.scaleX = mScaleX
                        photoView.scaleY = mScaleY

                        performEnterAnimation()

                    }
                })

    }

    override fun onResume() {
        super.onResume()

    }

    private fun updateProgress(event: DownloadEvent) {
        val status = event.downloadStatus
        progressfab.setIndeterminate(status.isChunked)
        progressfab.setMax(status.totalSize.toInt())
        progressfab.setProgress(status.downloadSize.toInt())

    }

    private fun downloadImage(imgUrl: String?) {
        RxPermissions.getInstance(this)
                .request(WRITE_EXTERNAL_STORAGE)
                .doOnNext { granted ->
                    if (!granted) {
                        throw RuntimeException("no permission")
                    }
                }
                .compose<Any>(mRxDownload?.transformService(imgUrl,imgUrl))
                .subscribe (
                        { snack("Downloading image...") },
                        {it -> Logger.e("Error : ${it.localizedMessage}") },
                        {-> Logger.w("onCompleted") }
                )
    }

    private fun snack(toString: String) {
        Snackbar.make(rootview,toString,Snackbar.LENGTH_SHORT).show()
    }


    private fun performExitAnimation(view: DragPhotoView, x: Float, y: Float, w: Float, h: Float) {
        view.finishAnimationCallBack()
        val viewX = mTargetWidth / 2 + x - mTargetWidth * mScaleX / 2
        val viewY = mTargetHeight / 2 + y - mTargetHeight * mScaleY / 2
        view.x = viewX
        view.y = viewY

        val centerX = view.x + mOriginWidth / 2
        val centerY = view.y + mOriginHeight / 2

        val translateX = mOriginCenterX - centerX
        val translateY = mOriginCenterY - centerY


        val translateXAnimator = ValueAnimator.ofFloat(view.x, view.x + translateX)
        translateXAnimator.addUpdateListener { valueAnimator -> view.x = valueAnimator.animatedValue as Float }
        translateXAnimator.duration = 300
        translateXAnimator.start()
        val translateYAnimator = ValueAnimator.ofFloat(view.y, view.y + translateY)
        translateYAnimator.addUpdateListener { valueAnimator -> view.y = valueAnimator.animatedValue as Float }
        translateYAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {

            }

            override fun onAnimationEnd(animator: Animator) {
                animator.removeAllListeners()
                finish()
                overridePendingTransition(0, 0)
            }

            override fun onAnimationCancel(animator: Animator) {

            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })
        translateYAnimator.duration = 300
        translateYAnimator.start()
    }

    private fun finishWithAnimation() {

        val photoView = dragphotoview!!
        val translateXAnimator = ValueAnimator.ofFloat(0F, mTranslationX)
        translateXAnimator.addUpdateListener { valueAnimator -> photoView.x = valueAnimator.animatedValue as Float }
        translateXAnimator.duration = 300
        translateXAnimator.start()

        val translateYAnimator = ValueAnimator.ofFloat(0F, mTranslationY)
        translateYAnimator.addUpdateListener { valueAnimator -> photoView.y = valueAnimator.animatedValue as Float }
        translateYAnimator.duration = 300
        translateYAnimator.start()

        val scaleYAnimator = ValueAnimator.ofFloat(1F, mScaleY)
        scaleYAnimator.addUpdateListener { valueAnimator -> photoView.scaleY = valueAnimator.animatedValue as Float }
        scaleYAnimator.duration = 300
        scaleYAnimator.start()

        val scaleXAnimator = ValueAnimator.ofFloat(1F, mScaleX)
        scaleXAnimator.addUpdateListener { valueAnimator -> photoView.scaleX = valueAnimator.animatedValue as Float }

        scaleXAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {

            }

            override fun onAnimationEnd(animator: Animator) {
                animator.removeAllListeners()
                finish()
                overridePendingTransition(0, 0)
            }

            override fun onAnimationCancel(animator: Animator) {

            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })
        scaleXAnimator.duration = 300
        scaleXAnimator.start()
    }

    private fun performEnterAnimation() {
        val photoView = dragphotoview!!
        val translateXAnimator = ValueAnimator.ofFloat(photoView.x, 0F)
        translateXAnimator.addUpdateListener { valueAnimator -> photoView.x = valueAnimator.animatedValue as Float }
        translateXAnimator.duration = 300
        translateXAnimator.start()

        val translateYAnimator = ValueAnimator.ofFloat(photoView.y, 0F)
        translateYAnimator.addUpdateListener { valueAnimator -> photoView.y = valueAnimator.animatedValue as Float }
        translateYAnimator.duration = 300
        translateYAnimator.start()

        val scaleYAnimator = ValueAnimator.ofFloat(mScaleY, 1F)
        scaleYAnimator.addUpdateListener { valueAnimator -> photoView.scaleY = valueAnimator.animatedValue as Float }
        scaleYAnimator.duration = 300
        scaleYAnimator.start()

        val scaleXAnimator = ValueAnimator.ofFloat(mScaleX, 1F)
        scaleXAnimator.addUpdateListener { valueAnimator -> photoView.scaleX = valueAnimator.animatedValue as Float }
        scaleXAnimator.duration = 300
        scaleXAnimator.start()
    }

    override fun onBackPressed() {
        finishWithAnimation()
    }
}
