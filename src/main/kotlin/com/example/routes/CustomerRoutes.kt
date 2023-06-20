package com.example.routes

import com.example.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRouting() {
    route("/customer") {
        get { // lists all customers
            if (customerStorage.isNotEmpty()) {
                call.respond(customerStorage)
            } else {
                call.respondText("No customers found", status = HttpStatusCode.OK)
            }
        }
        get("{id?}") { // display specific customer
            val id =
                call.parameters["id"] ?: return@get call.respondText("Missing id", status = HttpStatusCode.BadRequest)
            val customer = customerStorage.find { it.id == id } ?: return@get call.respondText(
                "No customer with id $id", status = HttpStatusCode.NotFound
            )
            call.respond(customer)    //If above are not null

        }
        post {   //create a Customer
            val customer = call.receive<Customer>()
            customerStorage.add(customer)
            call.respondText("Customer stored successfully", status = HttpStatusCode.Created)
        }
        delete("{id?}") {  //delete a customer
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)   //if there isn't an id
            if (customerStorage.removeIf { it.id == id }) {  //if an id is found
                call.respondText("Customer removed successfully", status = HttpStatusCode.Accepted)
            } else {  //if an id is not found
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}


/*
call.receive
    integrates with the configured Content Negotiation plugin.
    Calling it with the generic parameter Customer automatically deserializes the JSON request body into a Kotlin Customer object.
    We can then add the customer to our storage and respond with a status code of 201 creates.
*/
