package com.example.macpro.myapp

import android.app.Activity
import android.content.Intent
import android.provider.ContactsContract
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.home_item.view.*

class adapter_home(var mInterstitialAd: InterstitialAd) :RecyclerView.Adapter<adapter_home.ViewHolderIndex>() {

    var config = RealmConfiguration.Builder()
        .name("cplass.realm")
        .build()
    var realm = Realm.getInstance(config)
    val data = realm.where(DataBase::class.java).findAll()
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): adapter_home.ViewHolderIndex {
       var myViewInflater = LayoutInflater.from(viewGroup.context).inflate(R.layout.home_item,viewGroup,false)
        return ViewHolderIndex(myViewInflater)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(p0: adapter_home.ViewHolderIndex, p1: Int) {
        val data = data!![p1]
        p0.bind(data!!,mInterstitialAd )
    }
    class ViewHolderIndex(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind ( mydata : DataBase , mInterstitialAd: InterstitialAd){
                val name_lesson = itemView.title as TextView
                name_lesson.text = mydata.index_name
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context,lesson_Activity::class.java)
                    intent.putExtra("title",mydata.index_name)
                    intent.putExtra("cont",mydata.lesson)
                    intent.putExtra("image",mydata.image_url)

                    mInterstitialAd.adListener = object: AdListener() {
                        override fun onAdClosed() {
                            // Code to be executed when when the interstitial ad is closed.
                            mInterstitialAd.loadAd(AdRequest.Builder().build())
                            itemView.context.startActivity(intent)
                        }
                    }
                    if (mInterstitialAd.isLoaded) {
                        mInterstitialAd.show()

                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.")
                        itemView.context.startActivity(intent)
                    }
                    }
                }
            }
    }
