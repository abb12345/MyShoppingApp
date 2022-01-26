package com.example.myshoppinapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), ShoppingClickInterface, ShoppingClickDeleteInterface,
    ShoppingDetailsInterface {

    lateinit var ShoppingRV: RecyclerView
    lateinit var addFABItem: FloatingActionButton
    lateinit var viewModel: ShoopingViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ShoppingRV = findViewById(R.id.idRvShopping)
        addFABItem = findViewById(R.id.idAddItem)

        ShoppingRV.layoutManager = LinearLayoutManager(this)

        val shoppingRvAdapter = ShoppingRVAdapter(
            this, this,
            this, this
        )
        ShoppingRV.adapter = shoppingRvAdapter
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ShoopingViewModel::class.java)

        viewModel.allShoppingItems.observe(this, Observer { list ->
            list?.let {
                shoppingRvAdapter.updateList(it)
            }
        })
        addFABItem.setOnClickListener {
            val intent = Intent(this@MainActivity, AddShoppingItemsActivity::class.java)
            startActivity(intent)
            this.finish()

            //   val intent = Intent(this.Main)
        }
    }

    override fun onDeleteIconClick(item: Shopping) {
        viewModel.deleteShoppingItem(item)
        Toast.makeText(this, "Item deleted", Toast.LENGTH_LONG).show()
    }


    override fun onShoppingClick(item: Shopping) { //add item
        // opening a new intent and passing a data to it.
        val intent = Intent(this@MainActivity, AddShoppingItemsActivity::class.java)
        intent.putExtra("Type", "Edit")
        intent.putExtra("Title", item.ItemName)
        intent.putExtra("Description", item.ItemDescription)
        // intent.putExtra("Category", item.ItemCategory)
        intent.putExtra("Price", item.ItemPrice)
        intent.putExtra("status", item.ItemIsBought)
        intent.putExtra("Id", item.Id)
        startActivity(intent)
        this.finish()
    }

    override fun onShoppingDetailsClick(item: Shopping) {
        val intent = Intent(this@MainActivity, ShoppingDetailsActivity::class.java)

        intent.putExtra("Title", item.ItemName)
        intent.putExtra("Price", item.ItemPrice.toString())
        intent.putExtra("Description", item.ItemDescription)


        startActivity(intent)
        this.finish()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.tlbAddItem -> {


            val intent = Intent(this@MainActivity, AddShoppingItemsActivity::class.java)
            startActivity(intent)
            this.finish()
            true
        }

        R.id.tlbDeleteAll -> {
            viewModel.deleteAllShoppingItems()
            Toast.makeText(this, "All Items deleted", Toast.LENGTH_LONG).show()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.actionbar1,menu)
        return super.onCreateOptionsMenu(menu)
    }
}

