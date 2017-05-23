package id.paniclabs.kotlinmvp.feature.oldest

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.ajalt.flexadapter.FlexAdapter
import com.github.ajalt.flexadapter.register
import id.paniclabs.kotlinmvp.App
import id.paniclabs.kotlinmvp.R
import id.paniclabs.kotlinmvp.feature.popular.PopularFragment
import id.paniclabs.kotlinmvp.model.Photo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_data.view.*
import javax.inject.Inject

/**
 * @author ali@pergikuliner
 * @created 5/23/17.
 * @project BelajarKotlinMVP.
 */
class OldestFragment: Fragment(),OldestView{
    @Inject
    lateinit var mPresenter: OldestPresenters

    // Create the adapter
    val adapter = FlexAdapter<Any>()

    companion object {
        val TAG = PopularFragment::class.java.simpleName!!
        fun newInstance() = PopularFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity.title = "Oldest" //setToolbarTitle
        val view = inflater?.inflate(R.layout.fragment_popular, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rvlist.adapter = adapter
        val layoutManager = LinearLayoutManager(activity)
        rvlist.layoutManager = layoutManager

        with(adapter){
            register<Photo>(R.layout.row_data) { item, view, position ->

                Glide.with(activity)
                        .load(item.urls?.regular)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(view.card_img)

                view.card_author.text = "By : " + item.user?.username

                if(item.likes!! > 0){
                    view.card_liked.text = item.likes.toString()
                }else view.card_liked.setVisibility(View.GONE)


                view.setOnClickListener {
                    showSnack(item.id.toString())
                }
            }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        App.get(context).appComponent.inject(this)

        //Load API popularPhotos
        mPresenter.loadPopularPhotos()

    }

    override fun onStart() {
        super.onStart()
        mPresenter.attachView(this)
    }

    override fun onDestroyView() {
        mPresenter.detachView()
        super.onDestroyView()
    }

    override fun showError(errorMsg: String) {
        showSnack(errorMsg)
    }

    private fun showSnack(msg: String) {
        Snackbar.make(rvlist,msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun showData(list: List<Photo>) {
        adapter.items.addAll(list)
        adapter.notifyDataSetChanged()
    }

    override fun isLoading(isLoading: Boolean) {
        mprogress.setVisibility(if(isLoading) View.VISIBLE else View.GONE)
    }

}