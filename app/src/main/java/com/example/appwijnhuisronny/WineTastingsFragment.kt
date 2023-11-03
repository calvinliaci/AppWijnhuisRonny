package com.example.appwijnhuisronny

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class WineTastingsFragment : Fragment() {

    companion object {
        fun newInstance() = WineTastingsFragment()
    }

    private lateinit var viewModel: WineTastingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wine_tastings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WineTastingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}