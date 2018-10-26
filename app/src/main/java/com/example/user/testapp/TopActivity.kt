package com.example.user.testapp

import android.app.ActionBar
import android.graphics.Point
import android.os.Bundle
import android.os.PersistableBundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_top.*

class TopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_top)


        addRoom("room01")


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
    private fun addRoom(roomName: String){
        val button = Button(this)
        button.id = View.generateViewId()
        Log.i("addRoom", roomName + "のidを生成: id = " + button.id)
        button.text = roomName
        val param = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
        button.layoutParams = param

        linearLayout_activityTop.addView(button)
    }

    /**
     * 実行端末の画面サイズをPointで返す
     * */
    private fun getDisplaySize() : Point{
        val point = Point()
        windowManager.defaultDisplay.getRealSize(point)

        return point
    }


}