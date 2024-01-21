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
    : ListAdapter<Note, NotesListAdapter.NoteViewHolder>(DiffCallback()) {


    class DiffCallback: DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }

    class NoteViewHolder(private var binding: NoteItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(noteText: String){
            binding.noteTextView.text = noteText
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
        val note = getItem(position)

        holder.itemView.setOnClickListener(){
            onNoteClicked.invoke(note.id)
        }

        holder.bind(note.noteText)
    }
}