package com.sachinverma.rakutentask.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.sachinverma.rakutentask.R
import com.sachinverma.rakutentask.database.CursorFactory
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.ByteArrayOutputStream


class ImageActivity : AppCompatActivity() {

    private var imagePath = ""

    companion object{
        fun start(context: Context) = Intent(context, ImageActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)


        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            100 -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.extras?.let {
                        val bitmap = it.get("data") as Bitmap
                        captured_image.setImageBitmap(bitmap)

                        imagePath = getImageUri(bitmap).toString()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.image_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.item_save -> {
                    CursorFactory.saveImageTitle(this, image_title.text.toString().trim(), imagePath)
                    finish()
                true
            }
            else -> false
        }

    private fun getImageUri(photo: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path: String = MediaStore.Images.Media.insertImage(applicationContext.contentResolver, photo, "Title", null)
        return Uri.parse(path)
    }
}
