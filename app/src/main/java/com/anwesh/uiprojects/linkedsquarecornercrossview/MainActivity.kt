package com.anwesh.uiprojects.linkedsquarecornercrossview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.squarecornercrossview.SquareCornerCrossView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SquareCornerCrossView.create(this)
    }
}
