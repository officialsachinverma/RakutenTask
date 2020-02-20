package com.sachinverma.rakutentask.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore

/**
 * Created by Sachin Verma on 2020-02-09.
 */

object CursorFactory {


    fun getStationPhoto(context: Context, stationName: String): Cursor? {
        val projection = arrayOf(
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.DATE_TAKEN)
        val selection = "${MediaStore.Images.Media.DATA} LIKE \"%${stationName}%\""
        return context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )
    }

    fun saveImageTitle(context: Context, title: String, imagePath: String) {
        val contentValues = ContentValues()
        contentValues.put(MediaStore.Images.Media.TITLE, title)
        context.contentResolver.update(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues,
            "MediaStore.Images.Media.DATA = $imagePath",
            null)
    }

}