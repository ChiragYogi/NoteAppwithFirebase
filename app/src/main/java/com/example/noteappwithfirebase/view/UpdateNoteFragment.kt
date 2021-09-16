package com.example.noteappwithfirebase.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.noteappwithfirebase.MainActivity
import com.example.noteappwithfirebase.R
import com.example.noteappwithfirebase.databinding.FragmentUpdateNote2Binding
import com.example.noteappwithfirebase.model.Note
import com.example.noteappwithfirebase.utilles.validate.validatingInput


class UpdateNoteFragment : Fragment(R.layout.fragment_update_note2) {

    private var _binding:FragmentUpdateNote2Binding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentUpdateNote2Binding.bind(view)
        viewModel = (activity as MainActivity).viewModel

        val title = arguments?.getString("title")
        val info = arguments?.getString("info")
        val cratedAt = arguments?.getString("createdAt")
        Log.d("FirebaseLog","$title $info $cratedAt")

        binding.updateNoteLayout.apply {
            noteTitle.setText(title)
            noteInfoEdt.setText(info)
            cratedAtTimeTxT.text = cratedAt
        }

        binding.updateNoteButton.setOnClickListener {
            updateNoteToFireBase()
        }
    }

    private fun updateNoteToFireBase() {
        val title = binding.updateNoteLayout.noteTitle.text.toString()
        val info = binding.updateNoteLayout.noteInfoEdt.text.toString()
        val cratedTime = binding.updateNoteLayout.cratedAtTimeTxT.text.toString()

        if (validatingInput(title,info)){
            val note = Note(title,info,cratedTime)
            viewModel.updateNote(note)

            findNavController().navigateUp()

        }else{
            binding.updateNoteLayout.apply {
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