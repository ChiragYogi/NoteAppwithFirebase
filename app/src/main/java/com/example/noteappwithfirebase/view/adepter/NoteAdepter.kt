package com.example.noteappwithfirebase.view.adepter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappwithfirebase.databinding.ItemNoteBinding
import com.example.noteappwithfirebase.model.Note


class NoteAdepter(): RecyclerView.Adapter<NoteAdepter.MyViewHolder>() {

    private var data: List<Note> = ArrayList()

    class MyViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

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
        val currentItem = data[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun swapData(data:List<Note>){
        this.data = data
        notifyDataSetChanged()
    }

}
