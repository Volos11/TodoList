package com.tetron.create_task.fragment

import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.tetron.presentaton.R
import com.tetron.create_task.dialog.BottomSheetDialogAllTags
import com.tetron.create_task.viewmodel.CreateTaskViewModel
import com.tetron.domain_models.TaskShort
import com.tetron.presentaton.databinding.CreateTaskFragmentBinding
import com.tetron.presentaton.interfaces.fragment.TitleSetter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DateFormat.getDateInstance
import java.util.Date

class CreateTaskFragment : Fragment(), TitleSetter {
    private val binding: CreateTaskFragmentBinding by lazy {
        CreateTaskFragmentBinding.inflate(layoutInflater)
    }

    private val viewModelCreateTask: CreateTaskViewModel by viewModel<CreateTaskViewModel>()

    private val datePicker by lazy {
        MaterialDatePicker.Builder.datePicker()
            .setTitleText("Укажите дату для задачи")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setTheme(R.style.Custom_DatePicker)
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initHeader()
        initDate()
        setClickDateOnDatePicker()
        setActionOnButton()

        binding.tag.setOnClickListener {
            BottomSheetDialogAllTags(this).show(requireActivity().supportFragmentManager, "Dialog")
        }

        viewModelCreateTask.dataCurrentTag.observe(viewLifecycleOwner) {
            binding.tagText.text = it.name
        }
    }

    override fun setTitle(title: String) {
        binding.header.headerTitle.text = title
    }

    override fun setActionOnButton() {
        binding.header.headerButtonAction.setOnClickListener {
            viewModelCreateTask.createNewTask(
                TaskShort(
                    title = binding.title.text.toString(),
                    description = binding.disc.text.toString(),
                    dateMill = datePicker.selection ?: MaterialDatePicker.todayInUtcMilliseconds()
                )
            )
            clearViews()
            clearViewModel()
        }
    }

    override fun setIcon() {
        binding.header.headerButtonAction.setImageIcon(
            Icon.createWithResource(requireActivity(), R.drawable.ic_check)
        )
    }

    private fun clearViews() {
        with(binding) {
            setDateFromLong(MaterialDatePicker.todayInUtcMilliseconds())
            title.text?.clear()
            disc.text?.clear()
        }
    }

    private fun clearViewModel() {
        viewModelCreateTask.clear()
    }

    private fun initHeader() {
        setTitle("Создание")
        setIcon()
    }

    private fun initDate() {
        setDateFromLong(MaterialDatePicker.todayInUtcMilliseconds())
        binding.data.setOnClickListener {
            if(datePicker.isVisible)
                return@setOnClickListener
            datePicker.show(requireActivity().supportFragmentManager, "Dialog")
        }
    }

    private fun setDateFromLong(dateLong: Long) {
        val formattedDate = getDateInstance().format(Date(dateLong))
        binding.data.text = formattedDate
    }

    private fun setClickDateOnDatePicker() {
        datePicker.addOnPositiveButtonClickListener {
            setDateFromLong(it)
        }
    }
}