package com.example.chatapp.adapter

import android.media.Image
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.GONE
import androidx.recyclerview.widget.RecyclerView.VISIBLE
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.chatapp.Model.MessageModel
import com.example.chatapp.R

class MessageAdapter (var list: ArrayList<MessageModel>):Adapter<MessageAdapter.MessageViewHolder> (){

    inner class MessageViewHolder(view : View):ViewHolder(view){

        val mesText = view.findViewById<TextView>(R.id.show_message)
        val imageContainer = view.findViewById<LinearLayout>(R.id.imageCard)
        val image = view.findViewById<ImageView>(R.id.image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {

        var view : View ? = null

        val from = LayoutInflater.from(parent.context)

        if(viewType == 0){
            view = from.inflate(R.layout.chatright,parent,false)
        }else{
            view = from.inflate(R.layout.chatleft_item_view,parent,false)

        }

        return MessageViewHolder(view)

    }

    override fun getItemViewType(position: Int): Int {

        val message = list[position]
        return if (message.isUser) 0 else 1

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {

        val mes  =list[position]

        if (!mes.isUser){
            holder.imageContainer.visibility = GONE
        }

        if (mes.isImage){
            holder.imageContainer.visibility = VISIBLE

            Glide.with(holder.itemView.context)
                .load(mes.message)
                .into(holder.image)

        }else holder.mesText.text = mes.message


    }

}