package com.example.holamundoapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.graphics.Bitmap
import android.provider.MediaStore.Images
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import java.io.OutputStream


class MainActivity : AppCompatActivity() {

    lateinit var mShare : Button

    private val permiso = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this@MainActivity,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    permiso)


            }
        }



        // Compartir Gelatina

        mShare = findViewById(R.id.compartirgf)

        mShare.setOnClickListener {

            val gf = BitmapFactory.decodeResource(resources, R.drawable.gf)
            val compartirgf = Intent(Intent.ACTION_SEND)
            compartirgf.type = "image/jpeg"

            val valoresgf = ContentValues()
            valoresgf.put(Images.Media.TITLE, "Gelatina de Fresa")
            valoresgf.put(Images.Media.MIME_TYPE, "image/jpeg")
            val urigf = contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                valoresgf
            )


            val outstreamgf: OutputStream?
            try {
                outstreamgf = contentResolver.openOutputStream(urigf!!)
                gf.compress(Bitmap.CompressFormat.JPEG, 100, outstreamgf)
                outstreamgf!!.close()
            } catch (e: Exception) {
                System.err.println(e.toString())
            }


            compartirgf.putExtra(Intent.EXTRA_STREAM, urigf)
            startActivity(Intent.createChooser(compartirgf, getString(R.string.compartir_gf_txt)))
        }


        // Compartir Torta de Chocolate

        mShare = findViewById(R.id.compartirtc)

        mShare.setOnClickListener {

            val tc = BitmapFactory.decodeResource(resources, R.drawable.tc)
            val compartirtc = Intent(Intent.ACTION_SEND)
            compartirtc.type = "image/jpeg"

            val valorestc = ContentValues()
            valorestc.put(Images.Media.TITLE, "Gelatina de Fresa")
            valorestc.put(Images.Media.MIME_TYPE, "image/jpeg")
            val uritc = contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                valorestc
            )


            val outstreamtc: OutputStream?
            try {
                outstreamtc = contentResolver.openOutputStream(uritc!!)
                tc.compress(Bitmap.CompressFormat.JPEG, 100, outstreamtc)
                outstreamtc!!.close()
            } catch (e: Exception) {
                System.err.println(e.toString())
            }


            compartirtc.putExtra(Intent.EXTRA_STREAM, uritc)
            startActivity(Intent.createChooser(compartirtc, getString(R.string.compartir_tc_txt)))
        }


    }
}
