package com.example.notes.fragments

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notes.databinding.NoteFragmentBinding
import com.example.notes.fragments.viewmodel.CreateNoteViewModel
import com.tetron.domain_models.NoteId
import com.tetron.presentaton.interfaces.activity.Navigation
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream


@Suppress("DEPRECATION")
class NoteFragment : Fragment() {

    private val viewModelNode: CreateNoteViewModel by viewModel<CreateNoteViewModel>()
    private val REQUEST_TAKE_PHOTO = 1
    private var uriPhoto: String? = null
    private val binding:  by lazy {
        NoteFragmentBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.header.headerTitle.text = "Создание"
        binding.header.headerButtonAction.setImageResource(com.tetron.presentaton.R.drawable.ic_task)


        binding.imageView.setOnClickListener {


            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            try {
                startActivityForResult(intent, REQUEST_TAKE_PHOTO)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }

        }




        binding.header.headerButtonAction.setOnClickListener {

            viewModelNode.createNote(
                NoteId(
                    name = binding.edText.text.toString(),
                    photo = uriPhoto
                )
            )
            requireActivity().let {

                if (it is Navigation) it.main_addfragment(NoteListFragments())
            }

        }
    }

    private fun bitmapToBase(bitmap: Bitmap?): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }


    private fun uriToBitmap(uri: Uri): Bitmap? {
        return try {
            val parcelFileDescriptor = requireContext().contentResolver.openFileDescriptor(uri, "r")
            val fileDescriptor = parcelFileDescriptor?.fileDescriptor
            if (fileDescriptor != null) {
                val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
                parcelFileDescriptor.close()
                image
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            // Фотка сделана, извлекаем миниатюру картинки
            val thumbnailBitmap = data?.data


            binding.imageView.visibility = View.VISIBLE
            binding.imageView.setImageURI(thumbnailBitmap)

            uriPhoto = bitmapToBase(uriToBitmap(thumbnailBitmap!!))
            uriPhoto

        }
    }


}