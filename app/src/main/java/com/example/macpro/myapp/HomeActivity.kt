package com.example.macpro.myapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var mAdView : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        setSupportActionBar(toolbar)
        if(savedInstanceState == null){
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_frame,HomeFragment())
            .commitNow()
        }
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.home_frame,HomeFragment())
                    .commitNow()
            }
            R.id.about -> {
                     supportFragmentManager.beginTransaction()
                    .replace(R.id.home_frame,AboutFragment())
                    .commitNow()
            }
            R.id.team -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.home_frame,TeamFragment())
                    .commitNow()
            }
            R.id.order -> {
            startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("mailto:" + "info@ali-gamal.com" + "?subject=" + " رسالة من التطبيق")))
            }
            R.id.nav_share -> {
                val sent = Intent()
                sent.action = Intent.ACTION_SEND
                sent.putExtra(Intent.EXTRA_TEXT , "https://play.google.com/store/apps/details?id=com.example.macpro.myapp حمل تطبيق تعلم السي بلس بلس ")
                sent.type = "text/plain"
                startActivity(Intent.createChooser(sent,"إختار التطبيق الذي تريد المشاركه معه:"))
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
