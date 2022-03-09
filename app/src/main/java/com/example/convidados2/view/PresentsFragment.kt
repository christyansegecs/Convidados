package com.example.convidados2.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados2.R
import com.example.convidados2.services.constants.GuestConstants
import com.example.convidados2.view.adapter.GuestAdapter
import com.example.convidados2.view.listener.GuestListener
import com.example.convidados2.viewmodel.GuestsViewModel

class PresentsFragment : Fragment() {

    private lateinit var mViewModel: GuestsViewModel
    private val mAdapter : GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        mViewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_presents, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_presents)

        recycler.layoutManager = LinearLayoutManager(context)

        recycler.adapter = mAdapter

        mListener = object : GuestListener {
            override fun onClick(id: Int) {

                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.PRESENT)
            }
        }

        mAdapter.attachListener(mListener)
        observe()

        return root
    }

    private fun observe() {
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestConstants.FILTER.PRESENT)
    }

}
