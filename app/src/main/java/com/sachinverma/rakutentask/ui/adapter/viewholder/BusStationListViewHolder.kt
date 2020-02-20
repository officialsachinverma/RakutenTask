package com.sachinverma.rakutentask.ui.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.sachinverma.rakutentask.R
import com.sachinverma.rakutentask.model.NearStations

/**
 * Created by Sachin Verma on 2020-02-09.
 */

class BusStationListViewHolder(itemView: View, private val listener: OnClick) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var tvBusStationName: TextView = itemView.findViewById(R.id.tv_bus_station_name)
    private var rowBusStation: ConstraintLayout = itemView.findViewById(R.id.cl_row_bus_station)

    fun bind(nearStation: NearStations) {

        tvBusStationName.text = nearStation.street_name

        rowBusStation.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.cl_row_bus_station -> listener.onBusStationSelected(adapterPosition)
        }
    }


    interface OnClick {
        fun onBusStationSelected(position: Int)
    }
}