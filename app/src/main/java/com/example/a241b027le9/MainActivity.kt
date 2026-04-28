package com.example.a241b027le9


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var loadButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        loadButton = findViewById(R.id.loadButton)

        loadButton.setOnClickListener {
            val imageUrl = "annie-spratt-qzSMqWJyJBk-unsplash.jpg"
            loadImageFromUrl(imageUrl)
        }
    }

    private fun loadImageFromUrl(url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val connection = URL(url).openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val inputStream = connection.inputStream
                val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)

                withContext(Dispatchers.Main) { imageView.setImageBitmap(bitmap) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
