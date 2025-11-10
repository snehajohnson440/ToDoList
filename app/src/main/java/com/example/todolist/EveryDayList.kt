package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTodoBinding

class EveryDayList(
    private val onPress : (ListItem) -> Boolean,
) : ListAdapter<ListItem, EveryDayList.ToDoListViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ListItem>() {
            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
                oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EveryDayList.ToDoListViewHolder {
        return ToDoListViewHolder(ItemTodoBinding.inflate(LayoutInflater
            .from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: EveryDayList.ToDoListViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class ToDoListViewHolder(private val binding : ItemTodoBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindData(item : ListItem){
            binding.note.text = item.content
            binding.time.text = item.time?.let {
                android.text.format.DateFormat.format("hh:mm a", it)
            } ?: ""

            binding.root.setOnLongClickListener {
                onPress(item)
            }
        }

    }
}