package com.example.appwijnhuisronny

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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
        //return inflater.inflate(R.layout.fragment_shopping_cart, container, false)
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(ShoppingCartViewModel::class.java)

        binding.shoppingCartFragmentViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return view
    }

    // ... onCreateView and other methods

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ShoppingCartAdapter(this)

        // Set up the RecyclerView with the adapter
        binding.shoppingCartRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.shoppingCartRecyclerView.adapter = adapter

        viewModel.shoppingCart.observe(viewLifecycleOwner) { cartItems ->
            adapter.updateCartItems(cartItems)
        }

    }

}