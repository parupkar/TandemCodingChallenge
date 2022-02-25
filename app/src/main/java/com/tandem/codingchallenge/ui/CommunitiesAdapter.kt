package com.tandem.codingchallenge.ui

import android.graphics.Color.GREEN
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tandem.codingchallenge.data.models.CommunityData
import com.tandem.codingchallenge.databinding.ItemPassengerBinding

class CommunitiesAdapter :
    PagingDataAdapter<CommunityData, CommunitiesAdapter.PassengersViewHolder>(PassengersComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PassengersViewHolder {
        return PassengersViewHolder(
            ItemPassengerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PassengersViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindPassenger(it) }
    }

    inner class PassengersViewHolder(private val binding: ItemPassengerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPassenger(item: CommunityData) = with(binding) {
            logo.loadImage(item.pictureUrl)
            firstNameTV.text = item.firstName
            topicTV.text = item.topic
            referenceCountTV.text = if (item.referenceCnt > 0) {
                item.referenceCnt.toString()
            } else {
                referenceCountTV.setTextColor(GREEN)
                "NEW"
            }
            nativeTV.text = item.natives.joinToString()
            learnsTV.text = item.learns.joinToString()
            likeBtn.setOnClickListener {
                likeBtn.setColorFilter(GREEN)
            }
        }
    }

    object PassengersComparator : DiffUtil.ItemCallback<CommunityData>() {
        override fun areItemsTheSame(oldItem: CommunityData, newItem: CommunityData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CommunityData, newItem: CommunityData): Boolean {
            return oldItem == newItem
        }
    }
}