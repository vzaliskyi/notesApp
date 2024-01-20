package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.NoteItemBinding

class NotesListAdapter(private val context: Context, private val onNoteClicked:(Int) -> Unit)
    : ListAdapter<Note, NotesListAdapter.NoteViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }
        }
    }

    class NoteViewHolder(private var binding: NoteItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(note: String){
            binding.noteTextView.text = note
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        return NoteViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.itemView.setOnClickListener(){
            onNoteClicked.invoke(getItem(position).id)
        }

        holder.bind(getItem(position).noteText)
    }
}