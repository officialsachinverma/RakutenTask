package com.sachinverma.rakutentask.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.sachinverma.rakutentask.R
import com.sachinverma.rakutentask.model.StationPhoto
import com.squareup.picasso.Picasso

/**
 * Created by Sachin Verma on 2020-02-09.
 */
class StationPhotoListViewHolder (itemView: View, private val listener: OnClick):
    RecyclerView.ViewHolder(itemView), View.OnClickListener {


    private var iv_station_photo: ImageView = itemView.findViewById(R.id.iv_station_photo)
    private var tv_photo_title: TextView = itemView.findViewById(R.id.tv_photo_title)
    private var tv_photo_creation_date: TextView = itemView.findViewById(R.id.tv_photo_creation_date)
    private var rowStationPhoto: ConstraintLayout = itemView.findViewById(R.id.cl_row_station_photo)

    fun bind(stationPhoto: StationPhoto) {


        Picasso.get().load(stationPhoto.stationPhoto).centerInside().into(iv_station_photo)

        tv_photo_title.text = stationPhoto.photoTitle
        tv_photo_creation_date.text = stationPhoto.creationdate

        rowStationPhoto.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.cl_row_station_photo -> listener.onStationPhotoSelected(adapterPosition)
        }
    }


    interface OnClick {
        fun onStationPhotoSelected(position: Int)
    }

}