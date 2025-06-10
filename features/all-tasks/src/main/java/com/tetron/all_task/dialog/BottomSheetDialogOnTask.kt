package com.tetron.all_task.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelStoreOwner
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tetron.all_task.viewmodel.TaskViewModel
import com.tetron.domain_models.Task
import com.tetron.presentaton.databinding.DialogOnTaskBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomSheetDialogOnTask(
    private val viewModelStoreOwner: ViewModelStoreOwner,
    private val task: Task
): BottomSheetDialogFragment() {
    private val binding: DialogOnTaskBinding by lazy {
        DialogOnTaskBinding.inflate(layoutInflater)
    }

    private val viewModelTask: TaskViewModel by viewModel<TaskViewModel>(
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
        binding.title.text = task.title

        binding.buttonDel.setOnClickListener {
            viewModelTask.deleteTask(task.id)
            dismiss()
        }

        binding.buttonCheck.setOnClickListener {
            viewModelTask.checkTask(task.id, true)
            dismiss()
        }
    }
}