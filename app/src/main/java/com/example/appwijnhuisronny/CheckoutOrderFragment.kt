package com.example.appwijnhuisronny

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appwijnhuisronny.databinding.FragmentCheckoutOrderBinding

class CheckoutOrderFragment : Fragment() {
    private var _binding: FragmentCheckoutOrderBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CheckoutOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_checkout_order, container, false)
        _binding = FragmentCheckoutOrderBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(CheckoutOrderViewModel::class.java)

        binding.orderDetailsViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return view
    }
}