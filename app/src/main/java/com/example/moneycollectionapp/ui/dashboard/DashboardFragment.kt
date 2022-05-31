package com.example.moneycollectionapp.ui.dashboard

import android.app.Application
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneycollectionapp.R
import com.example.moneycollectionapp.databinding.FragmentDashboardBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var friendAdapter: FriendAdapter

    lateinit var  DashboardViewModel : DashboardViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val dashboardViewModel =
//            ViewModelProvider(this).get(DashboardViewModel::class.java)
        DashboardViewModel = DashboardViewModel(requireActivity().application)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text =
//        }

        val addfrnd : Button = root.findViewById(R.id.Addfrnd)
        val fname : EditText = root.findViewById(R.id.firstName)
        val lname : EditText = root.findViewById(R.id.lastName)
        val plusbtn : FloatingActionButton = root.findViewById(R.id.plusone)
        val form : CardView = root.findViewById(R.id.card)
        val cancel : Button = root.findViewById(R.id.cancel)
        // on click event to check validation

        plusbtn.setOnClickListener{
            form.visibility = View.VISIBLE
            plusbtn.visibility = View.GONE
        }

        addfrnd.setOnClickListener{

            if(fname.text.toString().trim().equals("") || lname.text.toString().trim().equals("")) {
                if (fname.text.toString().trim().equals("")) {
                    (root.findViewById(R.id.errfname) as TextView).visibility = View.VISIBLE
                    // show error message
                } else {
                    (root.findViewById(R.id.errfname) as TextView).visibility = View.INVISIBLE
                }
                if (lname.text.toString().trim().equals("")) {
                    (root.findViewById(R.id.errlname) as TextView).visibility = View.VISIBLE
                    // show error
                } else {
                    (root.findViewById(R.id.errlname) as TextView).visibility = View.INVISIBLE

                }
            }else{
                (root.findViewById(R.id.errfname) as TextView).visibility = View.INVISIBLE
                (root.findViewById(R.id.errlname) as TextView).visibility = View.INVISIBLE
                // insert
                DashboardViewModel.insertFriends(Friend(FName = fname.text.toString(), LName = lname.text.toString()));
                form.visibility = View.GONE
                plusbtn.visibility = View.VISIBLE
                val tost = Toast.makeText(this.requireContext(), "Inserted",Toast.LENGTH_SHORT)
                tost.show()
            }
        }
    cancel.setOnClickListener {
        form.visibility = View.GONE
        plusbtn.visibility = View.VISIBLE
    }

        //creating adapter for recycler view.
        val recyclerview : RecyclerView = root.findViewById(R.id.Recycler)
        var friendList = ArrayList<Friend>()
//        for (i in 1..5){
//            friendList.add(Friend(FName = "f"+i, LName = "l"+i ))
//        }
        DashboardViewModel.allFriends.observe(viewLifecycleOwner) {
            //friendList = it as ArrayList<Friend>
            getallFriends(it, friendList)
        }
        friendAdapter = FriendAdapter(friendList)
        recyclerview.layoutManager = LinearLayoutManager(this.context)

        recyclerview.adapter = friendAdapter
        friendAdapter.setItemListener(object: FriendAdapter.onItemClickListener{
            override fun onClickListener(position: Int) {
                // get all mappings of friend id  :
                Log.d("seleceted friend", "${friendList.get(position)}")

                DashboardViewModel.getAllMoneyFriendMapping(friendList.get(position).FriendId!!)
                    ?.observe(viewLifecycleOwner){
                        var total = 0.0f
                        for (item in it){
                            if(item.Type.equals("Credit")){
                                total += item.SharedAmt
                            }else {
                                total -= item.SharedAmt
                            }
                        }
                        showDialog(friendList.get(position), total)
                    }
            }
        })

        return root
    }

    private fun getallFriends(it: List<Friend>?, friendList: java.util.ArrayList<Friend>) {
        friendList.clear()
        friendList.addAll(it!!)
        friendAdapter .notifyDataSetChanged()
        Log.d("Listfriends  " , "${friendList.size}")
    }

    private fun showDialog(friend : Friend, total : Float) {
        val dialog = Dialog(this.requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_layout)

        (dialog.findViewById(R.id.fname) as TextView).text = friend.FName
        (dialog.findViewById(R.id.lname) as TextView).text = friend.LName
        (dialog.findViewById(R.id.total) as TextView).text = total.toString()

        val yesBtn = dialog.findViewById(R.id.btn_yes) as Button
        val noBtn = dialog.findViewById(R.id.btn_No) as TextView
        yesBtn.setOnClickListener {
            dialog.dismiss()
        }
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}