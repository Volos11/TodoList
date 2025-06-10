package com.tetron.presentaton.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tetron.domain_models.Tag
import com.tetron.presentaton.R
import com.tetron.presentaton.adapters.diffutil.TagDiffUtilCallback
import com.tetron.presentaton.databinding.ItemTagBinding

class AllTagsRecyclerAdapter (
    private val flagLightBackground: Boolean = false,
    private val onClickItem: (tagItem: Tag) -> Unit
) : RecyclerView.Adapter<AllTagsRecyclerAdapter.TagsHolder>() {
    class TagsHolder(val binding: ItemTagBinding) : RecyclerView.ViewHolder(binding.root)

    var listOfTags: List<Tag> = emptyList()
        set(value) {
            val callback = TagDiffUtilCallback(
                oldList = field,
                newList = value
            )
            field = value
            DiffUtil
                .calculateDiff(callback)
                .dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTagBinding.inflate(inflater, parent, false)

        if (flagLightBackground) {
            binding.constraint.setBackgroundColor(parent.context.getColor(R.color.white))
            binding.mark.setBackgroundColor(parent.context.getColor(R.color.black))
            binding.tag.setTextColor(parent.context.getColor(R.color.black))
        }

        return TagsHolder(binding)
    }

    override fun onBindViewHolder(holder: TagsHolder, position: Int) {
        val tagItem = listOfTags[position]

        with(holder.binding) {
            tag.text = tagItem.name
            tag.setOnClickListener {
                onClickItem(tagItem)
            }
        }
    }

    override fun getItemCount(): Int = listOfTags.size
}