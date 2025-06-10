package com.example.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tetron.all_task.viewmodel.TaskViewModel
import com.tetron.presentaton.databinding.DialogOnTaskBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomSheetDialogOnTask(): BottomSheetDialogFragment() {
    private val binding: DialogOnTaskBinding by lazy {
        DialogOnTaskBinding.inflate(layoutInflater)
    }

    private val viewModelTask: TaskViewModel by viewModel<TaskViewModel>(


    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.buttonDel.setOnClickListener {

        }

        binding.buttonCheck.setOnClickListener {

            dismiss()
        }
    }
}