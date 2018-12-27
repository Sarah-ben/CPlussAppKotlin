package com.example.macpro.myapp


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.android.gms.ads.MobileAds

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {
    lateinit var mInterstitialAd: InterstitialAd
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        MobileAds.initialize(this.context, "ca-app-pub-2505688435160257~1862231497")
        mInterstitialAd = InterstitialAd(this.context)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        Realm.init(activity)
        fitshData()

        recycler_home.layoutManager = LinearLayoutManager(this.context,LinearLayout.VERTICAL,false)
        recycler_home.adapter = adapter_home(mInterstitialAd)
        exit.setOnClickListener {
            activity?.finish()
        }
        face_home.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/abdulkhaleqhajj"))
            )
        }
        share_home.setOnClickListener {
            val sent = Intent()
            sent.action = Intent.ACTION_SEND
            sent.putExtra(Intent.EXTRA_TEXT , "https://play.google.com/store/apps/details?id=com.example.macpro.myapp حمل تطبيق تعلم السي بلس بلس ")
            sent.type = "text/plain"
            startActivity(Intent.createChooser(sent,"إختار التطبيق الذي تريد المشاركه معه:"))
        }
    }

    fun fitshData(){
        val url = "http://www.arablancer.org/cplasplas/public/api/"
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val Api :API = retrofit.create(API::class.java)
        val call = Api.getdata()
        var item = call.enqueue(object :Callback<ArrayList<data>>{
            override fun onFailure(call: Call<ArrayList<data>>, t: Throwable) {
               Toast.makeText(activity,"غير قادر على الإتصال بالسيرفر",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ArrayList<data>>, response: Response<ArrayList<data>>) {
            var config = RealmConfiguration.Builder()
                .name("cplass.realm")
                .build()
                var realm = Realm.getInstance(config)
            var allData : ArrayList<data>? = response.body()
//
                if(realm.where(DataBase::class.java).findAll().isEmpty()){
                    realm.executeTransaction {
                        for (i in allData!!){
                            val lesson = realm.createObject(DataBase::class.java,i.id)
                            lesson.index_name = i.index_name
                            lesson.image_url = i.image_url
                            lesson.lesson = i.lesson
                        }
                    }
                }
                if(realm.where(DataBase::class.java).findAll().size != allData!!.size){

                    realm.executeTransaction {
                        realm.deleteAll()
                        for (i in allData!!){
                            val lesson = realm.createObject(DataBase::class.java,i.id)
                            lesson.index_name = i.index_name
                            lesson.image_url = i.image_url
                            lesson.lesson = i.lesson
                        }
                    }
                }

                recycler_home.adapter = adapter_home(mInterstitialAd)
            }

        })

    }

}
