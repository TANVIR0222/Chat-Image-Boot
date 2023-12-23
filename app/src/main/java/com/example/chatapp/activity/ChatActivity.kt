package com.example.chatapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.Api.ApiUtlities
import com.example.chatapp.Model.MessageModel
import com.example.chatapp.Model.request.ChatRequest
import com.example.chatapp.Utils
import com.example.chatapp.adapter.MessageAdapter
import com.example.chatapp.databinding.ActivityChatBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody

class ChatActivity : AppCompatActivity() {

    lateinit var binding:ActivityChatBinding

    val list  = ArrayList<MessageModel>()
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var adapter: MessageAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChatBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            finish()
        }

        mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.stackFromEnd = true
        adapter=MessageAdapter(list)
        binding.recyclerView.adapter =adapter
        binding.recyclerView.layoutManager = mLayoutManager



        binding.sendbtn.setOnClickListener {
            if(binding.userMsg.text!!.isEmpty()){
                Toast.makeText(this, "Please enter your message ", Toast.LENGTH_SHORT).show()
            }else{
                callApi()
            }

        }


    }

    private fun callApi() {
        list.add(MessageModel(true ,false, binding.userMsg.text.toString()))
        adapter.notifyItemInserted(list.size -1)
        binding.recyclerView.recycledViewPool.clear()
        binding.recyclerView.smoothScrollToPosition(list.size -1)


        val apiInterface = ApiUtlities.getApiInterface()

        val requestBody = RequestBody.create(MediaType.parse("application/json"),
            Gson().toJson(
                ChatRequest(
                     250,
                        "gpt-3.5-turbo-instruct",
                         binding.userMsg.text.toString(),
                         0.7

                )
            )
        )

        val contentType = "application/json"
        val authorization = "Bearer ${Utils.API_KEY}"

        lifecycleScope.launch(Dispatchers.IO) {

            try {


                val response = apiInterface.getChat(
                    contentType, authorization, requestBody

                )

                val textResponse = response.choices.first().text

                list.add(MessageModel(false, false, textResponse))

                adapter.notifyItemInserted(list.size - 1)
                binding.recyclerView.recycledViewPool.clear()
                binding.recyclerView.smoothScrollToPosition(list.size - 1)

            }catch (e:Exception){

                withContext(Dispatchers.Main){
                    Toast.makeText(this@ChatActivity, e.message, Toast.LENGTH_SHORT).show()
                }

            }



        }



    }
}