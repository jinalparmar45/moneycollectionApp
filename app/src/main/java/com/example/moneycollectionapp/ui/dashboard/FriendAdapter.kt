package com.example.moneycollectionapp.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneycollectionapp.R

class FriendAdapter(private val friendList: List<Friend>) : RecyclerView.Adapter<ViewHolder>() {
    private lateinit var  ItemListener : onItemClickListener;
    interface onItemClickListener{
        fun onClickListener(position : Int)
    }


    fun setItemListener(Listener: onItemClickListener){
        this.ItemListener = Listener;
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.friends_item, parent,false)
        return ViewHolder(view, ItemListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemVH = friendList[position]
        holder.fname.text = itemVH.FName
        holder.lname.text = itemVH.LName
    }

    override fun getItemCount(): Int {
        return friendList.size
    }

}
class ViewHolder(view: View, ItemListener: FriendAdapter.onItemClickListener) : RecyclerView.ViewHolder(view) {
    var fname: TextView = view.findViewById(R.id.itemfirstname)
    var lname: TextView = view.findViewById(R.id.itemlastname)

    init {
        itemView.setOnClickListener {
            ItemListener.onClickListener(adapterPosition)
        }
    }
}