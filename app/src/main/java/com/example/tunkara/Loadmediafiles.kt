package com.example.tunkara

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.app.ActivityCompat
import com.example.tunkara.entity.Songinfo
import kotlinx.android.synthetic.main.activity_loadmediafiles.*
import kotlinx.android.synthetic.main.mylayout_song_item.view.*

class Loadmediafiles : AppCompatActivity() {
    val litsSong = ArrayList<Songinfo>()
    var mp : MediaPlayer? = null
    var adapter1 : MySongAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loadmediafiles)

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),111)
        }else
            loadSong()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            loadSong()

    }
    inner class MySongAdapter(litsSong: ArrayList<Songinfo>) : BaseAdapter() {
        var myListSong = ArrayList<Songinfo>()
        override fun getView(potision: Int, p1: View?, p2: ViewGroup?): View {
            var myview = layoutInflater.inflate(R.layout.mylayout_song_item,null)
            var song = myListSong[potision]
            myview.textView.text = song.Title
            myview.textView2.text = ""
            myview.button.setOnClickListener{
                if(myview.button.text == "STOP"){
                    mp!!.stop()
                    myview.button.text = "PLAY"
                }else{
                    mp = MediaPlayer()
                    try{
                        mp!!.setDataSource(song.SongURL)
                        mp!!.prepare()
                        mp!!.start()
                        myview.button.text = "STOP"
                    }catch (e:Exception){

                    }

                }

            }
            return myview
        }

        override fun getItem(p0: Int): Any {
            return myListSong[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return  myListSong.size
        }


    }
    private fun loadSong(){
        var uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        var selection = MediaStore.Audio.Media.IS_MUSIC+"!=0"
        var rs = contentResolver.query(uri, null, selection,null,null)
        if(rs!=null){
            while(rs!!.moveToNext()){
                var url = rs!!.getString(rs!!.getColumnIndex(MediaStore.Audio.Media.DATA))
                var author = rs!!.getString(rs!!.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                var title = rs!!.getString(rs!!.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))

                litsSong.add(Songinfo(title,author,url))
            }
        }
        adapter1 = MySongAdapter(litsSong)
        listview.adapter = adapter1


    }
}
