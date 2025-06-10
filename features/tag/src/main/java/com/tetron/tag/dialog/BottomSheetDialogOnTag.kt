package com.tetron.tag.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelStoreOwner
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tetron.domain_models.Tag
import com.tetron.presentaton.databinding.DialogOnTagDelBinding
import com.tetron.tag.viewmodel.TagViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomSheetDialogOnTag(
    private val viewModelStoreOwner: ViewModelStoreOwner,
    private val tag: Tag
): BottomSheetDialogFragment() {
    private val binding: DialogOnTagDelBinding by lazy {
        DialogOnTagDelBinding.inflate(layoutInflater)
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
        binding.title.text = tag.name

        binding.buttonDel.setOnClickListener {
            viewModel.deleteTagById(tag.id!!)
            dismiss()
        }
    }
}