package com.example.moneycollectionapp.ui.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneycollectionapp.ui.notifications.Money
import com.example.moneycollectionapp.R
import com.example.moneycollectionapp.databinding.FragmentHomeBinding
import com.example.moneycollectionapp.ui.dashboard.DashboardViewModel
import java.time.LocalDateTime
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var spendingAdapter : SpendingAdapter

    lateinit var HomeViewModel : HomeViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
        HomeViewModel = HomeViewModel(requireActivity().application)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
            // recycler view
        val recyclerview : RecyclerView = root.findViewById(R.id.spendingsList)
        var moneyList = ArrayList<Money>()
        HomeViewModel.allMoney.observe(viewLifecycleOwner){
            getallSpendings(it, moneyList)
            var total : Float = 0.0f
            for(item in moneyList){
                if(item.Type.equals("Credit")){
                    total += item.Amount
                }else{
                    total -= item.Amount
                }
            }
            root.findViewById<TextView>(R.id.SpendingTotal).text = total.toString()
        }
        //moneyList .add(Money(Amount = 0, SharedIn = 0, Type = "Loading", Date = ""))
//        for (i in 1..5){
//
//        }
        spendingAdapter = SpendingAdapter(moneyList)
        recyclerview.layoutManager = LinearLayoutManager(this.context)
        recyclerview.adapter = spendingAdapter

        return root
    }

    private fun getallSpendings(it: List<Money>?, moneyList: ArrayList<Money>) {
        moneyList.clear()
        moneyList.addAll(it?.reversed()!!)
        spendingAdapter.notifyDataSetChanged()
        Log.d("Listfriends  " , "${moneyList.size}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}