package com.example.appwijnhuisronny

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appwijnhuisronny.databinding.FragmentHomepageBinding
import com.squareup.picasso.Picasso

class HomepageFragment : Fragment() {

    private var _binding: FragmentHomepageBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomepageFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomepageBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(HomepageFragmentViewModel::class.java)

        binding.homepageFragmentViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Observe changes in photo URLs and update ImageViews
        viewModel.photoUrlsLiveData.observe(viewLifecycleOwner, Observer { photoUrls ->
            if (photoUrls.isNotEmpty()) {
                Picasso.get().load(photoUrls[0]).into(binding.imageView2)
                Picasso.get().load(photoUrls[1]).into(binding.overOnsImageView)
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