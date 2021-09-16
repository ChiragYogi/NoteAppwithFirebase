package com.example.noteappwithfirebase.repo

import android.util.Log
import com.example.noteappwithfirebase.utilles.Responce
import com.example.noteappwithfirebase.model.Note
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import kotlinx.coroutines.tasks.await
import java.util.*

class NoteReposetry {

    private val database:DatabaseReference = FirebaseDatabase.getInstance().reference



    fun addNote(note: Note) {
        try {
            database.child(note.createdAt.toString()).setValue(note)
            Log.d("FirebaseLog", "$note")
        } catch (e: Exception) {
            Log.d("FirebaseLog", "$e")
        }
    }


     suspend fun readNoteFromFireBase(): Responce {

        val response = Responce()


         try {
            response.notes = database.get().await().children.map {
                it.getValue(Note::class.java)!!
            }
         }catch (e: Exception){
             response.exception = e
         }

        return response
    }



    fun updatedNote(note: Note) {


      try {
            val updatedNote = mapOf(
                 "title" to note.title,
                 "info" to note.info,    )
              database.child(note.createdAt.toString()).setValue(updatedNote)

            Log.d("FirebaseLog", "$updatedNote in update")
        }catch (e: Exception){
            Log.d("FirebaseLog", "$e")
        }

    }

    fun deleteNote(note: Note){
          try {
            database.child(note.createdAt.toString()).removeValue()
        }catch (e : Exception){
            Log.d("FirebaseLog", "$e")
        }
    }


}