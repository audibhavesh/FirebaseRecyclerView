package com.example.user.firebaserecycler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.eventlist.view.*


class MainActivity : AppCompatActivity() {
    lateinit var mdatabase:DatabaseReference
     var data:ArrayList<Eventdata> = ArrayList<Eventdata>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mdatabase=FirebaseDatabase.getInstance().reference.child("eventlist")
        val option = FirebaseRecyclerOptions.Builder<Eventdata>()
            .setQuery(mdatabase,Eventdata::class.java)
            .build()
        val adapter=object:FirebaseRecyclerAdapter<Eventdata,eventViewHolder>(option)
        {
            override fun getItemCount(): Int {
                return super.getItemCount()
            }
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): eventViewHolder {
                     return eventViewHolder(
                         LayoutInflater.from(parent.context)
                             .inflate(R.layout.eventlist,parent,false))
            }

            override fun onBindViewHolder(holder: eventViewHolder, position: Int, model: Eventdata) {
                holder.eventViewHolder(model)

            }

        }
//        adapter.notifyDataSetChanged()
        adapter.startListening()
        eventlist.layoutManager=LinearLayoutManager(this)
        eventlist.adapter=adapter


    }
    
    public class eventViewHolder(var view: View):RecyclerView.ViewHolder(view)
    {
        fun eventViewHolder(eventdata: Eventdata)
        {

                      view.event.text=eventdata.name
                      view.venue.text=eventdata.venue
                      view.time.text=eventdata.time

        }
    }
}
