package com.example.noteappwithfirebase.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noteappwithfirebase.MainActivity
import com.example.noteappwithfirebase.databinding.FragmentUpdateNote2Binding
import com.example.noteappwithfirebase.model.Note


class UpdateNoteFragment : Fragment() {


    private var _binding:FragmentUpdateNote2Binding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentUpdateNote2Binding.bind(view)
        viewModel = (activity as MainActivity).viewModel

        val args = arguments?

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

    fun validatingInput(title: String,info: String): Boolean{
        return !(title.isEmpty() || info.isEmpty())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}