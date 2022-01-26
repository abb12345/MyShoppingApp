package com.example.myshoppinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*
import android.widget.ArrayAdapter

import android.widget.Spinner
import android.view.MenuInflater as MenuInflater1


class AddShoppingItemsActivity : AppCompatActivity() {

    lateinit var ItemTitle: TextView
    lateinit var ItemDesc: TextView
    lateinit var saveBtn: Button
   // lateinit var DetailsBtn : Button
    lateinit var itemCategory :Spinner
    lateinit var ItemPrice: TextView
    lateinit var ItemIsBought: CheckBox
    //  private lateinit var spinner: Spinner
    private val itemsArray= arrayOf("Food", "Electronics", "Clothing")

    lateinit var viewModal: ShoopingViewModel
    var shopID = -1;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_shopping_items)

        val languages = resources.getStringArray(R.array.Languages)

 //spinner = findViewById<View>(R.id.category_spinner) as Spinner
 var adapter = ArrayAdapter(
     this@AddShoppingItemsActivity,
     android.R.layout.simple_spinner_item, itemsArray
 )

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.category_spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, languages
            )
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }





 viewModal = ViewModelProvider(
     this,
     ViewModelProvider.AndroidViewModelFactory.getInstance(application)
 ).get(ShoopingViewModel::class.java)

     //   val spinner = findViewById<Spinner>(R.id.category_spinner)

 ItemTitle = findViewById(R.id.idItemName)
 ItemDesc = findViewById(R.id.idItemDesc)
// itemCategory = findViewById(R.id.category_spinner)
 ItemIsBought =   findViewById(R.id.checkbox_isBought)
 ItemPrice = findViewById(R.id.idItemPrice)
 saveBtn = findViewById(R.id.idBtn)
     //   DetailsBtn = findViewById(R.id.DetailsBtn)



 saveBtn.setOnClickListener {
     // on below line we are getting
     // title and desc from edit text.
     val itemTitle = ItemTitle.text.toString()
     val itemDescription = ItemDesc.text.toString()
     val ItemCategory_ = spinner.getSelectedItem().toString()
     val itemIsBought_ =ItemIsBought.isChecked

     val itemPrice = ItemPrice.text.toString()
     val pr: Int = Integer.parseInt(itemPrice)


     if (itemTitle.isNotEmpty() && itemDescription.isNotEmpty()) {

             viewModal.addShoppingItem(Shopping(itemTitle, itemDescription,ItemCategory_, pr,itemIsBought_))
             Toast.makeText(this, "$itemTitle Added", Toast.LENGTH_LONG).show()
         }

     // opening the new activity on below line
     startActivity(Intent(applicationContext, MainActivity::class.java))
     this.finish()
 }

}


fun onCheckboxClicked(view: View) {
 if (view is CheckBox) {
     val checked: Boolean = view.isChecked

     when (view.id) {
         R.id.checkbox_isBought -> {
             if (checked) {
                 // Put some code
             } else {
                 // Remove the code
             }
         }

     }
 }

}

   /* fun OnDetialsClick(view: View) {

        val intent = Intent(this@AddShoppingItemsActivity, ShoppingDetailsActivity::class.java)

        intent.putExtra("Title", ItemTitle.text.toString())
        intent.putExtra("Price", ItemPrice.text.toString().toInt())

        startActivity(intent)
        this.finish()
    }*/


}

private fun Spinner.setOnItemSelectedListener(addShoppingItemsActivity: AddShoppingItemsActivity) {

}

private fun TextView.toInt(): Int {
return 1
}
