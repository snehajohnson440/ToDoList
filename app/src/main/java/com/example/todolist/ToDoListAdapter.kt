package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.size
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.ToDoListAdapter.Companion.diffCallback
import com.example.todolist.databinding.ItemTodoBinding

class ToDoListAdapter(
    private val onPress : (ListItem) -> Boolean,
    private val onClick : (ListItem) -> Unit
  ) : ListAdapter<ListItem, ToDoListAdapter.ToDoListViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ListItem>() {
            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
                oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListAdapter.ToDoListViewHolder {
        return ToDoListViewHolder(ItemTodoBinding.inflate(LayoutInflater
            .from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ToDoListAdapter.ToDoListViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class ToDoListViewHolder(private val binding : ItemTodoBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindData(item : ListItem){
            binding.note.text = item.content
            binding.time.text = item.time?.let {
                android.text.format.DateFormat.format("hh:mm a", it)
            } ?: ""

            if (item.marked) {
                binding.card.setCardBackgroundColor(
                    binding.card.context.getColor(R.color.gray)
                )
            } else {
                binding.root.setCardBackgroundColor(
                    binding.root.context.getColor(R.color.highPriority)
                )
            }

            binding.root.setOnLongClickListener {
                onPress(item)
            }
            binding.root.setOnClickListener {
                onClick(item)
            }
        }

    }
}