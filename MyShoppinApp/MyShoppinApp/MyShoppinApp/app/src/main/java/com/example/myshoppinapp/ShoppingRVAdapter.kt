package com.example.myshoppinapp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.myshoppinapp.R.id.idAddTVItemPrice

class ShoppingRVAdapter(
    val context: Context,
    val  shoppingClickInterface: ShoppingClickInterface,
    val shoppingClickDeleteInterface: ShoppingClickDeleteInterface,
    val shoppingDetailsInterface : ShoppingDetailsInterface
) :RecyclerView.Adapter<ShoppingRVAdapter.ViewHolder>() {

    private val allShoppingItems = ArrayList<Shopping>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val shoppingItemTV = itemView.findViewById<TextView>(R.id.idAddTVItemTitle)

        val shoppingPriceTV =  itemView.findViewById<TextView>(R.id.idAddTVItemPrice)
       public final val shoppingIsPurchasedCB : CheckBox = itemView.findViewById<CheckBox>(R.id.IdCbPurchased)
        val deleteTvBtn = itemView.findViewById<Button>(R.id.idTvDeleteItem)

        val DetailsBtn =  itemView.findViewById<Button>(R.id.DetailsBtn)
        val imageViewIcon  =itemView.findViewById<ImageView>(R.id.IdImageIcon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.shopping_rv_items,
            parent, false
        )
return ViewHolder(itemView) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.shoppingItemTV.setText(allShoppingItems.get(position).ItemName)
        //holder.shoppingDescTV.setText(allShoppingItems.get(position).ItemDescription)
         holder.shoppingPriceTV.setText(allShoppingItems.get(position).ItemPrice.toString())
        val status = allShoppingItems.get(position).ItemIsBought.toString()

val category = allShoppingItems.get(position).ItemCategory.toString()
        if(category == "Food")
        {
           holder.imageViewIcon.setImageResource(R.drawable.food);
        }
        else if(category == "Electronics") {
            holder.imageViewIcon.setImageResource(R.drawable.electronics);
        }
       else {
            holder.imageViewIcon.setImageResource(R.drawable.clothes);
        }



        holder.shoppingIsPurchasedCB.isChecked = status == "true"


        holder.deleteTvBtn.setOnClickListener {
            shoppingClickDeleteInterface.onDeleteIconClick(allShoppingItems.get(position))
       }
holder.DetailsBtn.setOnClickListener {

shoppingDetailsInterface.onShoppingDetailsClick(allShoppingItems.get(position))
}
        holder.itemView.setOnClickListener {
          shoppingClickInterface.onShoppingClick(allShoppingItems.get(position))
     }


    }
    override fun getItemCount(): Int {
        return allShoppingItems.size
    }
fun updateList(myList: List<Shopping>)
{
    allShoppingItems.clear()
    allShoppingItems.addAll(myList)
    notifyDataSetChanged()
}
}




interface ShoppingClickDeleteInterface
{
    fun onDeleteIconClick(item :Shopping)
}
interface  ShoppingClickInterface {
    fun onShoppingClick(item :Shopping)
}

interface ShoppingDetailsInterface {
    fun onShoppingDetailsClick(item: Shopping)
}