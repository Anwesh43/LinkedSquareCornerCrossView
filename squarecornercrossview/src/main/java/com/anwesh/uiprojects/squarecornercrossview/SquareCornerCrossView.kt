package com.anwesh.uiprojects.squarecornercrossview

/**
 * Created by anweshmishra on 14/04/19.
 */

import android.view.View
import android.view.MotionEvent
import android.content.Context
import android.app.Activity
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Color

val nodes : Int = 5
val lines : Int = 4
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#673AB7")
val backColor : Int = Color.parseColor("#BDBDBD")
val lSizeFactor : Float = 3.2f
val angleDeg : Float = 45f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap
fun Float.updateToD(d : Float, sc : Float) : Float = this + (d - this) * sc
fun Float.distXY(y : Float) : Float = Math.sqrt((this * this + y * y).toDouble()).toFloat()

fun Canvas.drawXYLine(size : Float, sc : Float, paint : Paint) {
    val lSize : Float = size / lSizeFactor
    val dSize : Float = size.distXY(size)
    save()
    translate(0f, 0f.updateToD(dSize - lSize, sc))
    drawLine(0f, 0f, 0f, -lSize, paint)
    restore()
}

fun Canvas.drawSCCNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    paint.color = foreColor
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    save()
    translate(w / 2, gap * (i + 1))
    for (j in 0..(lines - 1)) {
        save()
        rotate(90f * j - angleDeg * sc1.divideScale(j, lines))
        drawXYLine(size, sc2.divideScale(j, lines), paint)
        restore()
    }
    restore()
}
