package com.kigya.bsutools.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kigya.bsutools.databinding.ItemSubjectBinding
import com.kigya.bsutools.databinding.ItemSubjectUpdatedBinding
import com.kigya.bsutools.models.Row

class RowAdapter : ListAdapter<Row, RowAdapter.RowViewHolder>(DiffComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder =
        RowViewHolder(
            view = ItemSubjectUpdatedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun getItemCount(): Int = currentList.size

    class DiffComparator : DiffUtil.ItemCallback<Row>() {
        override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
            return oldItem == newItem
        }
    }

    inner class RowViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val viewBinding by viewBinding(ItemSubjectUpdatedBinding::bind)

        fun bind(row: Row) {
            viewBinding.apply {
                tvSubject.text = row.subject
                tvTime.text = row.time.replace("–", "\n")
                tvTeacher.text = row.teacher
                tvAudience.text = row.audience
                tvGroup.text = row.group
                tvType.text = row.type
                sampleSurroundCardView.apply {
                    setSurrounded(false)
                    animate()
                    surround()
                    setOnClickListener {
                        sampleSurroundCardView.surround()
                    }
                }
            }
        }
    }
}
