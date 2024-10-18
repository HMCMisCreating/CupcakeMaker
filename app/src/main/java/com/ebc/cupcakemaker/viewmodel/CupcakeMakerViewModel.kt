package com.ebc.cupcakemaker.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ebc.cupcakemaker.enumerators.ViewModelIDs
import com.ebc.cupcakemaker.model.CupcakeOrderState

class CupcakeMakerViewModel: ViewModel() {
    //esta es la var de estado, va a dar seguimiento a los cambios.
    var state by mutableStateOf(CupcakeOrderState())
        private set


    //Si alguien solicita cambio al state, esta fx se encargara de ejecutar el cambio.
    // Opciones disponibles: flavor, quantity, price, pickupdate, extrainstructions, pickupinstructions
    fun onValue(value:String, textId:String) {
        when (textId) {
            ViewModelIDs.Flavor.id -> state = state.copy(flavor = value)
            ViewModelIDs.Quantity.id -> {
                state = state.copy(quantity = value.toInt())
                calculateTotalAmount()
            }
            ViewModelIDs.Price.id -> {
                state = state.copy(price = value.toDouble())
                calculateTotalAmount()
            }
            ViewModelIDs.PickUpDate.id -> state= state.copy(pickupDate = value)
            ViewModelIDs.ExtraInstructions.id -> state= state.copy(extraInstructions = value)
            ViewModelIDs.PickUpInstructions.id -> state= state.copy(pickupInstructions = value)
        }

    }


    //fx para calcular total de la orden
    private fun calculateTotalAmount() {
        val quantity = state.quantity
        val price = state.price
        val total = quantity * price

        state = state.copy(total = total)

    }


    //Fx para hacer un reset a la orden e iniciar de nuevo
    fun reset() {
        state = CupcakeOrderState()
    }


}