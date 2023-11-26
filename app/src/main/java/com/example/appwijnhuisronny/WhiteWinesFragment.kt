package com.example.appwijnhuisronny

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appwijnhuisronny.Adapters.WhiteWineAdapter
import com.example.appwijnhuisronny.Models.Wine
import com.example.appwijnhuisronny.databinding.FragmentWhiteWinesBinding
import com.example.appwijnhuisronny.databinding.FragmentWinesCategoryBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class WhiteWinesFragment : Fragment() {

    private var _binding: FragmentWhiteWinesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: WhiteWinesFragmentViewModel
    private lateinit var winesRecyclerView: RecyclerView
    lateinit var adapter: WhiteWineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWhiteWinesBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(WhiteWinesFragmentViewModel::class.java)

        binding.whiteWinesFragmentViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        winesRecyclerView = view.findViewById(R.id.wineRecyclerView)
        winesRecyclerView.layoutManager = LinearLayoutManager(context)
        winesRecyclerView.setHasFixedSize(true)
        adapter = WhiteWineAdapter()
        winesRecyclerView.adapter = adapter

        viewModel.allWines.observe(viewLifecycleOwner, Observer {
            adapter.updateWinesList(it)
        })
    }
}