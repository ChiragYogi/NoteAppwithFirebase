package com.example.noteappwithfirebase.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappwithfirebase.MainActivity
import com.example.noteappwithfirebase.R
import com.example.noteappwithfirebase.databinding.NoteDisplayFragmentBinding
import com.example.noteappwithfirebase.model.Note
import com.example.noteappwithfirebase.view.adepter.NoteAdepter


class NoteDisplay : Fragment(R.layout.note_display_fragment), NoteAdepter.OnItemClick{

    private var _binding:NoteDisplayFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NoteViewModel
    private val mAdapte =  NoteAdepter(this)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = NoteDisplayFragmentBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel

         binding.noteDisplayRv.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapte
             swipeToDelete()
             }

        getDataForRecyclerView()



        binding.addNoteFabButton.setOnClickListener {
        findNavController().navigate(R.id.action_noteDisplay_to_addNoteFragment)
    }



    }

    private fun swipeToDelete() {
      val swipeToCallback = object : ItemTouchHelper.SimpleCallback(
          0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
      ){
          override fun onMove(
              recyclerView: RecyclerView,
              viewHolder: RecyclerView.ViewHolder,
              target: RecyclerView.ViewHolder
          ): Boolean {
              return true
          }

          override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {


              val note = mAdapte.currentList[viewHolder.adapterPosition]
               viewModel.removeNote(note)
          }

      }
    }

    private fun getDataForRecyclerView() {

        viewModel.responseLiveData.observe(viewLifecycleOwner,{ responce ->
            responce.notes?.let { notes ->
                mAdapte.submitList(notes)

                Log.d("FirebaseLog","$notes")
            }


        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(note: Note) {

        val bundle = Bundle()
        bundle.putString("title",note.title)
        bundle.putString("info",note.info)
        bundle.putString("createdAt",note.createdAt)
        Log.d("FirebaseLog","$bundle")


        findNavController().navigate(R.id.action_noteDisplay_to_updateNoteFragment,bundle)

    }









}