package com.example.appwijnhuisronny

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appwijnhuisronny.databinding.FragmentHomepageBinding
import com.example.appwijnhuisronny.databinding.FragmentWineTastingsBinding

class WineTastingsFragment : Fragment() {

    private var _binding: FragmentWineTastingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: WineTastingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_wine_tastings, container, false)
        _binding = FragmentWineTastingsBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(WineTastingsViewModel::class.java)

        binding.wineTastingsFragmentViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return view
    }
}