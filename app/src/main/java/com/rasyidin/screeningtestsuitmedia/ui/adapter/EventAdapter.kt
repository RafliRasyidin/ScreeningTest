package com.rasyidin.screeningtestsuitmedia.ui.adapter

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

            root.setOnClickListener {
                onItemClickListener?.invoke(event)
            }
        }
    }
}