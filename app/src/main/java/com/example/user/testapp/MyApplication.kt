package com.example.user.testapp

import android.app.Application
import com.firebase.client.Firebase

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.setAndroidContext(this)
    }
}