package com.example.appwijnhuisronny

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.appwijnhuisronny.Models.OrderDetails
import com.example.appwijnhuisronny.Models.Wine
import com.example.appwijnhuisronny.databinding.FragmentCheckoutOrderBinding
import java.lang.Exception
class CheckoutOrderViewModel() : ViewModel() {
    fun getBodyText(orderDetails: OrderDetails, cartItems: List<Wine>, totalAmount: Double): String {
        val stringBuilder = StringBuilder()

        // Thank you message
        stringBuilder.append("Bestelling geplaatst door: ${orderDetails.name}!\n\n")

        // Order details
        stringBuilder.append("Besteldetails:\n")
        stringBuilder.append("Naam: ${orderDetails.name}\n")
        stringBuilder.append("E-mailadres: ${orderDetails.email}\n")
        stringBuilder.append("Adres: ${orderDetails.address}\n")
        stringBuilder.append("Postcode: ${orderDetails.zipCode}\n")
        stringBuilder.append("Woonplaats: ${orderDetails.country}\n")
        stringBuilder.append("GSM-nummer: ${orderDetails.phoneNumber}\n\n")

        // Bought wines
        stringBuilder.append("Bestelde wijnen:\n")
        for (wine in cartItems) {
            stringBuilder.append("${wine.Naam} - Hoeveelheid: ${wine.Aantal} - Totaalprijs per wijn: €${wine.TotalPrice}\n")
        }
        stringBuilder.append("\n")

        // Total price
        stringBuilder.append("Totaalprijs: €$totalAmount\n")
        stringBuilder.append("Gelieve te betalen bij afhaal! (Cash/Payconiq)\n")

        return stringBuilder.toString()
    }
}
