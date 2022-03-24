package com.example.tcpsocketchat_kotin_v1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tcpsocketchat_kotin_v1.adapter.MessageAdapter
import com.example.tcpsocketchat_kotin_v1.databinding.ActivityMain2Binding
import com.example.tcpsocketchat_kotin_v1.viewModel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    lateinit var mainViewModel: MainViewModel
    lateinit var messageList: ArrayList<String>
    lateinit var messageAdapter: MessageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle(intent.getStringExtra("Talking_to"))


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            mainViewModel.invokeServer(intent.getStringExtra("Talking_to").toString(),
                intent.getStringExtra("this_user").toString())
        }

        messageList = ArrayList()
        messageAdapter = MessageAdapter(this, messageList)
        binding.messages.layoutManager = LinearLayoutManager(this)
        binding.messages.adapter = messageAdapter


        mainViewModel.liveData.observe(this, Observer {
            addMessage(it)
        })

        binding.send.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                mainViewModel.send(binding.input.text.toString())
            }

            val k = binding.input.text.toString() + "_1_see_nt"
            addMessage(k)
            binding.input.text.clear()
        }

    }

    private fun addMessage(mess : String) {
        messageList.add(mess)
        messageAdapter.notifyDataSetChanged()
    }

}