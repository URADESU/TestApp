package com.example.user.testapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.TextView
import com.firebase.client.Firebase
import com.firebase.ui.database.FirebaseListAdapter
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

//TODO: class、methodにドキュメンテーションコメントを記入
class MainActivity : AppCompatActivity() {
    private val MESSAGE_STORE = "messages"
    private var mAdapter: FirebaseListAdapter<ChatMessage>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }

    override fun onResume() {
        super.onResume()

        mAdapter = object : FirebaseListAdapter<ChatMessage>(this, ChatMessage::class.java, android.R.layout.simple_list_item_1, getMessageRef()) {
            override fun populateView(v: View, model: ChatMessage, position: Int) {
                (v as TextView).text = model.sender + ": " + model.body
            }
        }

        val listView: ListView = listview
        listView.adapter = mAdapter
    }

    //データベースの参照を返すメソッド
    private fun getMessageRef() : DatabaseReference{
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()

        return database.getReference(MESSAGE_STORE)
    }

    private fun setup(){
        //sendボタン押下時の処理
        btn_send.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val content = txt_content.text.toString()

                //contentが空の場合、メッセージを送信しない
                if(TextUtils.isEmpty(content)) return

                sendMessage(content)
            }

        })


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
//        messages.push().setValue(ChatMessage("fdsa", "Steve", System.currentTimeMillis()))
//        messages.push().setValue(ChatMessage("fdsa", "Bill", System.currentTimeMillis()))



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

    private fun sendMessage(content: String){
        getMessageRef().push().setValue(ChatMessage(content, "ura", System.currentTimeMillis())).continueWith(object : Continuation<Void, Any>{
            override fun then(p0: Task<Void>): Any? {
                if(!p0.isSuccessful){
                    Log.e("sendMessage", "error", p0.exception)
                }

                (txt_content as TextView).text = ""

                return null
            }

        })
    }
}
