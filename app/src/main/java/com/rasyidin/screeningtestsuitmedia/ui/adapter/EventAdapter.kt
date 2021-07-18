package com.rasyidin.screeningtestsuitmedia.ui.adapter

import com.google.android.material.chip.Chip
import com.rasyidin.screeningtestsuitmedia.R
import com.rasyidin.screeningtestsuitmedia.data.model.Event
import com.rasyidin.screeningtestsuitmedia.databinding.ItemEventBinding
import com.rasyidin.screeningtestsuitmedia.utils.setImage

class EventAdapter : BaseAdapter<Event>(R.layout.item_event) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val event = data[position]
        val binding = ItemEventBinding.bind(holder.itemView)
        with(binding) {
            tvTitleEvent.text = event.name
            tvDate.text = event.date
            imgEvent.setImage(holder.itemView.context, event.image)

            for (index in event.hashtag.indices) {
                val chip = Chip(chipGroupHashtag.context)
                chip.text = event.hashtag[index]
                chip.isCheckable = false
                chip.isClickable = false
                chipGroupHashtag.addView(chip)
            }

            root.setOnClickListener {
                onItemClickListener?.invoke(event)
            }
        }
    }
}