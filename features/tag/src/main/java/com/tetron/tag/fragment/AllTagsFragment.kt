package com.tetron.tag.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tetron.presentaton.adapters.AllTagsRecyclerAdapter
import com.tetron.presentaton.databinding.AllTagsFragmentBinding
import com.tetron.presentaton.interfaces.fragment.TitleSetter
import com.tetron.tag.dialog.BottomSheetDialogCreateTag
import com.tetron.tag.dialog.BottomSheetDialogOnTag
import com.tetron.tag.viewmodel.TagViewModel

import org.koin.androidx.viewmodel.ext.android.viewModel

class AllTagsFragment : Fragment(), TitleSetter {
    private val binding: AllTagsFragmentBinding by lazy {
        AllTagsFragmentBinding.inflate(layoutInflater)
    }

    private val viewModel: TagViewModel by viewModel<TagViewModel>()

    private val adapterRecycler: AllTagsRecyclerAdapter by lazy {
        AllTagsRecyclerAdapter { tagItem ->
            BottomSheetDialogOnTag(this, tagItem)
                .show(requireActivity().supportFragmentManager, "Dialog")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setTitle("Теги")
        setActionOnButton()

        binding.recyclerTag.adapter = adapterRecycler
        binding.recyclerTag.layoutManager = LinearLayoutManager(requireContext())

        viewModel.data.observe(viewLifecycleOwner) {
            adapterRecycler.listOfTags = it
        }

        viewModel.getAllTags()
    }

    override fun setTitle(title: String) {
        binding.header.headerTitle.text = title
    }

    override fun setActionOnButton() {
        binding.header.headerButtonAction.setOnClickListener {
            BottomSheetDialogCreateTag(this)
                .show(requireActivity().supportFragmentManager, "Dialog")
        }
    }

}