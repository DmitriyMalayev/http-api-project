package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
)
val customerStorage = mutableListOf<Customer>()  //storage for customer objects


//@Serializable and Ktor integration allows us to generate the JSON representation we need for our API responses