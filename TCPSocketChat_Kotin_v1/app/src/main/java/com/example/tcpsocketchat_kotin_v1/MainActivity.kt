package com.example.tcpsocketchat_kotin_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.tcpsocketchat_kotin_v1.databinding.ActivityMainBinding
import com.example.tcpsocketchat_kotin_v1.viewModel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setTitle("Talk to Kaku")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.connect.setOnClickListener {

            val intent = Intent(this, MainActivity2::class.java).apply {
                putExtra("Talking_to", binding.connectTo.text.toString())
                putExtra("this_user", binding.userName.text.toString())
            }
            startActivity(intent)
        }

    }
}