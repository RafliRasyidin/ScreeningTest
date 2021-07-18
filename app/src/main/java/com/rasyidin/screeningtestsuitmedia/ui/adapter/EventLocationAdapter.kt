package com.rasyidin.screeningtestsuitmedia.ui.adapter

import com.rasyidin.screeningtestsuitmedia.R
import com.rasyidin.screeningtestsuitmedia.data.model.Event
import com.rasyidin.screeningtestsuitmedia.databinding.ItemEventLocationBinding
import com.rasyidin.screeningtestsuitmedia.utils.setImageWithUrl

class EventLocationAdapter : BaseAdapter<Event>(R.layout.item_event_location) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val event = data[position]
        val binding = ItemEventLocationBinding.bind(holder.itemView)
        with(binding) {
            imgEventLocation.setImageWithUrl(holder.itemView.context, event.imageEvent)

            root.setOnClickListener {
                onItemClickListener?.invoke(event)
            }
        }
    }
}