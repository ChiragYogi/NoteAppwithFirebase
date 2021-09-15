package com.example.noteappwithfirebase.view

import androidx.lifecycle.*
import com.example.noteappwithfirebase.model.Note
import com.example.noteappwithfirebase.repo.NoteReposetry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private val repo: NoteReposetry): ViewModel() {


   fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updatedNote(note)
        }

    }


    val responseLiveData = liveData(Dispatchers.IO) {
        emit(repo.readNoteFromFireBase())
    }
}