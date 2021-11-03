package com.laupdev.projectx.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.laupdev.projectx.databinding.FragmentHotelsListBinding
import com.laupdev.projectx.domain.viewmodel.HotelsViewModel
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

        recyclerView = binding.hotelsList
        recyclerView.adapter = viewModel.hotelAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    if (viewModel.isLoadingAllowed) {
                        Timber.e("LOAD DATA")
                        viewModel.getHotelsPaging()
                    }
                }
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}