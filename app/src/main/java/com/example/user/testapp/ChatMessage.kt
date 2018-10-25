package com.example.user.testapp

import java.util.function.LongFunction

class ChatMessage{
    var body: String = ""
    var sender: String = ""
    var timestamp: Long = 0

    //コンストラクタ
    constructor(){}
    constructor(body: String, sender: String, timestamp: Long){
        this.body = body
        this.sender = sender
        this.timestamp = timestamp
    }
}