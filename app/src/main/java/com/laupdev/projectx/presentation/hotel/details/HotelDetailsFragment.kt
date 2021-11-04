package com.laupdev.projectx.presentation.hotel.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.laupdev.projectx.R
import com.laupdev.projectx.databinding.FragmentHotelDetailsBinding
import com.laupdev.projectx.domain.adapter.HotelDetailsPageAdapter

private const val ARG_HOTEL_ID = "hotel_id"

class HotelDetailsFragment : Fragment() {

    private var hotelId = 0

    private var _binding: FragmentHotelDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hotelId = it.getInt(ARG_HOTEL_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
    }

    private fun setupViewPager() {
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = HotelDetailsPageAdapter(hotelId,this)
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.overview)
                }
                1 -> {
                    tab.text = getString(R.string.gallery)
                }
                2 -> {
                    tab.text = getString(R.string.contacts)
                }
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}