package com.rasyidin.screeningtestsuitmedia.ui.adapter

import com.rasyidin.screeningtestsuitmedia.R
import com.rasyidin.screeningtestsuitmedia.data.model.Guest
import com.rasyidin.screeningtestsuitmedia.databinding.ItemGuestBinding
import com.rasyidin.screeningtestsuitmedia.utils.setImage

class GuestAdapter : BaseAdapter<Guest>(R.layout.item_guest) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemData = data[position]
        val binding = ItemGuestBinding.bind(holder.itemView)
        with(binding) {
            imgGuest.setImage(holder.itemView.context, itemData.image)

            root.setOnClickListener {
                onItemClickListener?.invoke(itemData)
            }
        }
    }
}