package com.example.chatapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindng: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        bindng = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindng.root)

        generateImage()
        chatAi()


    }

    private fun chatAi() {

        bindng.chatBoth.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }
    }

    private fun generateImage() {
        bindng.generateImage.setOnClickListener {
            startActivity(Intent(this, ImageActivity::class.java))
        }
    }
}