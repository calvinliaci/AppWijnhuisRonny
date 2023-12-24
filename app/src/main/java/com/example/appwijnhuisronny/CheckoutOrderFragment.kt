package com.example.appwijnhuisronny

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.appwijnhuisronny.Models.OrderDetails
import com.example.appwijnhuisronny.databinding.FragmentCheckoutOrderBinding


class CheckoutOrderFragment : Fragment() {
    private var _binding: FragmentCheckoutOrderBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CheckoutOrderViewModel

    lateinit var shoppingCartViewModel: ShoppingCartViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_checkout_order, container, false)
        _binding = FragmentCheckoutOrderBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(CheckoutOrderViewModel::class.java)
        shoppingCartViewModel = ViewModelProvider(requireActivity()).get(ShoppingCartViewModel::class.java)

        binding.orderDetailsViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.checkoutButton.setOnClickListener {
            try {
                val subject = "Bestelbevestiging"
                val emailSub = "liacicalvin@gmail.com"

                val name = binding.nameEditText.text.toString().trim()
                val email = binding.mailEditText.text.toString().trim()
                val address = binding.addressEditText.text.toString().trim()
                val zipCode = binding.zipCodeEditText.text.toString().trim()
                val country = binding.countryEditText.text.toString().trim()
                val phoneNumber = binding.phoneEditText.text.toString().trim()

                val orderDetails = OrderDetails(
                    name = name,
                    email = email,
                    address = address,
                    zipCode = zipCode,
                    country = country,
                    phoneNumber = phoneNumber,
                )

                val cartItems = shoppingCartViewModel.getShoppingCart()
                val totalAmount = shoppingCartViewModel.getTotalAmount()

                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(emailSub))
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                    putExtra(Intent.EXTRA_TEXT, viewModel.getBodyText(orderDetails, cartItems, totalAmount))

                }
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("CheckoutOrderFragment", "Error during checkout: ${e.message}")
            }
        }

        return view
    }

}