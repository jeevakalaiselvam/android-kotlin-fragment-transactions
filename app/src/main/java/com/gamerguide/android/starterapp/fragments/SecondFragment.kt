package com.gamerguide.android.starterapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gamerguide.android.starterapp.R
import com.gamerguide.android.starterapp.databinding.FragmentSecondBinding

@SuppressLint("SetTextI18n")
class SecondFragment:Fragment(R.layout.fragment_second){

    private var _binding: FragmentSecondBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = requireArguments().getString("name")
        val count = requireArguments().getInt("count")
        binding.data.text = "$name";
        binding.count.text = "$count";
    }
}