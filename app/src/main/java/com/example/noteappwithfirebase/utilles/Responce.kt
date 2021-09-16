package com.example.noteappwithfirebase.utilles

import com.example.noteappwithfirebase.model.Note

data class Responce (
    var notes: List<Note>? = null,
     var exception: Exception? = null
)