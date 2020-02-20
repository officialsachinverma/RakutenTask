package com.sachinverma.rakutentask.ui.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachinverma.rakutentask.R
import com.sachinverma.rakutentask.model.NearStations
import com.sachinverma.rakutentask.ui.adapter.StationPhotosListAdapter
import com.sachinverma.rakutentask.ui.adapter.viewholder.StationPhotoListViewHolder
import com.sachinverma.rakutentask.ui.viewmodel.StationPhotoListViewModel
import com.sachinverma.rakutentask.ui.viewmodel.factory.StationPhotosViewModelFactory
import com.sachinverma.rakutentask.util.Constants
import kotlinx.android.synthetic.main.activity_station_photos_list.*


class StationPhotosListActivity : AppCompatActivity(), StationPhotoListViewHolder.OnClick {

    private lateinit var viewModel: StationPhotoListViewModel
    private lateinit var adapter: StationPhotosListAdapter
    private var busStation: NearStations? = null

    companion object{
        fun start(context: Context, busStation: NearStations): Intent{
            val intent = Intent(context, StationPhotosListActivity::class.java)
            intent.putExtra("busStation", busStation)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_photos_list)

        init()
    }

    private fun init() {

        getDataFromIntent()

        busStation?.let {
            title = it.street_name
            val viewModelFactory = StationPhotosViewModelFactory(this, it.street_name)
            viewModel = ViewModelProvider(this, viewModelFactory).get(StationPhotoListViewModel::class.java)
        }

        initAdapter()

        observeForObservable()
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.station_photo_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.item_add_station_photo -> {
                launchCameraActivity()
                true
            }
            else -> false
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            Constants.Permission.CAMERA_PERMISSION_REQUEST_CODE -> {
                if (isStoragePermissionGiven())
                    launchCameraActivity()
            }
        }
    }

    private fun launchCameraActivity() {
        if (isStoragePermissionGiven()) {
            startActivity(ImageActivity.start(this))
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                Constants.Permission.CAMERA_PERMISSION_REQUEST_CODE)
        }
    }

    private fun getDataFromIntent() {
        if (intent.hasExtra("busStation"))
            busStation = intent.getParcelableExtra("busStation")
    }

    private fun initAdapter() {
        adapter = StationPhotosListAdapter(R.layout.item_station_photos, this)
        rv_stations_photos_list.apply {
            layoutManager = LinearLayoutManager(this@StationPhotosListActivity)
            adapter = this@StationPhotosListActivity.adapter
        }
    }

    private fun observeForObservable() {
        viewModel.stationPhotoList.observe(this, Observer {
            hideWaitingSign()
            if (it.isNotEmpty()) {
                hideEmptyListMsg()
                showStationPhotosList()
                adapter.submitList(it)
            } else {
                hideStationPhotosList()
                showEmptyListMsg()
            }
        })
    }

    private fun isStoragePermissionGiven(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        }
        return true
    }

    /**
     * hides empty list msg
     */
    private fun hideEmptyListMsg() {
        tv_no_stations_photos_found_msg.visibility = View.GONE
    }

    /**
     * shows empty list msg
     */
    private fun showEmptyListMsg() {
        tv_no_stations_photos_found_msg.visibility = View.VISIBLE
    }

    /**
     * hides video list
     */
    private fun hideStationPhotosList() {
        rv_stations_photos_list.visibility = View.GONE
    }

    /**
     * shows video list
     */
    private fun showStationPhotosList() {
        rv_stations_photos_list.visibility = View.VISIBLE
    }

    /**
     * hides waiting sign
     */
    private fun hideWaitingSign() {
        pg_station_photos_waiting.visibility = View.GONE
    }

    /**
     * shows waiting sign
     */
    private fun showWaitingSign() {
        pg_station_photos_waiting.visibility = View.VISIBLE
    }

    override fun onStationPhotoSelected(position: Int) {

    }

}
