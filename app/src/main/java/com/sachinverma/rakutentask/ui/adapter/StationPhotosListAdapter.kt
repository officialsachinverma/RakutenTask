package com.sachinverma.rakutentask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sachinverma.rakutentask.model.StationPhoto
import com.sachinverma.rakutentask.ui.adapter.viewholder.StationPhotoListViewHolder

/**
 * Created by Sachin Verma on 2020-02-09.
 */
class StationPhotosListAdapter (private val view: Int, private val listener: StationPhotoListViewHolder.OnClick):
    ListAdapter<StationPhoto, StationPhotoListViewHolder>(StationPhotoDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = StationPhotoListViewHolder(LayoutInflater.from(parent.context).inflate(view, parent, false), listener)

    override fun onBindViewHolder(holder: StationPhotoListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class StationPhotoDiffUtil: DiffUtil.ItemCallback<StationPhoto>() {

        override fun areItemsTheSame(oldItem: StationPhoto, newItem: StationPhoto): Boolean {
            return oldItem.creationdate == newItem.creationdate
        }

        override fun areContentsTheSame(oldItem: StationPhoto, newItem: StationPhoto): Boolean {
            return oldItem == newItem
        }

    }


}