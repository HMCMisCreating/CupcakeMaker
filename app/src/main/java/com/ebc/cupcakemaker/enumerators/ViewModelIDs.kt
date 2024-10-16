package com.ebc.cupcakemaker.enumerators

enum class ViewModelIDs(val id: String) {
    Flavor(id = "flavor"),
    Quantity(id = "quantity"),
    Price(id = "price"),
    PickUpDate(id = "pickupDate"),
    ExtraInstructions(id = "extraInstructions"),
    PickUpInstructions(id = "pickupInstructions")
}