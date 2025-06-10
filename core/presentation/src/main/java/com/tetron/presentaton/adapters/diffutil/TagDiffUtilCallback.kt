package com.tetron.presentaton.adapters.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.tetron.domain_models.Tag

class TagDiffUtilCallback(
    private val oldList: List<Tag>,
    private val newList: List<Tag>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}