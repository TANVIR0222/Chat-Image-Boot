package com.example.chatapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.Api.ApiUtlities
import com.example.chatapp.Model.MessageModel
import com.example.chatapp.Model.imageResponcs.GenerateImageModel
import com.example.chatapp.Model.request.ImageGenerateRequest
import com.example.chatapp.Utils
import com.example.chatapp.adapter.MessageAdapter
import com.example.chatapp.databinding.ActivityImageBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody

class ImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageBinding

    val list  = ArrayList<MessageModel>()
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityImageBinding.inflate(layoutInflater)
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
        list.add(MessageModel(true ,false, binding.userMsg.editableText.toString()))
        adapter.notifyItemInserted(list.size -1)
        binding.recyclerView.recycledViewPool.clear()
        binding.recyclerView.smoothScrollToPosition(list.size -1)


        val apiInterface = ApiUtlities.getApiInterface()

        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            Gson().toJson(
                ImageGenerateRequest(
                    1,
                    binding.userMsg.text.toString(),
                    "1024x1024"


                    )
            )
        )

        val contentType = "application/json"
        val authorization = "Bearer ${Utils.API_KEY}"

        lifecycleScope.launch(Dispatchers.IO) {

            try {


                val response = apiInterface.generatesImage(

                    contentType,authorization,requestBody

                )

                val imageResponse = response.data.first().url

                list.add(MessageModel(false, true, imageResponse))

                adapter.notifyItemInserted(list.size - 1)
                binding.recyclerView.recycledViewPool.clear()
                binding.recyclerView.smoothScrollToPosition(list.size - 1)

            }catch (e:Exception){

                withContext(Dispatchers.Main){
                    Toast.makeText(this@ImageActivity, e.message, Toast.LENGTH_SHORT).show()
                }

            }



        }



    }
}