package com.example.noteappwithfirebase.repo

import android.util.Log
import com.example.noteappwithfirebase.Responce
import com.example.noteappwithfirebase.model.Note
import com.google.firebase.database.*
import kotlinx.coroutines.tasks.await

class NoteReposetry {

    private val database:DatabaseReference = FirebaseDatabase.getInstance().reference
    private val reference = database.child("MyNotes")



    fun addNote(note: Note) {
        try {
            database.child("MyNotes").push().setValue(note)
        } catch (e: Exception) {
            Log.d("FirebaseLog", "$e")
        }
    }


     suspend fun readNoteFromFireBase(): Responce{

        val response = Responce()

         try {
            response.notes = reference.get().await().children.map {
                it.getValue(Note::class.java)!!
            }
         }catch (e: Exception){
             response.exception = e
         }

        return response
    }

    fun updatedNote(note: Note) {

        val key = database.child("MyNotes").key
        try {
            val updatedNote = mapOf(
                 "title" to note.title,
                 "info" to note.info,
                 "createdAt" to note.createdAt
            )
            database.child("MyNotes").setValue(updatedNote)
        }catch (e: Exception){

        }

    }


}