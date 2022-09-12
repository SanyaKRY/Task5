package com.example.task5.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.task5.data.Cat
import com.example.task5.databinding.FragmentCatBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.time.LocalDateTime

class CatFragment : Fragment() {

    private var _binding: FragmentCatBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val args: CatFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCatBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.apply {
            Glide.with(view.context).load(args.cat?.imageUrl).into(catItemImageView)
            catItemTextView.text = args.cat?.id
        }

        binding.button.setOnClickListener { saveImage(args.cat!!) }

        return view
    }

    fun saveImage(cat: Cat) {
        if (isPermission()) {
            saveImageToGallery(cat)
        } else {
            askPermission()
        }
    }

    private fun isPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun askPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            REQUEST_CODE
        )
    }

    private fun saveImageToGallery(cat: Cat) {
        val filename = cat.imageUrl?.removePrefix("https://cdn2.thecatapi.com/images/")
        val bitmap: Bitmap = binding.catItemImageView.drawable.toBitmap()

        saveMediaToStorage(activity?.applicationContext, bitmap, filename)
    }

    fun saveMediaToStorage(context: Context?, bitmap: Bitmap, filename: String?) {
        var outputStream: OutputStream? = null

        val dir = File(Environment.getExternalStorageDirectory(), "Pictures/Cats")
        if (!dir.exists()) {
            dir.mkdir()
        }

        val file = File(dir, filename ?: "${System.currentTimeMillis()}.jpg")

        try {
            outputStream = FileOutputStream(file)
            outputStream.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, REQUEST_CODE, it)
                Toast.makeText(context, "Successfully Saved", Toast.LENGTH_SHORT).show()
                it?.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val REQUEST_CODE: Int = 100
    }
}