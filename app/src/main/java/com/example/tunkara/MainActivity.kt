package com.example.tunkara

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tunkara.entity.User
import com.example.tunkara.fragment.HomeFragment
import com.example.tunkara.fragment.ProfileFragment
import com.example.tunkara.fragment.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    val users = ArrayList<User>()
    lateinit var adapter: UserAdapter
    val loi : String = "Nhap bai hat"
    val homeFrag = HomeFragment()
    val searchFrag = SearchFragment()
    val profileFrag = ProfileFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        btnSearch.setOnClickListener {
//            var key_serch: String = search.text.toString()
//
//            //var key_serch2 : String = key_serch.trim()
//
//            if(key_serch.isNullOrBlank() == true)
//            {
//
//                Toast.makeText(applicationContext,loi, Toast.LENGTH_LONG).show()
//            }else {
//                var key_serch2:String = key_serch.replace(' ','+')
//                var urlyoutube: String = "https://byyswag.000webhostapp.com/?keyword="
//                urlyoutube = urlyoutube.plus(key_serch2)
//                clearListVideo()
//                Getdata().execute(urlyoutube)
//                initAdapter()
//                initRecyclerView()
//            }
//        }
        makeCurrentFragment(homeFrag)
        bottom_nvt.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> makeCurrentFragment(homeFrag)
                R.id.ic_search -> makeCurrentFragment(searchFrag)
                R.id.ic_profile -> makeCurrentFragment(profileFrag)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
    }
//    private fun initRecyclerView() {
//        rvAmUser.layoutManager = LinearLayoutManager(this)
//        rvAmUser.setHasFixedSize(true)
//        rvAmUser.adapter = adapter
//    }

    private fun initAdapter() {
        adapter = UserAdapter(users)
    }
    fun clearListVideo(){
        users.clear()
    }
    inner class Getdata : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg p0: String?): String {
            return getContentURL(p0[0])
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var idd: String=""
            var namee: String = ""
            var url_s: String = ""
            var jsonArray: JSONArray = JSONArray(result)
            for (video in 0..jsonArray.length() - 1) {
                var objectVD: JSONObject = jsonArray.getJSONObject(video)
                idd= objectVD.getString("ID")
                namee = objectVD.getString("song")
                url_s = objectVD.getString("songkey")
                users.add(User("",idd,namee,url_s))
                //listview.Name.text = name
                //mangbaihat.add(id+"\n"+name+"")
            }
            adapter.notifyDataSetChanged()
        }
    }
    private fun getContentURL(url: String?) : String{
        var content: StringBuilder = StringBuilder();
        val url: URL = URL(url)
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        val inputStreamReader = InputStreamReader(urlConnection.inputStream)
        val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)

        var line: String = ""
        try {
            do {
                line = bufferedReader.readLine()
                if(line != null){
                    content.append(line)
                }
            }while (line != null)
            bufferedReader.close()
        }catch (e: Exception){
            Log.d("AAA", e.toString())
        }
        return content.toString()
    }

}
