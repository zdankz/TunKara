package com.example.tunkara

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.example.tunkara.entity.User
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_play_video.*
import kotlinx.android.synthetic.main.fragment_home.*

class play_video : AppCompatActivity() {



    val users = ArrayList<User>()
    lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_video)
        val intent = intent
        val url:String? = intent.getStringExtra("URL")
        val title:String? = intent.getStringExtra("name")
        val youtube : YouTubePlayerView = findViewById((R.id.youtube_player_view))
        lifecycle.addObserver(youtube)

        initAdapter()
        initRecyclerView()


        youtube.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                val videourl = url.toString()
                youTubePlayer.loadVideo(videourl,0f)
                btnre_play.setOnClickListener{
                    youTubePlayer.loadVideo(videourl,0f)
                }
                //youtube_player_view.enterFullScreen()
                //youtube_player_view.exitFullScreen()
                //youtube_player_view.isFullScreen()
                //youtube_player_view.toggleFullScreen()


            }
        })


    }
    override fun onDestroy() {
        super.onDestroy()
        detroy()
    }
    fun detroy(){
        youtube_player_view.release()
    }
    @SuppressLint("WrongConstant")
    private fun initRecyclerView() {
        rvAmUser2.layoutManager = LinearLayoutManager(this,OrientationHelper.HORIZONTAL,true)
        rvAmUser2.setHasFixedSize(true)
        rvAmUser2.adapter = adapter
    }

    private fun initAdapter() {
        adapter = UserAdapter(users)
    }
    fun clearListVideo(){
        users.clear()
    }
}
