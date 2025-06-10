package com.tetron.all_task.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tetron.presentaton.databinding.DialogOpenTextTaskBinding

internal class BottomSheetDialogOpenTextTask(
    private val textOfTask: String
): BottomSheetDialogFragment() {
    private val binding: DialogOpenTextTaskBinding by lazy {
        DialogOpenTextTaskBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.title.text = textOfTask
    }
}