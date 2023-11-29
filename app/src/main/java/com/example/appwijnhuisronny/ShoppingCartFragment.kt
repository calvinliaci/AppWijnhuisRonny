package com.example.appwijnhuisronny

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appwijnhuisronny.Adapters.ShoppingCartAdapter
import com.example.appwijnhuisronny.databinding.FragmentHomepageBinding
import com.example.appwijnhuisronny.databinding.FragmentShoppingCartBinding

class ShoppingCartFragment : Fragment() {

    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ShoppingCartViewModel
    private lateinit var adapter: ShoppingCartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        val view = binding.root
        Log.d("ShoppingCartFragment", "FragmentShoppingCartBinding inflated")

        viewModel = ViewModelProvider(requireActivity()).get(ShoppingCartViewModel::class.java)
        Log.d("ShoppingCartFragment", "ShoppingCartViewModel initialized")

        binding.shoppingCartFragmentViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return view
    }

    // ... onCreateView and other methods

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.shoppingCart.observe(viewLifecycleOwner) { cartItems ->
            adapter.updateCartItems(cartItems)
            Log.d("ShoppingCartFragment", "Observed changes in shopping cart: $cartItems")
        }
        Log.d("ShoppingCartFragment", "onViewCreated called")


        adapter = ShoppingCartAdapter()
        binding.shoppingCartRecyclerView.adapter = adapter
        binding.shoppingCartRecyclerView.layoutManager = LinearLayoutManager(context)


    }
}