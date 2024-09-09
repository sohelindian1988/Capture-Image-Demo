package `in`.co.mohamedsohel.captureimagedemo

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var buttonCaptureImage: Button
    private lateinit var launcher:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView)
        buttonCaptureImage = findViewById(R.id.buttonCaptureImage)

launcher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
    result->
    if(result.resultCode == RESULT_OK)
    {
        val bundle: Bundle? =result.data?.extras
        val bitmap= bundle?.get("data") as Bitmap
        imageView.setImageBitmap(bitmap)
    }
}

        buttonCaptureImage.setOnClickListener {
            val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            launcher.launch(intent)
        }

    }
}