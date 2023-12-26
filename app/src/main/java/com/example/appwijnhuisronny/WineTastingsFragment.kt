package com.example.appwijnhuisronny

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appwijnhuisronny.Adapters.WhiteWineAdapter
import com.example.appwijnhuisronny.Adapters.WineTastingsAdapter
import com.example.appwijnhuisronny.Models.OrderDetails
import com.example.appwijnhuisronny.Models.WineTasting
import com.example.appwijnhuisronny.databinding.FragmentHomepageBinding
import com.example.appwijnhuisronny.databinding.FragmentWineTastingsBinding

class WineTastingsFragment : Fragment(), WineTastingsAdapter.OnInschrijvenClickListener {

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

        adapter = WineTastingsAdapter(this)
        wineTastingsRecyclerView.adapter = adapter

        viewModel.allWineTastings.observe(viewLifecycleOwner, Observer {
            adapter.updateWineTastingsList(it)
            Log.d("WhiteWinesFragment", "Observed changes in allWines: $it")
        })
    }

    override fun onInschrijvenClick(wineTasting: WineTasting) {
        try {
            val subject = "Inschrijving degustatie"
            val emailSub = "info@wijnhuisronny.be"

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(emailSub))
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, viewModel.getBodyText(wineTasting))

            }
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("CheckoutOrderFragment", "Error during checkout: ${e.message}")
        }
        view?.findNavController()?.navigate(R.id.action_wineTastingsFragment_to_homepageFragment)
    }
}