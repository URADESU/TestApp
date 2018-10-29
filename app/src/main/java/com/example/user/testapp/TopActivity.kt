package com.example.user.testapp

import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_top.*
import com.google.firebase.database.DatabaseError


class TopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_top)



        FirebaseRef().ref().addValueEventListener(object : com.google.firebase.database.ValueEventListener{
            override fun onDataChange(p0: com.google.firebase.database.DataSnapshot) {
                Log.d("Firebase", p0.value.toString() + "childの合計 = " + p0.childrenCount)
                p0.children.forEach {
                    Log.d("Firebase", "子要素 = " + it.key)
                    addRoom(it.key.toString()).setOnClickListener(object : View.OnClickListener{
                        override fun onClick(p0: View?) {
                            Log.d("Firebase", it.key.toString() + "が押されました")

                            toRoom(it.key.toString())
                        }
                    })
                }
            }
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })


        button_to_fragment.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val roomFragment = RoomFragment()
                fragmentTransaction.replace(R.id.frame_room, roomFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }

        })

    }

    /**
     * トップ画面にRoomを追加する
     * */
    private fun addRoom(roomName: String) : Button{
        val button = Button(this)
        button.id = View.generateViewId()
        Log.i("addRoom", roomName + "のidを生成: id = " + button.id)
        button.text = roomName
        val param = LinearLayout.LayoutParams(0, getDisplaySize().x / 3, 1.0f)
        button.layoutParams = param

        linearLayout_activityTop.addView(button)

        return button
    }

    /**
     * 実行端末の画面サイズをPointで返す
     * */
    private fun getDisplaySize() : Point{
        val point = Point()
        windowManager.defaultDisplay.getRealSize(point)

        return point
    }

    private fun toRoom(roomName: String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val roomFragment = RoomFragment()
        val bundle = Bundle()

        bundle.putString("roomName", roomName)
        roomFragment.arguments = bundle

        fragmentTransaction.replace(R.id.frame_room, roomFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}