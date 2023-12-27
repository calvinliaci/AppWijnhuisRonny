package com.example.appwijnhuisronny

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.appwijnhuisronny.databinding.FragmentWinesCategoryBinding
import com.squareup.picasso.Picasso

class WinesCategoryFragment : Fragment() {

    private var _binding: FragmentWinesCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: WinesCategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWinesCategoryBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(WinesCategoryViewModel::class.java)

        binding.winesCategoryFragmentViewModel = viewModel

        binding.layoutWitteWijn.setOnClickListener {
            view.findNavController().navigate(R.id.action_winesCategoryFragment_to_whiteWinesFragment)
        }
        binding.layoutRodeWijn.setOnClickListener {
            view.findNavController().navigate(R.id.action_winesCategoryFragment_to_whiteWinesFragment)
        }
        binding.layoutRoseWijn.setOnClickListener {
            view.findNavController().navigate(R.id.action_winesCategoryFragment_to_whiteWinesFragment)
        }

        // Observe changes in photo URLs and update ImageViews
        viewModel.photoUrlsLiveData.observe(viewLifecycleOwner, Observer { photoUrls ->
            if (photoUrls.isNotEmpty()) {
                Picasso.get().load(photoUrls[0]).into(binding.imageWitteWijn)
                Picasso.get().load(photoUrls[1]).into(binding.imageRodeWijn)
                Picasso.get().load(photoUrls[2]).into(binding.imageRoseWijn)
                Picasso.get().load(photoUrls[3]).into(binding.imageBubbels)
                Picasso.get().load(photoUrls[4]).into(binding.imagePorto)
                Picasso.get().load(photoUrls[5]).into(binding.imageGins)
                Picasso.get().load(photoUrls[6]).into(binding.imageOverige)
            }
        })

        // Load photo URLs
        viewModel.loadPhotoUrls()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
