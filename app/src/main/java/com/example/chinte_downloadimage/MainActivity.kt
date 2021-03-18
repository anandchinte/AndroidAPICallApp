package com.example.chinte_downloadimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.loader.content.AsyncTaskLoader
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getMyData()

    }

    private fun getMyData() {

        val retrofitbuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(ApiInterface::class.java)


        val retrofitDate = retrofitbuilder.getDate()

        retrofitDate.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {

                Log.d("MainActivity", "OnFailure: "+t.message)

            }

            override fun onResponse(
                call: Call<List<MyDataItem>?>, response: Response<List<MyDataItem>?>) {
               var responseBody = response.body()!!

                val myStringBuilder = StringBuilder()
                for (myData in responseBody){

                    myStringBuilder.append(myData.id)
                    myStringBuilder.append("\n")
                }

                findViewById<TextView>(R.id.myTextView).text = myStringBuilder
            }
        })




    }


}