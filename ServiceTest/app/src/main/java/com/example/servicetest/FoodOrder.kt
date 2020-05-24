package com.example.servicetest

import org.json.JSONObject

class FoodOrder private constructor(
    private val bread: String?,
    private val condiments: String?,
    private val meat: String?,
    private val fish: String?,
    private val price: Double = 0.0,
    var total: Double = 0.0
) {
    data class Builder(
        var bread: String? = null,
        var condiments: String? = null,
        var meat: String? = null,
        var fish: String? = null
    ) {
        fun bread(bread: String) = apply { this.bread = bread }
        fun condiments(condiments: String) = apply { this.condiments = condiments }
        fun meat(meat: String) = apply { this.meat = meat }
        fun fish(fish: String) = apply { this.fish = fish }
        fun build() = FoodOrder(bread, condiments, meat, fish, 100.0)
    }

    fun addTax() = apply { total = price * 1.0825 }

    override fun toString(): String {
        return "foodOrder = {bread: $bread, condiments: $condiments, meat: $meat, fish: ${fish ?: "Ahorita no joven"}, price: $price, total: $total"
    }
}