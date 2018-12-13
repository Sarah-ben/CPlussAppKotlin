package com.example.macpro.myapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_lesson_.*

class lesson_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_)
        var title = intent?.extras?.getString("title")
        var con = intent?.extras?.getString("cont")
        val image = intent?.extras?.getString("image")
        title_text.text = title
        con_text.text = con
        Picasso.get().load("http://www.arablancer.org/cplasplas/public"+image).into(image_lesson)
    }
}
