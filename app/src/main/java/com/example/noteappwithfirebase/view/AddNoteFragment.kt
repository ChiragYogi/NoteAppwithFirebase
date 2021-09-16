package com.example.noteappwithfirebase.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

import android.view.View

import androidx.annotation.RequiresApi

import androidx.navigation.fragment.findNavController
import com.example.noteappwithfirebase.MainActivity
import com.example.noteappwithfirebase.R
import com.example.noteappwithfirebase.databinding.FragmentAddNoteBinding
import com.example.noteappwithfirebase.model.Note
import com.example.noteappwithfirebase.utilles.validate.validatingInput

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



class AddNoteFragment : Fragment(R.layout.fragment_add_note) {

        private var _binding:FragmentAddNoteBinding? = null
        private val binding get() = _binding!!
        private lateinit var viewModel: NoteViewModel

       @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAddNoteBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel

        val date = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a")
        val timeNow = date.format(formatter)
        binding.addNoteLayout.cratedAtTimeTxT.text = timeNow
        Log.d("FirebaseLog", timeNow)
        binding.addNoteButton.setOnClickListener {
            addNoteToFirebase()
        }

    }

    private fun addNoteToFirebase() {
        val title = binding.addNoteLayout.noteTitle.text.toString()
        val info = binding.addNoteLayout.noteInfoEdt.text.toString()
        val cratedTime = binding.addNoteLayout.cratedAtTimeTxT.text.toString()

        if (validatingInput(title,info)){
            val note = Note(title,info,cratedTime)
            viewModel.addNote(note)

            findNavController().navigateUp()

        }else{
            binding.addNoteLayout.apply {
                noteTitle.error = "Please Add Title"
                noteInfo.error = "Please Enter some Info "
            }

        }


    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





}