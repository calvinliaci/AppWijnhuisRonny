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
import com.example.appwijnhuisronny.Adapters.WhiteWineAdapter
import com.example.appwijnhuisronny.Adapters.WineTastingsAdapter
import com.example.appwijnhuisronny.databinding.FragmentHomepageBinding
import com.example.appwijnhuisronny.databinding.FragmentWineTastingsBinding

class WineTastingsFragment : Fragment() {

    private var _binding: FragmentWineTastingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: WineTastingsViewModel

    private lateinit var adapter: WineTastingsAdapter
    private lateinit var wineTastingsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_wine_tastings, container, false)
        _binding = FragmentWineTastingsBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(WineTastingsViewModel::class.java)

        binding.wineTastingsViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wineTastingsRecyclerView = binding.wineTastingsRecyclerView
        wineTastingsRecyclerView.layoutManager = LinearLayoutManager(context)
        wineTastingsRecyclerView.setHasFixedSize(true)

        adapter = WineTastingsAdapter()

        wineTastingsRecyclerView.adapter = adapter

        viewModel.allWineTastings.observe(viewLifecycleOwner, Observer {
            adapter.updateWineTastingsList(it)
            Log.d("WhiteWinesFragment", "Observed changes in allWines: $it")
        })
    }
}