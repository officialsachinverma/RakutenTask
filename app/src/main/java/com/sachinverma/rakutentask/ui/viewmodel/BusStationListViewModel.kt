package com.sachinverma.rakutentask.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sachinverma.rakutentask.model.NearStations
import com.sachinverma.rakutentask.model.Response
import com.sachinverma.rakutentask.network.APIClient
import com.sachinverma.rakutentask.network.APIInterface
import com.sachinverma.rakutentask.util.Logger
import retrofit2.Call
import retrofit2.Callback

/**
 * Created by Sachin Verma on 2020-02-09.
 */

class BusStationListViewModel  : BaseViewModel() {


    var apiInterface: APIInterface? = null

    private var _busStationList = MutableLiveData<List<NearStations>>()
    val busStationList: LiveData<List<NearStations>>
        get() = _busStationList

    private val callback = object : Callback<Response> {

        override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
            Logger.i("network response : ${response.body()}")
            val temp = response.body() as Response
            _busStationList.value = temp.data.nearstations
        }

        override fun onFailure(call: Call<Response>, t: Throwable) {
            Logger.i("network onFailure : ${t.printStackTrace()}")
        }
    }

    init {
        loadData()
    }

    private fun loadData () {
        apiInterface = APIClient.getClient()!!.create(APIInterface::class.java)

        val call: Call<Response> = apiInterface!!.doGetBusStationList()

        call.enqueue(callback)
    }

}