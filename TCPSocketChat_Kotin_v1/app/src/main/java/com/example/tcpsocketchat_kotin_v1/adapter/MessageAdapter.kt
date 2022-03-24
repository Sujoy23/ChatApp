package com.example.tcpsocketchat_kotin_v1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tcpsocketchat_kotin_v1.R

class MessageAdapter(val c: Context, val userList:ArrayList<String>): RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {


    inner class MessageViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val message: TextView

        init {
            message = v.findViewById(R.id.message)
        }

    }

    override fun getItemViewType(position: Int): Int {
        if(userList[position].contains("_1_see_nt")){
            return 1
        }else{
            return 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val listItem = LayoutInflater.from(c).inflate(R.layout.message_received, parent, false)
        val listItem2 = LayoutInflater.from(c).inflate(R.layout.message_sent, parent, false)
        if (viewType == 1){
            return MessageViewHolder(listItem2)
        }else {
            return MessageViewHolder(listItem)
        }
//        return MessageViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
//        holder.bind(userList[position])
        if(userList[position].contains("_1_see_nt")){
            val k = userList[position].replace("_1_see_nt", "")
            holder.message.text = k
        }else {
            holder.message.text = userList[position]
        }
    }

    override fun getItemCount(): Int {
        return userList.count()
    }
}