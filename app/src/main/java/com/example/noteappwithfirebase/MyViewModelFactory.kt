package com.example.noteappwithfirebase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteappwithfirebase.repo.NoteReposetry
import com.example.noteappwithfirebase.view.NoteViewModel


class MyViewModelFactory(private val repo: NoteReposetry): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteViewModel(repo) as T
    }
}