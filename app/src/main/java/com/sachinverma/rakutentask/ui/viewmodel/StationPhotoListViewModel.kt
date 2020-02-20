package com.sachinverma.rakutentask.ui.viewmodel

import android.content.Context
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sachinverma.rakutentask.database.CursorFactory
import com.sachinverma.rakutentask.model.StationPhoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Sachin Verma on 2020-02-09.
 */

class StationPhotoListViewModel(private val context: Context, private val streetName: String) : BaseViewModel() {

    private var _stationPhotoList = MutableLiveData<List<StationPhoto>>()
    val stationPhotoList: LiveData<List<StationPhoto>>
        get() = _stationPhotoList

    private lateinit var stationPhotoArrayList: ArrayList<StationPhoto>

    init {
        loadData()
    }

    fun loadData() {

        stationPhotoArrayList = arrayListOf()

        ioScope.launch {

            val photosCursor = CursorFactory.getStationPhoto(context, streetName)

            photosCursor?.let {
                if (it.moveToFirst()) {
                    do {

                        val photoPath = it.getString(it.getColumnIndex(MediaStore.Images.Media.DATA))
                        val photoTitle = it.getString(it.getColumnIndex(MediaStore.Images.Media.TITLE))
                        val photoDateCreated = it.getString(it.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN))

                        stationPhotoArrayList.add(StationPhoto(photoPath, photoTitle, photoDateCreated))

                    } while (it.moveToNext())
                }
            }

            withContext(Dispatchers.Main)
            {
                _stationPhotoList.value = stationPhotoArrayList
            }
        }
    }

}