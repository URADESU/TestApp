package com.example.user.testapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import com.firebase.ui.database.FirebaseListAdapter
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class RoomFragment : Fragment() {
    private val MESSAGE_STORE = "messages"
    private var mAdapter: FirebaseListAdapter<ChatMessage>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()
    }

    override fun onResume() {
        super.onResume()

        mAdapter = object : FirebaseListAdapter<ChatMessage>(activity, ChatMessage::class.java, android.R.layout.simple_list_item_1, getMessageRef()) {
            override fun populateView(v: View, model: ChatMessage, position: Int) {
                (v as TextView).text = model.sender + ": " + model.body
            }
        }

        val listView: ListView = listview
        listView.adapter = mAdapter
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
    }

    private fun sendMessage(content: String){
        getMessageRef().push().setValue(ChatMessage(content, "ura", System.currentTimeMillis())).continueWith(object : Continuation<Void, Any> {
            override fun then(p0: Task<Void>): Any? {
                if(!p0.isSuccessful){
                    Log.e("sendMessage", "error", p0.exception)
                }

                (txt_content as TextView).text = ""

                return null
            }

        })
    }

    //データベースの参照を返すメソッド
    private fun getMessageRef() : DatabaseReference {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()

        return database.getReference(MESSAGE_STORE)
    }
}