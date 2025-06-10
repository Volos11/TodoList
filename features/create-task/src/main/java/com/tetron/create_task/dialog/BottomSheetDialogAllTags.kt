package com.tetron.create_task.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tetron.create_task.viewmodel.CreateTaskViewModel
import com.tetron.presentaton.adapters.AllTagsRecyclerAdapter
import com.tetron.presentaton.databinding.DialogAllTagsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class BottomSheetDialogAllTags(
    private val viewModelStoreOwner: ViewModelStoreOwner
): BottomSheetDialogFragment() {
    private val binding: DialogAllTagsBinding by lazy {
        DialogAllTagsBinding.inflate(layoutInflater)
    }

    private val adapterRecycler: AllTagsRecyclerAdapter by lazy {
        AllTagsRecyclerAdapter(flagLightBackground = true) { tagItem ->
            viewModel.selectTagById(tagItem)
            dismiss()
        }
    }

    private val viewModel: CreateTaskViewModel by viewModel<CreateTaskViewModel>(
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
        binding.recyclerTag.adapter = adapterRecycler
        binding.recyclerTag.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAllTags()
        viewModel.dataListOfTags.observe(viewLifecycleOwner) {
            adapterRecycler.listOfTags = it
        }
    }
}