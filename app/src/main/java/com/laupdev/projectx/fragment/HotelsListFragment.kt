package com.laupdev.projectx.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.laupdev.projectx.adapter.HotelsListRecyclerViewAdapter
import com.laupdev.projectx.databinding.FragmentHotelsListBinding
import com.laupdev.projectx.model.HotelsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: 01.11.2021 Implement paging

        recyclerView = binding.hotelsList
//        recyclerView.adapter = HotelsListRecyclerViewAdapter()

        // TODO: 01.11.2021 Finish recyclerView implementation

    }


}