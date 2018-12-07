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
import kotlinx.android.synthetic.main.fragment_home.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_home.layoutManager = LinearLayoutManager(this.context,LinearLayout.VERTICAL,false)
        recycler_home.adapter = adapter_home()

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


}
