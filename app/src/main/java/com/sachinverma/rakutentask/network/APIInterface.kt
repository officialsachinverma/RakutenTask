package com.sachinverma.rakutentask.network

import com.sachinverma.rakutentask.model.Response
import com.sachinverma.rakutentask.util.Constants
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Sachin Verma on 2020-02-09.
 */

interface APIInterface {

    @GET(Constants.Network.apiBusStationList)
    fun doGetBusStationList(): Call<Response>

}