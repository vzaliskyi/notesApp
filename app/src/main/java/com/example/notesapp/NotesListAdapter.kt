package com.example.notesapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.NoteItemBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class NotesListAdapter(private val context: Context, private val onNoteClicked:() -> Unit)
    : ListAdapter<Int, NotesListAdapter.NoteViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
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
        //Log.d("MainActivity", "View Holder was created.")
        val noteViewHolder =  NoteViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        noteViewHolder.itemView.setOnClickListener(){
            onNoteClicked.invoke()
        }

        return noteViewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        //Log.d("MainActivity", "${getItem(position)}")
        holder.bind(context.getString(getItem(position)))
    }
}