package com.example.moneycollectionapp.ui.notifications

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.moneycollectionapp.MoneyFriendMapping
import com.example.moneycollectionapp.R
import com.example.moneycollectionapp.databinding.FragmentNotificationsBinding
import com.example.moneycollectionapp.ui.dashboard.DashboardViewModel
import com.example.moneycollectionapp.ui.dashboard.Friend
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val frndList = ArrayList<Friend>()
     private lateinit var frndArray: Array<String>
    private  var moneyList = ArrayList<Money>()

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel = NotificationsViewModel(requireActivity().application)
        val DashboardViewModel = DashboardViewModel(requireActivity().application)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val Autocomplete : MultiAutoCompleteTextView = root.findViewById(R.id.friends)
        val submitbtn : Button = root.findViewById(R.id.Submitbtn)
        val spinner: Spinner = root.findViewById(R.id.type)
        val amount : EditText = root.findViewById(R.id.amount)
        val errAmount :TextView = root.findViewById(R.id.errAmount)
        val errfrnd : TextView = root.findViewById(R.id.errfrnds)
        var type = ""
        ArrayAdapter.createFromResource(this.requireContext(),R.array.transaction_type, android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                type = parent?.getItemAtPosition(position).toString()
                Log.d("selectedItem", "$type")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
               //
            }

        }
        //spinner.transaction_type


        // multiple select autocomplete

        DashboardViewModel.allFriends.observe(viewLifecycleOwner){
            setFriends(it)
            val arrayAdapter = ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_list_item_1, frndArray)
            Autocomplete.setAdapter(arrayAdapter)
            Autocomplete.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

        }
        notificationsViewModel.getAllMoney()?.observe(viewLifecycleOwner) {
            moneyList.clear()
            moneyList.addAll(it)
        }
        submitbtn.setOnClickListener {
            Log.d("EnterdFriends1", "${Autocomplete.text.toString()}")
            if(amount.text.toString().toInt() <= 0 || Autocomplete.text.toString().trim().equals(""))
            {
                if(amount.text.toString().toInt() <= 0){
                    errAmount.visibility = TextView.VISIBLE
                }else{
                    errAmount.visibility = TextView.GONE
                }
                if(Autocomplete.text.toString().trim().equals("")){
                    errfrnd.visibility = TextView.VISIBLE
                }else{
                    errfrnd.visibility = TextView.GONE
                }
            }else{
                var shared: List<String> = Autocomplete.text.toString().split(",")

                if(type.equals("Debit") && shared.size > 2){
                    // show error
                    errfrnd.visibility = TextView.VISIBLE
                }else{
                    errfrnd.visibility = TextView.GONE
                    var Moneyobj = Money(Amount= amount.text.toString().toInt(), Type = type, SharedIn = shared.size, Date = (LocalDateTime.now()).format(
                        DateTimeFormatter.ofPattern("dd-MMM-yy hh:mm")).toString())
                    if(shared.size > 0) {
                        notificationsViewModel.insertSpending(Moneyobj)
                        Log.d("EnterdFriends2", "${Moneyobj}")
                        var moneyID :Int =1


                        moneyID = moneyList.size+1
                        Log.d("EnterdFriends3", "${moneyID}")
                        for(friendname in shared){
                            var friendID : Int = 0
                            Log.d("EnterdFriends4", "${moneyID} // $friendname" )
                            for(item in frndList){
                                if(friendname.trim().equals(item.FName.trim())) {

                                    friendID = item.FriendId!!
                                    var sharedamt = 0.0f
                                    if(type.equals("Credit")){
                                        sharedamt = amount.text.toString().toFloat() / (shared.size)
                                    }else{
                                        sharedamt = amount.text.toString().toFloat()
                                    }

                                    var mappingobj = MoneyFriendMapping(FriendId = friendID, MoneyId = moneyID, SharedAmt = sharedamt, Type = type)
                                    Log.d("EnterdFriends5", " $mappingobj")
                                    notificationsViewModel.insertMoneyFriendMapping(mappingobj)
                }

                                }

                        }
                    }
                }
                Toast.makeText(this.requireContext(), "Inserted successfully", Toast.LENGTH_SHORT).show()
            }
        }
//        submitbtn1.setOnClickListener {
//
////            if(amount.text.toString().toInt() <= 0 || Autocomplete.text.toString().trim().equals(""))
////            {
////                if(amount.text.toString().toInt() <= 0){
////                    errAmount.visibility = TextView.VISIBLE
////                }else{
////                    errAmount.visibility = TextView.GONE
////                }
////                if(Autocomplete.text.toString().trim().equals("")){
////                    errfrnd.visibility = TextView.VISIBLE
////                }else{
////                    errfrnd.visibility = TextView.GONE
////                }
////            }else{
//
//                // get selected type and get frnd id with autocomplete
//                //insert into money and money mapping tables.
//                 //   var shared = Autocomplete.text.toString().split(",")
////                    var Moneyobj = Money(Amount= amount.text.toString().toInt(), Type = type, SharedIn = shared.size, Date = (LocalDateTime.now()).format(
////                        DateTimeFormatter.ofPattern("dd-MMM-yy hh:mm")).toString())
//
////                if(shared.size > 0){
////                    notificationsViewModel.insertSpending(Moneyobj)
////                    var moneyID :Int =0
////                    notificationsViewModel.getAllMoney()?.observe(viewLifecycleOwner){
////                        moneyID = it.get(it.size-1).MoneyId!!
//// inserting in mapping object
//                        for(i in 0..(shared.size-1)){
//
//                            var name = shared[i]
//                            //enter everything in mapping table.
//                            //find frind id from list of frinds where friend.fname == i
//                            var friendID : Int = 0
//                            for(item in frndList){
//                                Log.d("condition", "$name and ${item.FName} && ${name.trim().equals(item.FName.trim())}")
//                                if(name.trim().equals(item.FName.trim())){
//                                    friendID = item.FriendId!!
//                                    val sharedamt = amount.text.toString().toFloat() / (shared.size)
//                                    Log.d("object", "FriendId = $friendID, MoneyId = $moneyID, SharedAmt = $sharedamt")
//                                    var mappingobj = MoneyFriendMapping(FriendId = friendID, MoneyId = moneyID, SharedAmt = sharedamt, Type = type)
//
//                                   notificationsViewModel.insertMoneyFriendMapping(mappingobj)
//
//                                }
//                            }
//
//                            // shared amount = entered amount / shared length+1
//
//                            // Problem: Money ID is null, ::
//                            //solution : get all money list and ID = list.get(list.size -1).MoneyID as last inserted moneyID will be the one for now.
//                            if(friendID> 0){
//
//                            }
//
//                        }
//                        Toast.makeText(this.requireContext(), "Inserted successfully", Toast.LENGTH_SHORT).show()
//
//                    }
//
////                        var frg: Fragment? = getFragmentManager()?.findFragmentByTag("Your_Fragment_TAG")
////                        var fts : FragmentTransaction? =  getFragmentManager()?.beginTransaction()
////                        fts?.detach(frg!!)
////                        fts?.attach(frg!!)
////                        fts?.commit()
//                }
//            }
//
//
//
//        }
//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    private fun setFriends(it: List<Friend>?) {
        frndList.clear()
        frndList.addAll(it!!)
        frndArray= Array<String>(size = it.size){""}
         for((ind,item) in it.withIndex()){
             frndArray.set(ind , item.FName)
         }
        Log.d("Friends in Notification", "${frndArray.size} // ${frndList.size} // $it")
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

//class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {
//   private  var itemSelected ="Credit"
//    private lateinit var spinnerInterface  : AdapterView.OnItemSelectedListener
//
//    interface spinnerInterface {
//        fun onItemSelected ()
//    }

//    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
//        // An item was selected. You can retrieve the selected item using
//        var posi = parent.getItemAtPosition(pos)
//        itemSelected = posi
//        Log.d("sleceted item", "$posi")
//    }
//
//    override fun onNothingSelected(parent: AdapterView<*>) {
//        // Another interface callback
//    }
//
//    fun getslectedItem(): String {
//       return  itemSelected;
//    }
//}