package com.tetron.tag.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelStoreOwner
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tetron.domain_models.TagWithoutId
import com.tetron.presentaton.databinding.DialogCreateTagBinding
import com.tetron.tag.viewmodel.TagViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomSheetDialogCreateTag(
    private val viewModelStoreOwner: ViewModelStoreOwner
): BottomSheetDialogFragment() {
    private val binding: DialogCreateTagBinding by lazy {
        DialogCreateTagBinding.inflate(layoutInflater)
    }

    private val viewModel: TagViewModel by viewModel<TagViewModel>(
        ownerProducer = {
            viewModelStoreOwner
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonDel.setOnClickListener {
            viewModel.addNewTag(TagWithoutId(name = binding.title.text.toString()))
            dismiss()
        }
    }
}