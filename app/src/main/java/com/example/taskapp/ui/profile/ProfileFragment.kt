package com.example.taskapp.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.taskapp.data.Pref
import com.example.taskapp.databinding.FragmentProfileBinding
import com.example.taskapp.utils.loadImage

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref

    private val mGetContent: ActivityResultLauncher<String> =
        registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri ->
            binding.ivProfile.setImageURI(uri)

            Pref(requireContext()).saveImage(uri.toString())
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())

        binding.etName.setText(pref.getName())
        binding.etAge.setText(pref.getAge())
        binding.ivProfile.loadImage(pref.getImage())
        binding.btnSave.setOnClickListener{
            pref.saveName(binding.etName.text.toString())
            pref.saveAge(binding.etAge.text.toString())
        }//Сохраняем данные с SharedPreferences

        binding.ivProfile.setOnClickListener {
            mGetContent.launch("image/*")
        }
    }
}