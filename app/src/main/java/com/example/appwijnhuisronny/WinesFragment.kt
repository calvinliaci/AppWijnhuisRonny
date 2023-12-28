package com.example.appwijnhuisronny

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appwijnhuisronny.Adapters.WineAdapter

import com.example.appwijnhuisronny.databinding.FragmentWinesBinding

class WinesFragment : Fragment() {

    private var _binding: FragmentWinesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: WinesFragmentViewModel
    private lateinit var viewModel2: ShoppingCartViewModel

    private lateinit var adapter: WineAdapter
    private lateinit var winesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWinesBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(requireActivity()).get(WinesFragmentViewModel::class.java)
        viewModel2 = ViewModelProvider(requireActivity()).get(ShoppingCartViewModel::class.java)

        val category = arguments?.getString("category") ?: "default"
        viewModel = ViewModelProvider(requireActivity()).get(WinesFragmentViewModel::class.java)
        viewModel.init(category)

        binding.whiteWinesFragmentViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        winesRecyclerView = view.findViewById(R.id.wineRecyclerView)
        winesRecyclerView.layoutManager = LinearLayoutManager(context)
        winesRecyclerView.setHasFixedSize(true)

        adapter = WineAdapter { wine ->
            viewModel2.addToCart(wine)
        }

        winesRecyclerView.adapter = adapter

        viewModel.allWines.observe(viewLifecycleOwner, Observer {
            adapter.updateWinesList(it)
            Log.d("WinesFragment", "Observed changes in allWines: $it")
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}