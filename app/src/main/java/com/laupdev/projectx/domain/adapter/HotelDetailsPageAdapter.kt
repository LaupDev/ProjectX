package com.laupdev.projectx.domain.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.laupdev.projectx.presentation.hotel.details.HotelDetailsGalleryFragment

class HotelDetailsPageAdapter(private val hotelId: Int, fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                HotelDetailsGalleryFragment.newInstance(hotelId)
            }
            1 -> {
                HotelDetailsGalleryFragment.newInstance(hotelId)
            }
            2 -> {
                HotelDetailsGalleryFragment.newInstance(hotelId)
            }
            else -> {
                HotelDetailsGalleryFragment()
            }
        }
    }
}