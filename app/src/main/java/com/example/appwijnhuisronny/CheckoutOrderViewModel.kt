package com.example.appwijnhuisronny

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.appwijnhuisronny.Models.OrderDetails
import com.example.appwijnhuisronny.databinding.FragmentCheckoutOrderBinding
import java.lang.Exception
class CheckoutOrderViewModel(application: Application) : AndroidViewModel(application) {
    private var _binding: FragmentCheckoutOrderBinding? = null
    private val binding get() = _binding!!
    private lateinit var winesViewModel: ShoppingCartViewModel
    fun getOrderDetailsAndSendConfirmation() {
        val name = binding.nameEditText.text.toString().trim()
        val email = binding.mailEditText.text.toString().trim()
        val address = binding.addressEditText.text.toString().trim()
        val zipCode = binding.zipCodeEditText.text.toString().trim()
        val country = binding.countryEditText.text.toString().trim()
        val phoneNumber = binding.phoneEditText.text.toString().trim()
        val wines = winesViewModel.shoppingCart.value ?: emptyList()

        val orderDetails = OrderDetails(
            name = name,
            email = email,
            address = address,
            zipCode = zipCode,
            country = country,
            phoneNumber = phoneNumber,
            wines = wines
        )

        // Now, you can use orderDetails as needed, for example, send an email:
        sendOrderConfirmation(email, "Bestelbevestiging", orderDetails)
    }
    fun sendOrderConfirmation(recipientEmail: String, subject: String?, body: OrderDetails) {
        val mIntent = Intent(Intent.ACTION_SEND)

        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipientEmail))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, getBodyText(body))

        try {
            getApplication<Application>().startActivity(Intent.createChooser(mIntent, "Send Email"))
        } catch (e: Exception){
            Log.e("Email", "Email has not send")
        }
    }

    private fun getBodyText(orderDetails: OrderDetails): String {
        val stringBuilder = StringBuilder()

        // Thank you message
        stringBuilder.append("Thank you for your order, ${orderDetails.name}!\n\n")

        // Order details
        stringBuilder.append("Order Details:\n")
        stringBuilder.append("Name: ${orderDetails.name}\n")
        stringBuilder.append("Email: ${orderDetails.email}\n")
        stringBuilder.append("Address: ${orderDetails.address}\n")
        stringBuilder.append("Zip Code: ${orderDetails.zipCode}\n")
        stringBuilder.append("Country: ${orderDetails.country}\n")
        stringBuilder.append("Phone Number: ${orderDetails.phoneNumber}\n")

        // List of wines
        stringBuilder.append("Ordered Wines:\n")
        for (wine in orderDetails.wines) {
            stringBuilder.append("${wine.Naam} - ${wine.Aantal} x ${wine.Prijs} each\n")
        }

        return stringBuilder.toString()
    }
}
