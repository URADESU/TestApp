package com.example.user.testapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.firebase.client.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * senderだけをSteveからWozに書き換える
         * */
//        val messages = Firebase("https://testapp-567f7.firebaseio.com/messages")
//        val sender: MutableMap<String, Any> = mutableMapOf()
//        sender.put("sender", "Woz")
//        messages.child("01").updateChildren(sender)

        /**
         * 任意のURIへの参照を取得し，setValue()メソッドを呼び出して書き込む
         * push()を間に噛ませることにより、timestampの値に基づいて一意なIDを自動的に払い出してくれる
         * */
//        val messages = Firebase("https://testapp-567f7.firebaseio.com/messages")
//        messages.push().setValue(ChatMessage("How are you doing?", "Steve", System.currentTimeMillis()))
//        messages.push().setValue(ChatMessage("I'm great!", "Bill", System.currentTimeMillis()))



        /**
         * データのソート後、読み出しを検知するリスナー
         * */
//        val ref = Firebase("https://testapp-567f7.firebaseio.com/counts")
//
//        val query = ref.orderByValue()
//        query.addChildEventListener(object : ChildEventListener {
//            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {}
//
//            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {}
//
//            override fun onChildRemoved(p0: DataSnapshot?) {}
//
//            override fun onCancelled(p0: FirebaseError?) {}
//
//            //リストのようなデータ構造で，子要素が新規追加されたタイミングで呼び出される
//            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
//                val user = p0!!.key
//                val count = p0!!.value as Long
//                Log.d("Firebase", String.format("user: %s's count=%d", user, count))
//            }
//        })


    }
}
