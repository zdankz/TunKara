package com.example.tunkara.fragment


import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.tunkara.R
import com.example.tunkara.UserAdapter
import com.example.tunkara.entity.User
import com.example.tunkara.play_video
import kotlinx.android.synthetic.main.fragment_home.*

import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {


    val users = ArrayList<User>()
    lateinit var adapter: UserAdapter
    val loi : String = "Nhap bai hat"

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnSearch.setOnClickListener {
            var key_serch: String = search.text.toString()

            //var key_serch2 : String = key_serch.trim()

            if(key_serch.isNullOrBlank() == true)
            {

                //Toast.makeText(applicationContext,loi, Toast.LENGTH_LONG).show()
            }else {
                var key_serch2:String = key_serch.replace(' ','+')
                var urlyoutube: String = "https://byyswag.000webhostapp.com/?keyword="
                urlyoutube = urlyoutube.plus(key_serch2)
                clearListVideo()
                Getdata().execute(urlyoutube)
                initAdapter()
                initRecyclerView()
            }
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
    }


    private fun initRecyclerView() {
        rvAmUser.layoutManager = LinearLayoutManager(frag_home.context)
        rvAmUser.setHasFixedSize(true)
        rvAmUser.adapter = adapter
    }

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
