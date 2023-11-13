package com.example.appwijnhuisronny

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.appwijnhuisronny.databinding.FragmentWinesCategoryBinding


class WinesCategoryFragment : Fragment() {

    private var _binding: FragmentWinesCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: WinesCategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWinesCategoryBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(WinesCategoryViewModel::class.java)

        binding.winesCategoryFragmentViewModel = viewModel

        // Set the OnClickListener for each RelativeLayout
        binding.layoutWitteWijn.setOnClickListener {
            view.findNavController().navigate(R.id.action_winesCategoryFragment_to_whiteWinesFragment)
        }
        binding.layoutRodeWijn.setOnClickListener {
            view.findNavController().navigate(R.id.action_winesCategoryFragment_to_whiteWinesFragment)
        }
        binding.layoutRoseWijn.setOnClickListener {
            view.findNavController().navigate(R.id.action_winesCategoryFragment_to_whiteWinesFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}