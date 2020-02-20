package com.sachinverma.rakutentask.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * Created by Sachin Verma on 2020-02-09.
 */

open class BaseViewModel : ViewModel() {

    /**
     * Allows to cancel all assigned jobs for this ViewModel
     */
    private var coroutineJob = Job()
    /**
     * we are pass [viewModelJob], any coroutine started in this coroutineScope can be cancelled
     * by calling viewModelJob.cancel()
     */
    protected var ioScope = CoroutineScope(Dispatchers.IO + coroutineJob)


    override fun onCleared() {
        super.onCleared()
        coroutineJob.cancel()
    }
}