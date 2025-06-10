package com.example.notes.fragments


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.notes.databinding.DialogImageBinding
import com.example.notes.databinding.NoteFragmentBinding
import com.example.notes.fragments.viewmodel.CreateNoteViewModel
import com.tetron.domain_models.NoteId
import com.tetron.presentaton.interfaces.activity.Navigation
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyDialogFragment(photo: String?) : DialogFragment() {


    private var _photo = photo


    private val bindingDialog: DialogImageBinding by lazy {
        DialogImageBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = bindingDialog.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingDialog.image.setImageBitmap(EditNote().baseToBitmap(_photo))
        bindingDialog.root.setOnClickListener {
            dialog?.dismiss()
        }

        // Установить обработчик нажатия для кнопки

    }
}

class EditNote : Fragment() {


    var idNote: Long = 0
    private val viewModelNode: CreateNoteViewModel by viewModel<CreateNoteViewModel>()

    //for add new photo
    //private val REQUEST_TAKE_PHOTO = 1
    private var uriPhoto: String? = null
    private val binding: NoteFragmentBinding by lazy {
        NoteFragmentBinding.inflate(layoutInflater)
    }


    companion object {
        fun newInstance(id: Long?) = EditNote().apply {
            this.idNote = id!!
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    fun baseToBitmap(imageString: String?): Bitmap? {
        if (!imageString.isNullOrEmpty()){
            val imageBytes = Base64.decode(imageString, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        }else{
            return null
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.header.headerTitle.text = "Редактрование"

        binding.header.headerButtonAction.setImageResource(com.tetron.presentaton.R.drawable.ic_task)

        binding.imageView.visibility=View.INVISIBLE

        lifecycleScope.launch {
            val a = viewModelNode.getNoteId(idNote)

            uriPhoto= a.photo
            if (uriPhoto != null) {
                binding.imageView.visibility=View.VISIBLE
                binding.imageView.setImageBitmap(baseToBitmap(uriPhoto))
            }

            binding.imageView.setOnClickListener {


                val dialog = MyDialogFragment(a.photo)
                dialog.show(requireActivity().supportFragmentManager, "my_dialog")

            }


            binding.edText.setText(a.name)
        }




        binding.header.headerButtonAction.setOnClickListener {

            lifecycleScope.launch {
                viewModelNode.changeNote(
                    NoteId(
                        id = idNote,
                        name = binding.edText.text.toString(),
                        photo = uriPhoto
                    )
                )
            }

            requireActivity().let {

                if (it is Navigation) it.main_addfragment(NoteListFragments())
            }

        }

    }


}