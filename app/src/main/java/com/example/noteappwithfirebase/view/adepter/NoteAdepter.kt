package com.example.noteappwithfirebase.view.adepter

import android.view.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappwithfirebase.databinding.ItemNoteBinding
import com.example.noteappwithfirebase.model.Note



class NoteAdepter(private val listener: OnItemClick) :
    ListAdapter<Note,NoteAdepter.MyViewHolder>(DiffUtillCallback()) {



    inner class MyViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            binding.apply {

                root.setOnClickListener {

                    val position = adapterPosition

                    if (position != RecyclerView.NO_POSITION) {
                        val note = getItem(position)
                        listener.onItemClick(note)
                    }
                }


            }
        }

        fun bind(item: Note) {
            binding.apply {
                noteTitle.text = item.title
                noteInfo.text = item.info
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }





    interface OnItemClick {

        fun onItemClick(note: Note)



    }

    class DiffUtillCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.createdAt == newItem.createdAt
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }


}

