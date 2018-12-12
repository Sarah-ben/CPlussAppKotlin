package com.example.macpro.myapp

import android.app.Activity
import android.content.Intent
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.home_item.view.*

class adapter_home(var data : ArrayList<data>) :RecyclerView.Adapter<adapter_home.ViewHolderIndex>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): adapter_home.ViewHolderIndex {
       var myViewInflater = LayoutInflater.from(viewGroup.context).inflate(R.layout.home_item,viewGroup,false)
        return ViewHolderIndex(myViewInflater)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(p0: adapter_home.ViewHolderIndex, p1: Int) {
        val data = data!![p1]
        p0.bind(data)
    }
    class ViewHolderIndex(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind ( mydata : data){
                val name_lesson = itemView.title as TextView
                name_lesson.text = mydata.index_name
                itemView.setOnClickListener {
                val intent = Intent(itemView.context,lesson_Activity::class.java)
                    intent.putExtra("title",mydata.index_name)
                    intent.putExtra("cont",mydata.lesson)
                    itemView.context.startActivity(intent)
                }
            }
    }
}