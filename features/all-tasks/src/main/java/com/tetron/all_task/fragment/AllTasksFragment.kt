package com.tetron.all_task.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tetron.all_task.dialog.BottomSheetDialogOnTask
import com.tetron.all_task.dialog.BottomSheetDialogOpenTextTask
import com.tetron.all_task.viewmodel.TaskViewModel
import com.tetron.presentaton.adapters.AllTaskRecyclerAdapter
import com.tetron.presentaton.databinding.AllTasksFragmentBinding
import com.tetron.presentaton.interfaces.activity.Navigation
import com.tetron.presentaton.interfaces.fragment.TitleSetter
import org.koin.androidx.viewmodel.ext.android.viewModel


class AllTasksFragment : Fragment(), TitleSetter {
    private val binding: AllTasksFragmentBinding by lazy {
        AllTasksFragmentBinding.inflate(layoutInflater)
    }

    private val viewModel: TaskViewModel by viewModel<TaskViewModel>()

    private val adapterRecycler: AllTaskRecyclerAdapter by lazy {
        AllTaskRecyclerAdapter(
            onClickItem = {
                BottomSheetDialogOpenTextTask(it.description)
                    .show(requireActivity().supportFragmentManager, "Dialog")
            },
            onLongClickItem = {
                BottomSheetDialogOnTask(this, it)
                    .show(requireActivity().supportFragmentManager, "Dialog")
            },
            onClickCheck = { id, status ->
                viewModel.checkTask(id, status)
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setTitle("Задачи")
        setActionOnButton()

        binding.button.setOnClickListener {
            openCreate()
        }

        binding.recyclerTasks.adapter = adapterRecycler
        binding.recyclerTasks.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getAllTasks()

        viewModel.dataListOfTasks.observe(viewLifecycleOwner) {listOfTasks ->
            adapterRecycler.listOfTasks = listOfTasks
            changeVisibility(listOfTasks.isEmpty())
        }
    }

    override fun setActionOnButton() {
        binding.header.headerButtonAction.setOnClickListener {
            openCreate()
        }
    }

    override fun setTitle(title: String) {
        binding.header.headerTitle.text = title
    }

    private fun openCreate() {
        requireActivity().let { if (it is Navigation) it.moveOnCreateNewTask() }
    }

    private fun changeVisibility(flagListEmpty: Boolean) {
        binding.recyclerTasks.visibility = if(flagListEmpty) View.GONE else View.VISIBLE
        binding.textOnEmptyList.visibility = if(flagListEmpty) View.VISIBLE else View.GONE
        binding.button.visibility = if(flagListEmpty) View.VISIBLE else View.GONE
    }
}
