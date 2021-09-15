package com.example.noteappwithfirebase.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteappwithfirebase.MainActivity
import com.example.noteappwithfirebase.R
import com.example.noteappwithfirebase.databinding.NoteDisplayFragmentBinding
import com.example.noteappwithfirebase.model.Note
import com.example.noteappwithfirebase.view.adepter.NoteAdepter


class NoteDisplay : Fragment(R.layout.note_display_fragment) {

    private var _binding:NoteDisplayFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NoteViewModel
    private val mAdapte =  NoteAdepter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = NoteDisplayFragmentBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel

         binding.noteDisplayRv.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapte
        }

        getDataForRecyclerView()



        binding.addNoteFabButton.setOnClickListener {
        findNavController().navigate(R.id.action_noteDisplay_to_addNoteFragment)
    }


    }

    private fun getDataForRecyclerView() {

        viewModel.responseLiveData.observe(viewLifecycleOwner,{ responce ->
            responce.notes?.let { notes ->
                mAdapte.swapData(notes)
                Log.d("FireBase","$notes")
            }


        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}