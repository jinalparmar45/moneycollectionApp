package com.example.moneycollectionapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneycollectionapp.ui.notifications.Money
import com.example.moneycollectionapp.R

class SpendingAdapter(private val spendingList: List<Money>) : RecyclerView.Adapter<ViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.spending_history_item2, parent,false)
        return ViewHolder2(view)
    }

    override fun getItemCount(): Int {
        return spendingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
        val itemVH = spendingList[position]
        holder.date.text = itemVH.Date.toString()
        holder.type.text = itemVH.Type+"ed"
        holder.amount.text = "$ "+itemVH.Amount.toString()
        holder.sharedIn.text = "Shared in :"+itemVH.SharedIn.toString()+" friends"
    }
}
class ViewHolder2(view: View) : RecyclerView.ViewHolder(view) {
    var date: TextView = view.findViewById(R.id.itemdate)
    var type: TextView = view.findViewById(R.id.itemtype)
    var amount : TextView = view.findViewById(R.id.itemamount)
    var sharedIn : TextView = view.findViewById(R.id.itemsharedin)
    }
