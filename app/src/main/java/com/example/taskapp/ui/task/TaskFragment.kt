package com.example.taskapp.ui.task

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.databinding.FragmentTaskBinding
import com.example.taskapp.model.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            onSave()
        }
    }

    private fun onSave() {
        val task = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        putTask(task)
        App.db.taskDao().insert(task)
        findNavController().navigateUp()
    }

    private fun putTask(task: Task){
        FirebaseAuth.getInstance().currentUser?.uid?.let {
            db.collection(it).add(task).addOnSuccessListener {
                Log.e("ololo", "onSave: success!")
            }.addOnFailureListener {
                Log.e("ololo", "onSave: " + it.message)
            }
        }
    }

    companion object{
        const val RESULT_TASK = "result.task"
    }
}