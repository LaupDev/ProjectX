package com.laupdev.projectx.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.laupdev.projectx.adapter.HotelsListRecyclerViewAdapter
import com.laupdev.projectx.database.Hotel
import com.laupdev.projectx.databinding.FragmentHotelsListBinding
import com.laupdev.projectx.model.HotelsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HotelsListFragment : Fragment() {

    private val viewModel by viewModel<HotelsViewModel>()

    private var _binding: FragmentHotelsListBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hotelsList = mutableListOf<Hotel>()
        val adapter = HotelsListRecyclerViewAdapter(hotelsList)

        recyclerView = binding.hotelsList
        recyclerView.adapter = adapter

        viewModel.hotelsList.observe(viewLifecycleOwner) {
            it?.let {
                hotelsList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    if (viewModel.isDataLoaded) {
                        Timber.e("LOAD DATA")
                        viewModel.getHotelsPaging()
                    }
                }
            }
        })

    }


}