package com.sachinverma.rakutentask.ui.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sachinverma.rakutentask.ui.viewmodel.StationPhotoListViewModel

/**
 * Created by Sachin Verma on 2020-02-09.
 */

class StationPhotosViewModelFactory (private val context: Context, private val streetName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StationPhotoListViewModel::class.java)) {

            return StationPhotoListViewModel(context, streetName) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}