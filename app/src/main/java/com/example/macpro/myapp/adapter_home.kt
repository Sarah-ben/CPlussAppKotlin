package com.example.macpro.myapp

import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class adapter_home() :RecyclerView.Adapter<adapter_home.ViewHolderIndex>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): adapter_home.ViewHolderIndex {
       var myViewInflater = LayoutInflater.from(viewGroup.context).inflate(R.layout.home_item,viewGroup,false)
        return ViewHolderIndex(myViewInflater)
    }

    override fun getItemCount(): Int {
       return 9
    }

    override fun onBindViewHolder(p0: adapter_home.ViewHolderIndex, p1: Int) {

    }
    class ViewHolderIndex(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}