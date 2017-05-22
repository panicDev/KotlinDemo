package id.paniclabs.kotlinmvp.feature.main

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.paniclabs.kotlinmvp.R
import id.paniclabs.kotlinmvp.api.response.DataItem
import kotlinx.android.synthetic.main.row_data.view.*
import java.util.*


/**
 * @author      paniclabs.
 * @created     on 5/23/17.
 * @email       panic.inc.dev@gmail.com
 * @projectName Belajarkotlin-android
 */
class MainAdapter (val data : List<DataItem>, val listener : onItemClickListener) : RecyclerView.Adapter<MainAdapter.MainHolder>() {
    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: MainHolder, position: Int) = holder.bind(
            data[position], listener
    )

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainHolder = MainHolder(
            LayoutInflater.from(parent?.context).inflate(R.layout.row_data, parent, false)
    )

    class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DataItem, listener: onItemClickListener) = with(itemView) {

            /*setText Komoditas*/
            txtkomoditas.text = item.komoditas

            /*Sort by Tanggal*/
            val tgl = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            for (items in item.datas!!) {
                Log.e("HARGA",items?.harga)
                if (items?.tanggal.equals(tgl.toString())){

                    txtHarga.text = items?.harga //setHarga
                }
            }

            txtunit.text = item.unit //setUnit


            //set OnItemClickListener
            setOnClickListener { listener.onItemClick(item) }

        }
    }

    interface onItemClickListener {
        fun onItemClick(item: DataItem)
    }
}