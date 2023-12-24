package com.example.appwijnhuisronny

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.appwijnhuisronny.Models.OrderDetails
import com.example.appwijnhuisronny.Models.Wine
import com.example.appwijnhuisronny.databinding.FragmentCheckoutOrderBinding
import java.lang.Exception
class CheckoutOrderViewModel(application: Application) : AndroidViewModel(application) {
    fun getBodyText(orderDetails: OrderDetails, cartItems: List<Wine>, totalAmount: Double): String {
        val stringBuilder = StringBuilder()

        // Thank you message
        stringBuilder.append("Order placed by: , ${orderDetails.name}!\n\n")

        // Order details
        stringBuilder.append("Order Details:\n")
        stringBuilder.append("Name: ${orderDetails.name}\n")
        stringBuilder.append("Email: ${orderDetails.email}\n")
        stringBuilder.append("Address: ${orderDetails.address}\n")
        stringBuilder.append("Zip Code: ${orderDetails.zipCode}\n")
        stringBuilder.append("Country: ${orderDetails.country}\n")
        stringBuilder.append("Phone Number: ${orderDetails.phoneNumber}\n\n")

        // Bought wines
        stringBuilder.append("Bought Wines:\n")
        for (wine in cartItems) {
            stringBuilder.append("${wine.Naam} - Quantity: ${wine.Aantal} - Total Price: $${wine.TotalPrice}\n")
        }
        stringBuilder.append("\n")

        // Total price
        stringBuilder.append("Total Price: $$totalAmount\n")

        return stringBuilder.toString()
    }
}
