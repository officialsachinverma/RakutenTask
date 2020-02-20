package com.sachinverma.rakutentask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sachinverma.rakutentask.model.NearStations
import com.sachinverma.rakutentask.ui.adapter.viewholder.BusStationListViewHolder

/**
 * Created by Sachin Verma on 2020-02-09.
 */

class BusStationListAdapter(private val view: Int, private val listener: BusStationListViewHolder.OnClick):
    ListAdapter<NearStations, BusStationListViewHolder>(BusStationDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = BusStationListViewHolder(LayoutInflater.from(parent.context).inflate(view, parent, false), listener)

    override fun onBindViewHolder(holder: BusStationListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun getItemAtPostition(position: Int) = getItem(position)

    class BusStationDiffUtil: DiffUtil.ItemCallback<NearStations>() {

        override fun areItemsTheSame(oldItem: NearStations, newItem: NearStations): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NearStations, newItem: NearStations): Boolean {
            return oldItem == newItem
        }

    }

}