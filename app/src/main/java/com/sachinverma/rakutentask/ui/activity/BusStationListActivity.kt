package com.sachinverma.rakutentask.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachinverma.rakutentask.R
import com.sachinverma.rakutentask.ui.adapter.BusStationListAdapter
import com.sachinverma.rakutentask.ui.adapter.viewholder.BusStationListViewHolder
import com.sachinverma.rakutentask.ui.viewmodel.BusStationListViewModel
import com.sachinverma.rakutentask.util.UtilityFunctions
import kotlinx.android.synthetic.main.activity_bus_station_list.*

class BusStationListActivity : AppCompatActivity(), BusStationListViewHolder.OnClick {

    private lateinit var viewModel: BusStationListViewModel
    private lateinit var adapter: BusStationListAdapter

    companion object{
        fun start(context: Context) = Intent(context, BusStationListActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_station_list)

        if (UtilityFunctions.isNetworkAvailable(this))
            init()
        else {
            Toast.makeText(this, "No internet connectivity", Toast.LENGTH_SHORT).show()
            hideWaitingSign()
            hideEmptyListMsg()
            showEmptyListMsg()
        }
    }

    private fun init() {

        viewModel = ViewModelProvider(this).get(BusStationListViewModel::class.java)

        title = "Tag The Bus!"

        initAdapter()

        observeForObservable()
    }

    private fun initAdapter() {
        adapter = BusStationListAdapter(R.layout.item_bus_station, this)
        rv_bus_stations_list.apply {
            layoutManager = LinearLayoutManager(this@BusStationListActivity)
            adapter = this@BusStationListActivity.adapter
        }
    }

    private fun observeForObservable() {
        viewModel.busStationList.observe(this, Observer {
            hideWaitingSign()
            if (it.isNotEmpty()) {
                showBusStationList()
                hideEmptyListMsg()
                adapter.submitList(it)
            } else {
                hideBusStationList()
                showEmptyListMsg()
            }
        })
    }

    override fun onBusStationSelected(position: Int) {
        startActivity(StationPhotosListActivity.start(this, adapter.getItemAtPostition(position)))
    }


    /**
     * hides empty list msg
     */
    private fun hideEmptyListMsg() {
        tv_no_stations_found_msg.visibility = View.GONE
    }

    /**
     * shows empty list msg
     */
    private fun showEmptyListMsg() {
        tv_no_stations_found_msg.visibility = View.VISIBLE
    }

    /**
     * hides video list
     */
    private fun hideBusStationList() {
        rv_bus_stations_list.visibility = View.GONE
    }

    /**
     * shows video list
     */
    private fun showBusStationList() {
        rv_bus_stations_list.visibility = View.VISIBLE
    }

    /**
     * hides waiting sign
     */
    private fun hideWaitingSign() {
        pg_waiting.visibility = View.GONE
    }

    /**
     * shows waiting sign
     */
    private fun showWaitingSign() {
        pg_waiting.visibility = View.VISIBLE
    }

}
