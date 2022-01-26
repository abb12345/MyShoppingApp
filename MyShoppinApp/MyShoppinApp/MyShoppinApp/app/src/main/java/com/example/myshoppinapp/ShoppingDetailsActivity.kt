package com.example.myshoppinapp

import Rates
import android.content.Intent
import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import com.google.gson.Gson
import myData
import org.json.JSONObject
import kotlin.text.StringBuilder


//const val URL_API = "https://jsonplaceholder.typicode.com/"
const val URL_API = "https://api.frankfurter.app/"
class ShoppingDetailsActivity : AppCompatActivity() {

    lateinit var ItemTitle: TextView
    lateinit var CURRENCY1: TextView
    lateinit var CURRENCY2: TextView
    lateinit var CURRENCY3: TextView
    lateinit var ItemPrice: TextView
       var amount  : Double? =0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_details)





        val intent = getIntent()
        val name = intent.getStringExtra("Title")
        val price = intent.getStringExtra("Price")
       // val Description = intent.getStringExtra("Description")


        ItemTitle = findViewById(R.id.tvName)
        ItemPrice= findViewById(R.id.tvPrice)

        CURRENCY1= findViewById(R.id.tvPriceincurr1)
        CURRENCY2= findViewById(R.id.tvPriceincurr2)
        CURRENCY3= findViewById(R.id.tvPriceincurr3)


         ItemTitle.text = name
            ItemPrice.text = " HUF Price :" +price.toString()
        amount =  price.toString().toDouble()

        geMyData()
    }


   private  fun geMyData()
    {
val retrofitBuilder = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(URL_API).build()
    .create(apiInterface::class.java)

        //val retrofitData  = retrofitBuilder.getData()
        val retrofitData  = retrofitBuilder.getDatabyamount(amount, "HUF")

       retrofitData.enqueue(object : Callback<myData?> {
            override fun onResponse(call: Call<myData?>, response: Response<myData?>) {
                val responseBody = response.body()

                CURRENCY1.text = "Price in USD is :" + responseBody?.rates?.USD.toString()

                CURRENCY2.text = "Price in AUD is :" + responseBody?.rates?.AUD.toString()

                CURRENCY3.text = "Price in BGN is :" + responseBody?.rates?.BGN.toString()
               // val myStrBuilder = StringBuilder()
               // Log.e("TAG", "response 33: " + Gson().toJson(response.body()))
            }

            override fun onFailure(call: Call<myData?>, t: Throwable) {
                Log.e("TAG", "onFailure: $t");
            }
        })
        //ctrl + shift +space



    }

    fun btnBackClick(view: View) {

        val intent = Intent(this@ShoppingDetailsActivity, MainActivity ::class.java)

        startActivity(intent)
        this.finish() }

    //use adapter


}








