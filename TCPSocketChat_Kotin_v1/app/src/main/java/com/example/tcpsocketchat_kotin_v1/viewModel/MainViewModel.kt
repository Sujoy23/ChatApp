package com.example.tcpsocketchat_kotin_v1.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.Socket
import java.util.*

class MainViewModel : ViewModel() {

    lateinit var client : Socket
    val serveradd = "192.168.1.39"
    //lateinit var mes: String

    val factsLiveDataObject = MutableLiveData<String>()


    val liveData : LiveData<String>
        get() = factsLiveDataObject
    //lateinit var writer: PrintWriter

    suspend fun invokeServer(connect_to:String, connect_from:String){
        client = Socket(serveradd, 4444)
        val User_Info = connect_to +":"+connect_from
        client.getOutputStream().write(User_Info.toByteArray())
        receiveMessage()
    }

    suspend fun send(mess: String) {
        client.getOutputStream().write(mess.toByteArray())
        if(mess == "Over"){
            client.close()
        }
    }

    suspend fun receiveMessage(){
        while (!client.isClosed){
            val mess = Scanner(client.getInputStream())
            val revert = mess.nextLine().toString()

            if (revert != "Over"){
                factsLiveDataObject.postValue(revert)
            }else{
                factsLiveDataObject.postValue("Connection closed!!")
                client.close()
            }
        }

    }


}