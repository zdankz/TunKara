package com.example.tunkara.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tunkara.Load_list_media
import com.example.tunkara.R
import kotlinx.android.synthetic.main.fragment_search.*


/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        click.setOnClickListener{
            var intent : Intent = Intent(frag_file.context,Load_list_media::class.java)
            startActivity(intent)
        }

//
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
//


}
