package id.paniclabs.kotlinmvp.feature.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import id.paniclabs.kotlinmvp.App
import id.paniclabs.kotlinmvp.R
import id.paniclabs.kotlinmvp.api.response.DataItem
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject


/**
 * @author      paniclabs.
 * @created     on 5/23/17.
 * @email       panic.inc.dev@gmail.com
 * @projectName Belajarkotlin-android
 */
class MainActivity : AppCompatActivity(),MainView, MainAdapter.onItemClickListener {

    @Inject
    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as App).appComponent.inject(this)

        val year = Calendar.getInstance().get(Calendar.YEAR)
        val month = Calendar.getInstance().get(Calendar.MONTH)

        mPresenter.loadData(month,year)

    }


    override fun onStart() {
        super.onStart()
        mPresenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }

    /**
     * onErrorView
     * @return errorMessage
     */
    override fun onErrorView(errorMsg: String?) {
        Log.e("MAINACTIVITY",errorMsg)
        Toast.makeText(applicationContext, "Error : " + errorMsg,Toast.LENGTH_SHORT).show()
    }

    /**
     * onResultView
     * @return it (Response)
     */
    override fun onResultView(it: List<DataItem>) {
        rvlist.setHasFixedSize(true)
        rvlist.layoutManager = LinearLayoutManager(this)
        rvlist.adapter = MainAdapter(it , this)
    }

    /**
     * onLoadingView
     * @return boolean
     */
    override fun onLoadingView(b: Boolean) {
        mprogress.setVisibility(if(b)VISIBLE else GONE)
    }

    override fun onItemClick(item: DataItem) {
        Toast.makeText(applicationContext,item.komoditas + "Clicked",Toast.LENGTH_SHORT).show()
    }
}