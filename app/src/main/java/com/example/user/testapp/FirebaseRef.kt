package com.example.user.testapp

import com.firebase.client.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseRef(){

    fun ref() : DatabaseReference{
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()

        return database.getReference()
    }

    fun ref(path: String) : DatabaseReference{
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()

        return database.getReference(path)
    }
}