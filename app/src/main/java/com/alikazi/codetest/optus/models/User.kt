package com.alikazi.codetest.optus.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
) {
    data class Address(
        val street: String,
        val suite: String,
        val city: String,
        val zipcode: String,
        val location: Geo
    )

    data class Geo(
        val lat: String,
        val lng: String
    )

    data class Company(
        val name: String,
        val catchPharse: String,
        val bs: String
    )
}