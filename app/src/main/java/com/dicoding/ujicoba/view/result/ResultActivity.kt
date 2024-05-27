package com.dicoding.ujicoba.view.result

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.ujicoba.helper.ImageClassifierHelper
import com.dicoding.ujicoba.databinding.ActivityResultBinding
import org.tensorflow.lite.task.vision.classifier.Classifications

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // TODO: Menampilkan hasil gambar, prediksi, dan confidence score.
        val imageUri = intent.getStringExtra(EXTRA_IMAGE_URI)
        if (imageUri != null) {
            val image = Uri.parse(imageUri)
            displayImage(image)

            val imageClassifierHelper = ImageClassifierHelper(
                context = this,
                classifierListener = object : ImageClassifierHelper.ClassifierListener {
                    override fun onError(errorMessage: String) {
                        Log.d(TAG, "Error: $errorMessage")
                    }

                    override fun onResults(results: List<Classifications>?, inferenceTime: Long) {
                        results?.let { Results(it) }
                    }
                }
            )
            imageClassifierHelper.classifyStaticImage(image)
        } else {
            Log.e(TAG, "No image URI provided")
            finish()
        }

    }

    private fun Results(results: List<Classifications>) {
        val topResult = results[0]
        val label = topResult.categories[0].label
        val score = topResult.categories[0].score

        fun Float.formatToString(): String {
            return String.format("%.2f%%", this * 100)
        }
        binding.resultText.text = "$label ${score.formatToString()}"
    }

    private fun displayImage(uri: Uri) {
        Log.d(TAG, "Displaying image: $uri")
        binding.resultImage.setImageURI(uri)
    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val TAG = "main_activity"
    }
}